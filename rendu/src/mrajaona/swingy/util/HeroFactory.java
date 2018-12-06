package mrajaona.swingy.util;

import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import mrajaona.swingy.elements.characters.hero.Hero;
import mrajaona.swingy.elements.characters.hero.HeroController;
import mrajaona.swingy.elements.characters.hero.HeroView;

public class HeroFactory {

    private static HeroFactory factory = new HeroFactory();

    private HeroFactory() {}

    public static HeroFactory getFactory() {
        return (factory);
    }

    // Create hero

    public HeroController   newHero() {

        //Create ValidatorFactory which returns validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        //It validates bean instances
        Validator validator = factory.getValidator();

        Scanner inputScanner = new Scanner(System.in);

        Hero hero                   = new Hero();
        HeroView view               = new HeroView();
        HeroController controller   = new HeroController(hero, view);

        boolean valid = false;

        while (valid == false) {

            controller.initHero(inputScanner);

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

        inputScanner.close();

        return (controller);

    }

}
