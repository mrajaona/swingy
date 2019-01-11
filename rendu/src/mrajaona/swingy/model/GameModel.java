package mrajaona.swingy.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.util.Util;
import mrajaona.swingy.builder.HeroBuilder;
import mrajaona.swingy.Save;

public class GameModel {

    @SuppressWarnings("unused")
    private GameModel() {}

    public static void setLocale(Locale newLocale) {
        GameData.setLocale(newLocale);
    }

    public static void createHero() {
		GameData.setHero(
			HeroBuilder.getBuilder().newHero()
			);
    }

    public static void loadHero(long id) throws SQLException, IOException {
		GameData.setHero(
			HeroBuilder.getBuilder().loadHero(
				Save.getManager().load(id)
				)
			);
    }

    public static void changeViewType(String newType) {
        if (newType != Util.VIEW_TYPE_GUI && newType != Util.VIEW_TYPE_CONSOLE)
            ; // Exception
        else
    		GameData.setViewType(newType);
    }

}
