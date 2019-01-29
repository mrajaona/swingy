package mrajaona.swingy.view.helper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import mrajaona.swingy.controller.WinController;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.exception.InvalidViewTypeException;
import mrajaona.swingy.exception.SwingyException;
import mrajaona.swingy.util.Coord;
import mrajaona.swingy.util.Util.ViewTypes;
import mrajaona.swingy.view.console.ConsoleView;
import mrajaona.swingy.view.gui.GUIWin;
import mrajaona.swingy.view.gui.Window;

public class WinHelper {

    @SuppressWarnings("unused")
    private WinHelper() {}

    public static void printMsg(String message) throws InvalidViewTypeException {
        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            ConsoleView.println(message);
        } else if (GameData.getData().getViewType().equals(ViewTypes.GUI)) {
            ;
        } else {
            throw (new InvalidViewTypeException());
        }
    }

    public static void printPrompt() throws InvalidViewTypeException {
        Coord heroCoord = GameData.getData().getMap().getHeroCoord();
        printMsg(
            String.format(
                ResourceBundle.getBundle("mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale())
                    .getString("msgTitleInput"),
                    heroCoord.getX(), // %1$d
                    heroCoord.getY() // %2$d
            )
        );

        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            ResourceBundle locale = ResourceBundle.getBundle("mrajaona.swingy.locale.GameResource", GameData.getData().getLocale() );
            ConsoleView.println(locale.getString("menuCmds"));
            ConsoleView.println(locale.getString("winCmds"));
        }
    }

    public static void waitForInput() throws SQLException, IOException, SwingyException {
        printPrompt();

        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            String[] line;

            line = ConsoleView.getSplitInput();
            WinController.delocalize(line);

        } else if (GameData.getData().getViewType().equals(ViewTypes.GUI)) {
            mrajaona.swingy.Game.getGame().waiting(true); // GUI waits for user to click somewhere
        } else {
            throw (new InvalidViewTypeException());
        }
    }

    private static void printConsoleScreen() {
        ResourceBundle locale = ResourceBundle.getBundle(
                                    "mrajaona.swingy.locale.InterfaceResource",
                                    GameData.getData().getLocale()
                                    );

        ConsoleView.println(locale.getString("winLabel"));
    }

    public static void show() throws SQLException, IOException, InvalidViewTypeException {
        GUIWin.getScreen().localize();

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
