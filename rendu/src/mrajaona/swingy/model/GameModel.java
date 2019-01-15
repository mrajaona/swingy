package mrajaona.swingy.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.character.HeroData;
import mrajaona.swingy.util.Util;
import mrajaona.swingy.builder.HeroBuilder;
import mrajaona.swingy.Save;

public class GameModel {

    @SuppressWarnings("unused")
    private GameModel() {}

    public static void setLocale(Locale newLocale) {
        GameData.setLocale(newLocale);
    }

    public static void createHero() throws SQLException, IOException {
		GameData.setHero(
			HeroBuilder.getBuilder().newHero()
			);
        Save.getManager().save();
    }

    public static void loadHero(long id) throws SQLException, IOException {
		GameData.setHero(
			HeroBuilder.getBuilder().loadHero(
				Save.getManager().load(id)
				)
			);
    }

    public static void deleteHero(long id) throws SQLException, IOException {
        Save.getManager().delete(id);
    }

    public static void changeViewType(String newType) {
        if (!newType.equals(Util.VIEW_TYPE_GUI) && !newType.equals(Util.VIEW_TYPE_CONSOLE)) {
            ; // Exception
        }
        else {
    		GameData.setViewType(newType);
        }
    }

}
