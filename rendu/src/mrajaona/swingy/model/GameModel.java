package mrajaona.swingy.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.IllformedLocaleException;
import java.util.Locale;
import java.util.ResourceBundle;

import mrajaona.swingy.builder.GameMapBuilder;
import mrajaona.swingy.builder.HeroBuilder;
import mrajaona.swingy.builder.SaveFileBuilder;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.ArtifactData;
import mrajaona.swingy.data.character.EnemyData;
import mrajaona.swingy.util.ResourceMap;
import mrajaona.swingy.util.SaveManager;
import mrajaona.swingy.util.Util.ArtifactType;
import mrajaona.swingy.util.Util.GameScreen;
import mrajaona.swingy.util.Util.ViewTypes;
import mrajaona.swingy.view.View;
import mrajaona.swingy.view.console.ConsoleView;
import mrajaona.swingy.view.gui.GUIMain;
import mrajaona.swingy.view.gui.Window;
import mrajaona.swingy.view.helper.MainHelper;
import mrajaona.swingy.view.helper.TitleHelper;

public class GameModel {

    @SuppressWarnings("unused")
    private GameModel() {}

    public static void init (Locale locale, String viewType) throws SQLException, IOException {
        GameData.getData().setLocale(locale);
        changeViewType(viewType);
    }

    public static void setLocale(Locale newLocale) throws SQLException, IOException {
        GameData.getData().setLocale(newLocale);
        View.update();
    }

    public static void setLocale(String arg) throws SQLException, IOException {
        try {
            if (!arg.equals("en") && !arg.equals("fr")) {
                ConsoleView.println(
                    ResourceBundle.getBundle(
                        "mrajaona.swingy.locale.ErrorResource",
                        GameData.getData().getLocale() )
                    .getString("invalidLanguage")
                ); // only possible in console view
                return ;
            }

            Locale newLocale = new Locale.Builder().setLanguage(arg).build();
            setLocale(newLocale);

        } catch (IllformedLocaleException e) {
            // Exception : invalid language format
        }
    }

    public static void enemyEncounter(EnemyData enemy) {
        GameData.getData().setEnemy(enemy);
        MainHelper.changeSubScreen();
    }

    public static void enemyEncounterEnd() {
        GameData.getData().setEnemy(null);
        MainHelper.changeSubScreen();
    }

    public static void drop(ArtifactData artifact) {
        GameData.getData().setArtifact(artifact);
        ArtifactType type = artifact.getType();

        if (type == null) {
            // Exception
            return ;
        }

        String name = artifact.getName();
        ResourceMap resMap;
        switch (type) {
            case HELM :
                resMap = (ResourceMap) ResourceBundle.getBundle(
                    "mrajaona.swingy.locale.ArtifactResource",
                    GameData.getData().getLocale() )
                .getObject("HelmList");
                break ;
            case ARMOR :
                resMap = (ResourceMap) ResourceBundle.getBundle(
                    "mrajaona.swingy.locale.ArtifactResource",
                    GameData.getData().getLocale() )
                .getObject("ArmorList");
                break ;
            case WEAPON :
                resMap = (ResourceMap) ResourceBundle.getBundle(
                    "mrajaona.swingy.locale.ArtifactResource",
                    GameData.getData().getLocale() )
                .getObject("WeaponList");
                break ;
            default :
                // Exception
                resMap = null;
                break ;
        }

        if (resMap == null) {
            // Exception
            return ;
        }

        String msg = String.format(
            ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() ).getString("msgDrop"),
            resMap.get(name), //s
            type.localizeType(), // %2$s
            type.localizeStat(), // %3$s
            artifact.getModifier() // %4$d
        );
        MainHelper.printMsg(msg);
        MainHelper.changeSubScreen();
    }

    public static void noDrop() {
        GameData.getData().setArtifact(null);
        MainHelper.changeSubScreen();
    }

    public static void noLoot() throws SQLException, IOException {
        MainHelper.printMsg(ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() ).getString("msgLeave"));
        noDrop();
        MainHelper.waitForInput();
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
            TitleHelper.printMsg("Could not load file for Hero id " + heroId);
            return ;
        }

        GameData.getData().setHero(GameData.getData().getSaveFile().getHero());
        GameData.getData().setMap(GameData.getData().getSaveFile().getMap());

        GUIMain.getScreen().updateTable();

        if ( GameData.getData().getMap().getLevel() == 0 )
            GameMapModel.initMap();
        else
            GameMapModel.checkInitPosition();
    }

    public static void title() throws SQLException, IOException {
        MainHelper.clean();
        GameMapModel.removeMap();
        GameData.getData().setHero(null);
        changeScreen(GameScreen.TITLE);
    }

    public static void deleteHero(long id) throws SQLException, IOException {
        SaveManager.getManager().delete(id);
    }

    public static void changeViewType(String newType) throws SQLException, IOException {
        ViewTypes tmp = ViewTypes.getKeyByValue(newType);

        if (tmp == null) {
            ; // Exception
        } else {
            changeViewType(tmp);
        }
    }

    public static void changeViewType(ViewTypes newType) throws SQLException, IOException {
        if (newType == null) {
            ; // Exception
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
        MainHelper.clean();
        changeScreen(GameScreen.MAIN);
    }

}
