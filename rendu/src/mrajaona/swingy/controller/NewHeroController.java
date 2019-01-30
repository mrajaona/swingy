package mrajaona.swingy.controller;

import java.io.IOException;
import java.sql.SQLException;

import mrajaona.swingy.Game;
import mrajaona.swingy.exception.SwingyException;
import mrajaona.swingy.model.GameModel;

public class NewHeroController {

    public static void create(final String heroClass, final String heroName) throws SQLException, IOException, SwingyException {
    	Game.getGame().insertToQueue(
            new Runnable() {
                    public void run() {
                        try {
                            GameModel.createHero(heroClass, heroName);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            );
    }

}
