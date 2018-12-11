package mrajaona.swingy.game;

import mrajaona.swingy.elements.characters.hero.HeroController;

public class Swingy {

    public static void main(String[] args) {

        System.out.println("Hello Maven!" + System.lineSeparator() + "Welcome to Swingy!");

        HeroController heroController = new HeroController();

        heroController.updateView();

    }

}
