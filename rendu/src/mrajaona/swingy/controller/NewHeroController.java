package mrajaona.swingy.controller;

import java.io.IOException;
import java.sql.SQLException;

import mrajaona.swingy.model.GameModel;

public class NewHeroController {

    public static void create(String heroClass, String heroName) throws SQLException, IOException {
        GameModel.createHero(heroClass, heroName);
        mrajaona.swingy.Game.getGame().waiting(false);
    }

}
