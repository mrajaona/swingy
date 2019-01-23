package mrajaona.swingy.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.IllformedLocaleException;
import java.util.Locale;

import mrajaona.swingy.builder.GameMapBuilder;
import mrajaona.swingy.builder.HeroBuilder;
import mrajaona.swingy.builder.SaveFileBuilder;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.ArtifactData;
import mrajaona.swingy.data.character.EnemyData;
import mrajaona.swingy.util.SaveManager;
import mrajaona.swingy.util.Util.GameScreen;
import mrajaona.swingy.util.Util.ViewTypes;
import mrajaona.swingy.view.View;
import mrajaona.swingy.view.gui.GUIMain;
import mrajaona.swingy.view.gui.Window;

public class GameModel {

    @SuppressWarnings("unused")
    private GameModel() {}

    public static void init (Locale locale, String viewType) throws SQLException, IOException {
        GameData.getData().setLocale(locale); // "en", "fr"
        changeViewType(viewType);
    }

    public static void setLocale(Locale newLocale) {
        GameData.getData().setLocale(newLocale);
        // TODO : update view
    }

    public static void setLocale(String arg) {
        try {
            Locale newLocale = new Locale.Builder().setLanguage(arg).build();
            setLocale(newLocale);
            // TODO : check supported language
            // TODO : update view

        } catch (IllformedLocaleException e) {
            // TODO : invalid language format
        }
    }

    public static void enemyEncounter(EnemyData enemy) {
        GameData.getData().setEnemy(enemy);
    }

    public static void enemyEncounterEnd() {
        GameData.getData().setEnemy(null);
    }

    public static void drop(ArtifactData artifact) {
        GameData.getData().setArtifact(artifact);
    }

    public static void noDrop() {
        GameData.getData().setArtifact(null);
    }

    public static void createHero() throws SQLException, IOException {
        HeroBuilder hBuilder = new HeroBuilder();
        GameData.getData().setHero(
            hBuilder.newHero()
            );
        GUIMain.getScreen().updateTable();

        GameMapBuilder gmBuilder = new GameMapBuilder();
        GameData.getData().setMap(
            gmBuilder.newMap()
            );

        SaveManager.getManager().save();
        generateMap();
    }

    public static void createHero(String heroClass, String heroName) throws SQLException, IOException {
        HeroBuilder hBuilder = new HeroBuilder();
        GameData.getData().setHero(
            hBuilder.newHero(heroClass, heroName)
            );
        GUIMain.getScreen().updateTable();

        GameMapBuilder gmBuilder = new GameMapBuilder();
        GameData.getData().setMap(
            gmBuilder.newMap()
            );

        SaveManager.getManager().save();
        generateMap();
    }

    public static void loadFile(long heroId) throws SQLException, IOException {
        SaveFileBuilder sfBuilder = new SaveFileBuilder();
        GameData.getData().setSaveFile(
            sfBuilder.loadFile(
                SaveManager.getManager().load(heroId)
                    ));

        if (GameData.getData().getSaveFile() == null) {
            System.out.println("Could not load file for Hero id " + heroId); // Debug // TODO : vrai message
            return ;
        }

        GameData.getData().setHero(GameData.getData().getSaveFile().getHero());
        GameData.getData().setMap(GameData.getData().getSaveFile().getMap());

        GUIMain.getScreen().updateTable();

        if ( GameData.getData().getMap().getLevel() == 0 )
            GameMapModel.initMap();
        changeScreen(GameScreen.MAIN);
    }


    public static void deleteHero(long id) throws SQLException, IOException {
        SaveManager.getManager().delete(id);
    }

    public static void changeViewType(String newType) throws SQLException, IOException {
        ViewTypes tmp = ViewTypes.getKeyByValue(newType);

        if (tmp == null) {
            ; // TODO : Exception
        } else {
            changeViewType(tmp);
        }
    }

    public static void changeViewType(ViewTypes newType) throws SQLException, IOException {
        if (newType == null) {
            ; // TODO : Exception
        } else {
            GameData.getData().setViewType(newType);

            // hide gui if view type changed
            if (newType != ViewTypes.GUI)
                Window.getWindow().hide();

            // update view
            View.show();
        }
    }

    public static void changeScreen(GameScreen screen) throws SQLException, IOException {
        GameData.getData().setScreen(screen);
        View.show();
    }

    public static void generateMap() throws SQLException, IOException {
        GameMapModel.initMap();
        changeScreen(GameScreen.MAIN);
    }

}
