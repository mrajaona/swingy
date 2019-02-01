package mrajaona.swingy.view.helper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.exception.InvalidViewTypeException;
import mrajaona.swingy.exception.SwingyException;
import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.util.ResourceMap;
import mrajaona.swingy.util.Util;
import mrajaona.swingy.util.Util.GameScreen;
import mrajaona.swingy.util.Util.ViewTypes;
import mrajaona.swingy.view.console.ConsoleView;
import mrajaona.swingy.view.gui.Window;

public class BuildHelper {

    @SuppressWarnings("unused")
    private BuildHelper() {}

    public static void printMsg(String message) throws InvalidViewTypeException {
        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            ConsoleView.println(message);
        } else if (GameData.getData().getViewType().equals(ViewTypes.GUI)) {
            ; // No place to print
        } else {
            throw (new InvalidViewTypeException());
        }
    }

    public static String ask(String message) throws InvalidViewTypeException {
        printMsg(message);

        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            return (ConsoleView.getLowerCaseInput());
        } else if (GameData.getData().getViewType().equals(ViewTypes.GUI)) {
            ;
        } else {
            throw (new InvalidViewTypeException());
        }
        return (null);
    }

    public static void listClasses() throws InvalidViewTypeException {
        List<String> typeList = Arrays.asList(Util.heroTypes);
        ResourceBundle locale = ResourceBundle.getBundle( "mrajaona.swingy.locale.HeroResource", GameData.getData().getLocale() );
        printMsg(locale.getString("msgListClasses"));
        String msg;
        Util.HeroBaseStats stats;

        for (String type : typeList) {
            stats = Util.HERO_BASE_STATS_MAP.get(type);
            msg = String.format(
                locale.getString("msgClass"),
                ((ResourceMap) locale.getObject("ClassesList")).get(type), // %1$s
                stats.getAtk() * 5,
                stats.getDef() * 5,
                stats.getHp() * 25
            );
            printMsg(msg);
        }

        printMsg(locale.getString("msgLevel"));

    }

    public static void waitForInput() throws SQLException, IOException, SwingyException {
        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            ;
        } else if (GameData.getData().getViewType().equals(ViewTypes.GUI)) {
            ;
        } else {
            throw (new InvalidViewTypeException());
        }
    }

    public static void prev() throws SQLException, IOException, SwingyException {
        GameModel.changeScreen(GameScreen.TITLE);
    }

    public static void show() throws SQLException, IOException, SwingyException {
        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            Window.getWindow().hide();
            GameModel.createHero();
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
