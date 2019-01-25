package mrajaona.swingy.view.helper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import mrajaona.swingy.controller.GameOverController;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.util.Util.ViewTypes;
import mrajaona.swingy.view.console.ConsoleView;
import mrajaona.swingy.view.gui.GUIGameOver;
import mrajaona.swingy.view.gui.Window;

public class GameOverHelper {

    @SuppressWarnings("unused")
    private GameOverHelper() {}

    public static void printMsg(String message) {
        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            ResourceBundle locale = ResourceBundle.getBundle("mrajaona.swingy.locale.GameResource", GameData.getData().getLocale() );
            ConsoleView.println(message);
            ConsoleView.println(locale.getString("menuCmds"));
            ConsoleView.println(locale.getString("gameOverCmds"));
        } else if (GameData.getData().getViewType().equals(ViewTypes.GUI)) {
            ;
        } else {
            // Exception
        }
    }

    public static void waitForInput() throws SQLException, IOException {
        printMsg(
                ResourceBundle.getBundle(
                    "mrajaona.swingy.locale.InterfaceResource",
                    GameData.getData().getLocale() )
                .getString("msgGetInput")
                );

        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            String[] line;

            line = ConsoleView.getSplitInput();
            GameOverController.run(line);

            waitForInput();

        } else if (GameData.getData().getViewType().equals(ViewTypes.GUI)) {
            ; // GUI waits for user to click somewhere
        } else {
            // Exception
        }
    }

    private static void printConsoleScreen() {
        ResourceBundle locale = ResourceBundle.getBundle(
                                    "mrajaona.swingy.locale.InterfaceResource",
                                    GameData.getData().getLocale()
                                    );

        ConsoleView.println(locale.getString("loseLabel"));
    }

    public static void show() throws SQLException, IOException {
        GUIGameOver.getScreen().localize();

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
            // Exception
        }

        waitForInput();

    }
}
