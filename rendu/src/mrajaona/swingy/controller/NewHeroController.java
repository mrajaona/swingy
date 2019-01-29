package mrajaona.swingy.controller;

import java.io.IOException;
import java.sql.SQLException;

import mrajaona.swingy.exception.SwingyException;
import mrajaona.swingy.model.GameModel;

public class NewHeroController {

    public static void create(String heroClass, String heroName) throws SQLException, IOException, SwingyException {
        GameModel.createHero(heroClass, heroName);
    }

}
