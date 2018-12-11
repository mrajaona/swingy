package mrajaona.swingy.game;

import java.sql.SQLException;

import mrajaona.swingy.elements.characters.hero.HeroController;
import mrajaona.swingy.save.DatabaseManager;

public class Swingy {

    private static DatabaseManager dbManager     = null;
    private static HeroController heroController = null;

    private static void newHero()  {
        heroController = new HeroController();
        heroController.updateView();
        System.out.println("New hero created.");
    }

    private static void loadHero() {

    }

    private static void saveHero() throws SQLException {
        if (heroController == null) {
            System.out.println("No hero to save.");
        } else {
            dbManager.save(heroController.getModel());
            System.out.println("Hero saved.");
        }
    }

    public static void main(String[] args) {

        try {
            dbManager = DatabaseManager.getManager();
            dbManager.openConnection();

            System.out.println("Hello Maven!" + System.lineSeparator() + "Welcome to Swingy!");

            newHero();
            saveHero();

            dbManager.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
