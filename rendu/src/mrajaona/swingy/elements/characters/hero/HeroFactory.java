package mrajaona.swingy.elements.characters.hero;

import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class HeroFactory {
    
    private static HeroFactory factory = new HeroFactory();

    private HeroFactory() {}

    public static HeroFactory getFactory() {
        return (factory);
    }

    // Create hero

    public Hero    newHero() {
        Scanner inputScanner = new Scanner(System.in);

        //Create ValidatorFactory which returns validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
         
        //It validates bean instances
        Validator validator = factory.getValidator();

        Hero hero = new Hero();

        boolean valid = false;

        while (valid == false) {

            if (hero.getHeroClass() == null || hero.getHeroClass().trim().isEmpty()) {
                System.out.println("Choose your class (Warrior, Thief, Mage, Priest) :");
                hero.setHeroClass(inputScanner.nextLine());
            }
            if (hero.getHeroName() == null || hero.getHeroName().trim().isEmpty()) {
                System.out.println("Name your hero :");
                hero.setHeroName(inputScanner.nextLine());
            }

            hero.initStats();

            //Validate bean
            Set<ConstraintViolation<Hero>> constraintViolations = validator.validate(hero);
     
            //Show errors
            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Hero> violation : constraintViolations) {
                    System.out.println(violation.getMessage());
                }
            } else {
                System.out.println("Valid Object");
                valid = true;
            }

        }

        return (hero);

    }

}
