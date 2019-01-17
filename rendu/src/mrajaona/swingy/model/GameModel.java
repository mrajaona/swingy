package mrajaona.swingy.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.IllformedLocaleException;
import java.util.Locale;
import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.character.HeroData;
import mrajaona.swingy.util.SaveManager;
import mrajaona.swingy.util.Util;
import mrajaona.swingy.view.View;
import mrajaona.swingy.view.gui.Window;
import mrajaona.swingy.view.helper.BuildHelper;
import mrajaona.swingy.builder.HeroBuilder;

public class GameModel {

    @SuppressWarnings("unused")
    private GameModel() {}

    public static void init (Locale locale, String viewType) throws SQLException, IOException {
        GameData.setLocale(locale); // "en", "fr"
        changeViewType(viewType);
    }

    public static void setLocale(Locale newLocale) {
        GameData.setLocale(newLocale);
        // TODO : update view
    }

    public static void setLocale(String arg) {
        try {
            Locale newLocale = new Locale.Builder().setLanguage(arg).build();
            setLocale(newLocale);
            // TODO : check supported language
            // TODO : update view

        } catch (IllformedLocaleException e) {
            // invalid language format
        }
    }

    public static void createHero() throws SQLException, IOException {
        GameData.setHero(
            HeroBuilder.getBuilder().newHero()
            );
        SaveManager.getManager().save();
        BuildHelper.next();
    }

    public static void createHero(String heroClass, String heroName) throws SQLException, IOException {
        GameData.setHero(
            HeroBuilder.getBuilder().newHero(heroClass, heroName)
            );
        SaveManager.getManager().save();
        BuildHelper.next();
    }

    public static void loadHero(long id) throws SQLException, IOException {
        GameData.setHero(
            HeroBuilder.getBuilder().loadHero(
                SaveManager.getManager().load(id)
                )
            );
        BuildHelper.next();
    }

    public static void deleteHero(long id) throws SQLException, IOException {
        SaveManager.getManager().delete(id);
    }

    public static void changeViewType(String newType) throws SQLException, IOException {
        Util.ViewTypes tmp = Util.ViewTypes.getKeyByValue(newType);

        if (tmp == null) {
            ; // Exception
        } else {
            changeViewType(tmp);
        }
    }

    public static void changeViewType(Util.ViewTypes newType) throws SQLException, IOException {
        if (newType == null) {
            ; // Exception
        } else {
            GameData.setViewType(newType);

            // hide gui if view type changed
            if (newType != Util.ViewTypes.GUI)
                Window.getWindow().hide();

            // update view
            View.show();
        }
    }

    public static void changeScreen(Util.GameScreen screen) throws SQLException, IOException {
        GameData.setScreen(screen);
        View.show();
    }

}
