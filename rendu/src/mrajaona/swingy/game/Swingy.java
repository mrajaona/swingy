package mrajaona.swingy.game;

import mrajaona.swingy.elements.characters.hero.HeroController;
import mrajaona.swingy.save.DatabaseManager;

public class Swingy {

    public static void main(String[] args) {

        try {
            DatabaseManager dbManager = DatabaseManager.getManager();
            dbManager.openConnection();

            System.out.println("Hello Maven!" + System.lineSeparator() + "Welcome to Swingy!");

            HeroController heroController = new HeroController();

            heroController.updateView();
            dbManager.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
