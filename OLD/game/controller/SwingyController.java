package mrajaona.swingy.game.controller;

import java.sql.SQLException;

import mrajaona.swingy.elements.characters.enemy.EnemyController;
import mrajaona.swingy.elements.characters.hero.Hero;
import mrajaona.swingy.elements.characters.hero.HeroController;
import mrajaona.swingy.game.model.Swingy;
import mrajaona.swingy.game.view.SwingyView;
import mrajaona.swingy.save.DatabaseManager;

import lombok.AccessLevel;
import lombok.Getter;

public class SwingyController {
    @Getter private static Swingy      model = new Swingy();
    @Getter private static SwingyView  view;

    private void newHero()  {
        model.setHeroController(new HeroController());
        model.getHeroController().updateView();
        System.out.println("New hero created.");
    }

    private void loadHero(long id) throws SQLException {
        Hero hero = model.getDbManager().load(id);

        if (hero == null) {
            System.out.println("No hero found.");
        }

        model.setHeroController(new HeroController(hero));
        model.getHeroController().updateView();
        System.out.println("Hero loaded.");
    }

    private void saveHero() throws SQLException {
        if (model.getHeroController() == null) {
            System.out.println("No hero to save.");
        } else {
            model.getDbManager().save(model.getHeroController().getModel());
            System.out.println("Hero saved.");
        }
    }

    public void game(String[] args) {
    	// create hero
        try {
            model.setDbManager(DatabaseManager.getManager());
            model.getDbManager().openConnection();

            System.out.println("Hello Maven!" + System.lineSeparator() + "Welcome to Swingy!");

            newHero();
            saveHero();

            model.getDbManager().closeConnection();
            model.setHeroController(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    	// load hero
        try {
            model.setDbManager(DatabaseManager.getManager());
            model.getDbManager().openConnection();

            System.out.println("Hello Maven!" + System.lineSeparator() + "Welcome to Swingy!");

            loadHero(1);
            saveHero();

            model.getDbManager().closeConnection();
            model.setHeroController(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // create enemy
        try {
            System.out.println("Hello Maven!" + System.lineSeparator() + "Welcome to Swingy!");

            model.setEnemyController(new EnemyController("Slime", 5));
            model.getEnemyController().updateView();

            model.setEnemyController(null);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new SwingyController().game(args);
    }

}
