package mrajaona.swingy.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.character.HeroData;
import mrajaona.swingy.util.Util;
import mrajaona.swingy.view.View;
import mrajaona.swingy.view.helper.BuildHelper;
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
        BuildHelper.next();
    }

    public static void createHero(String heroClass, String heroName) throws SQLException, IOException {
        GameData.setHero(
            HeroBuilder.getBuilder().newHero(heroClass, heroName)
            );
        Save.getManager().save();
        BuildHelper.next();
    }

    public static void loadHero(long id) throws SQLException, IOException {
        GameData.setHero(
            HeroBuilder.getBuilder().loadHero(
                Save.getManager().load(id)
                )
            );
        BuildHelper.next();
    }

    public static void deleteHero(long id) throws SQLException, IOException {
        Save.getManager().delete(id);
    }

    public static void changeViewType(String newType) {
        Util.ViewTypes tmp = Util.ViewTypes.getKeyByValue(newType);

        if (tmp == null) {
            ; // Exception
        } else {
            GameData.setViewType(tmp);
        }
    }

    public static void changeScreen(Util.GameScreen screen) throws SQLException, IOException {
        GameData.setScreen(screen);
        View.show();
    }

}
