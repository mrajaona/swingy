package mrajaona.swingy.view.helper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import mrajaona.swingy.controller.WinController;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.util.Util.GameScreen;
import mrajaona.swingy.util.Util.ViewTypes;
import mrajaona.swingy.view.console.ConsoleView;
import mrajaona.swingy.view.gui.GUIWin;
import mrajaona.swingy.view.gui.Window;

public class WinHelper {
    @SuppressWarnings("unused")
    private WinHelper() {}

    public static void printMsg(String message) {
        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            ConsoleView.println(message);
        } else if (GameData.getData().getViewType().equals(ViewTypes.GUI)) {
            ;
        } else {
            // TODO : Exception
        }
    }

    public static void waitForInput() throws SQLException, IOException {
        printMsg(
                ResourceBundle.getBundle(
                    "mrajaona.swingy.locale.GameResource",
                    GameData.getData().getLocale() )
                .getString("msgGetInput")
                );

        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            String[] line;

            line = ConsoleView.getSplitInput();
            WinController.run(line);

            waitForInput();

        } else if (GameData.getData().getViewType().equals(ViewTypes.GUI)) {
            ; // GUI waits for user to click somewhere
        } else {
            // TODO : Exception
        }
    }

    public static void show() throws SQLException, IOException {
        GUIWin.getScreen().localize();

        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            Window.getWindow().hide();

            // TODO
            printMsg("You win !");

        } else if (GameData.getData().getViewType().equals(ViewTypes.GUI)) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Window.getWindow().show();
                }
            });
        } else {
            // TODO : Exception
        }

        waitForInput();

    }

}
