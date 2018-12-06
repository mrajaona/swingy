package mrajaona.swingy.game;

import mrajaona.swingy.elements.characters.hero.HeroController;
import mrajaona.swingy.util.HeroFactory;

public class Swingy {

    public static void main(String[] args) {

        System.out.println("Hello Maven!" + System.lineSeparator() + "Welcome to Swingy!");

        HeroFactory hFactory          = HeroFactory.getFactory();
        HeroController heroController = hFactory.newHero();

        heroController.updateView();

    }

}
