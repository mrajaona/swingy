package mrajaona.swingy.util;

import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Collections;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import mrajaona.swingy.elements.characters.hero.Hero;

public class HeroFactory {
	
    private static HeroFactory factory = new HeroFactory();

    private HeroFactory() {}

    public static HeroFactory getFactory() {
        return (factory);
    }

    // Nested interfaces

    private interface HeroCreator {
        public Hero make(final String heroName);
    }

    // Hero types

    public static final String WARRIOR	= "Warrior";
    public static final String THIEF	= "Thief";
    public static final String MAGE	    = "Mage";
    public static final String PRIEST	= "Priest";
    
    private static final String [] heroTypes    = {
        WARRIOR,
        THIEF,
        MAGE,
        PRIEST
    } ;
  
    private static final Map <String, HeroCreator> CREATOR_MAP = initCreatorMap();
    private static final Map <String, HeroCreator> initCreatorMap() {
        Map <String,HeroCreator> newMap = new HashMap <String, HeroCreator> (heroTypes.length);

        newMap.put(
            WARRIOR,
            new HeroCreator() {
                public Hero make(final String heroName) {
                    return (new Hero(heroName, WARRIOR, 10, 25, 75));
                }
            }
        );

        newMap.put(
            THIEF,
            new HeroCreator() {
                public Hero make(final String heroName) {
                    return (new Hero(heroName, THIEF, 20, 20, 50));
                }
            }
        );

        newMap.put(
            MAGE,
            new HeroCreator() {
                public Hero make(final String heroName) {
                    return (new Hero(heroName, MAGE, 30, 10, 50));
                }
            }
        );

        newMap.put(
            PRIEST,
            new HeroCreator() {
                public Hero make(final String heroName) {
                    return (new Hero(heroName, PRIEST, 10, 10, 150));
                }
            }
        );

        return (Collections.unmodifiableMap(newMap));
    }

    // Create hero

    public Hero     newHero() {
        Scanner inputScanner = new Scanner(System.in);

        HeroCreator creator = null;
        String  heroClass;

        while (creator == null) {
            System.out.println("Choose your class :");
            heroClass   = inputScanner.nextLine(); // wait for user input
            creator     = CREATOR_MAP.get(heroClass);
        }

        //Create ValidatorFactory which returns validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
         
        //It validates bean instances
        Validator validator = factory.getValidator();

        String heroName;
        Hero hero = null;

        while (hero == null) {
            System.out.println("Name your hero :");
            heroName    = inputScanner.nextLine(); // wait for user input
            hero   = creator.make(heroName);

            //Validate bean
            Set<ConstraintViolation<Hero>> constraintViolations = validator.validate(hero);
     
            //Show errors
            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Hero> violation : constraintViolations) {
                    System.out.println(violation.getMessage());
                }
                hero = null;
            } else {
                System.out.println("Valid Object");
            }

        }

        return (hero);
    }

}
