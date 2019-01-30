package mrajaona.swingy.view.helper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import mrajaona.swingy.controller.TitleScreenController;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.character.HeroData;
import mrajaona.swingy.exception.InvalidViewTypeException;
import mrajaona.swingy.exception.LoadHeroListException;
import mrajaona.swingy.exception.SwingyException;
import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.util.ResourceMap;
import mrajaona.swingy.util.SaveManager;
import mrajaona.swingy.util.Util.GameScreen;
import mrajaona.swingy.util.Util.ViewTypes;
import mrajaona.swingy.view.console.ConsoleView;
import mrajaona.swingy.view.gui.GUITitle;
import mrajaona.swingy.view.gui.Window;

public class TitleHelper {

    private static DecimalFormat   format = new DecimalFormat("#");

    @SuppressWarnings("unused")
    private TitleHelper() {}

    public static void printMsg(String message) throws InvalidViewTypeException {
        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            ConsoleView.println(message);
        } else if (GameData.getData().getViewType().equals(ViewTypes.GUI)) {
            ; // TODO : modal
        } else {
            throw (new InvalidViewTypeException());
        }
    }

    public static void printPrompt() throws SQLException, IOException, LoadHeroListException, InvalidViewTypeException {
        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE))
            listHeroes();

        printMsg(
            ResourceBundle.getBundle("mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale())
            .getString("msgTitleInput"));

        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            ResourceBundle locale = ResourceBundle.getBundle("mrajaona.swingy.locale.GameResource", GameData.getData().getLocale() );
            ConsoleView.println(locale.getString("menuCmds"));
            ConsoleView.println(locale.getString("titleCmds"));
        }
    }

    public static void reloadHeroes() throws LoadHeroListException, SQLException, IOException {
        GUITitle.getScreen().updateHeroList();
    }

    public static Object[] getHeroesList() throws SQLException, IOException {
        return ( SaveManager.getManager().listHeroes().toArray() );
    }

    public static void newHero() throws SQLException, IOException, SwingyException {
        GameModel.changeScreen(GameScreen.NEW);
    }

    public static void loadHero(long id) throws SQLException, IOException, SwingyException {
        GameModel.loadFile(id);
    }

    public static void deleteHero(long id) throws SQLException, IOException {
        GameModel.deleteHero(id);
    }

    public static void waitForInput() throws SQLException, IOException, SwingyException {
        printPrompt();

        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            String[] line;

            line = ConsoleView.getSplitInput();
            TitleScreenController.delocalize(line);
        } else if (GameData.getData().getViewType().equals(ViewTypes.GUI)) {
            ; // GUI waits for user to click somewhere
        } else {
            throw (new InvalidViewTypeException());
        }
    }

    private static void heroStats(HeroData hero) {
        ResourceBundle locale         = ResourceBundle.getBundle( "mrajaona.swingy.locale.StatResource", GameData.getData().getLocale() );
        ResourceBundle heroLocale     = ResourceBundle.getBundle( "mrajaona.swingy.locale.HeroResource", GameData.getData().getLocale() );
        ResourceBundle artifactLocale = ResourceBundle.getBundle( "mrajaona.swingy.locale.ArtifactResource", GameData.getData().getLocale() );

        ConsoleView.println(
            locale.getString("id")            + " : " + Long.toString(hero.getId()) + System.lineSeparator() +
            locale.getString("name")          + " : " + hero.getHeroName() + System.lineSeparator() +
            locale.getString("class")         + " : " + ((ResourceMap) heroLocale.getObject("ClassesList")).get(hero.getHeroClass()) + System.lineSeparator() +
            locale.getString("level")         + " : " + format.format(hero.getExperience()) + " / " + format.format(hero.getLevel() * 1000 + Math.pow( (hero.getLevel() - 1), 2) * 450) + System.lineSeparator() +
            locale.getString("experience")    + " : " + hero.getExperience() + System.lineSeparator() +

            locale.getString("attack")        + " : " + Integer.toString(hero.getAttack())     + System.lineSeparator() +
            locale.getString("defense")       + " : " + Integer.toString(hero.getDefense())    + System.lineSeparator() +
            locale.getString("hitPoints")     + " : " + Integer.toString(hero.getHitPoints())  + " / " + Integer.toString(hero.getMaxHitPoints()) + System.lineSeparator()
        );
    }


    private static void listHeroes() throws SQLException, IOException, LoadHeroListException {
        List<HeroData> heroList = SaveManager.getManager().listHeroes();
        if (heroList == null)
            throw (new LoadHeroListException()); // Empty != null
        for (HeroData hero : heroList) {
            heroStats(hero);
        }
    }

    private static void printConsoleScreen() throws SQLException, IOException, LoadHeroListException  {
        ResourceBundle locale = ResourceBundle.getBundle(
                                    "mrajaona.swingy.locale.InterfaceResource",
                                    GameData.getData().getLocale()
                                    );

        ConsoleView.println(locale.getString("title") + System.lineSeparator());
    }

    public static void reload() throws SQLException, IOException, LoadHeroListException {
        GUITitle.getScreen().initHeroList();
    }

    public static void show() throws SQLException, IOException, InvalidViewTypeException, LoadHeroListException {
        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            Window.getWindow().hide();
            printConsoleScreen();
        } else if (GameData.getData().getViewType().equals(ViewTypes.GUI)) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Window.getWindow().show();
                }
            });
        } else {
            throw (new InvalidViewTypeException());
        }
    }

}
