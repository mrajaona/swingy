package mrajaona.swingy.view.helper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import mrajaona.swingy.controller.BattleController;
import mrajaona.swingy.controller.LootController;
import mrajaona.swingy.controller.MainGameController;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.util.Util.GameScreen;
import mrajaona.swingy.util.Util.SubScreen;
import mrajaona.swingy.util.Util.ViewTypes;
import mrajaona.swingy.view.console.ConsoleView;
import mrajaona.swingy.view.gui.GUIMain;
import mrajaona.swingy.view.gui.Window;

public class MainHelper {

    @SuppressWarnings("unused")
    private MainHelper() {}

    public static void printMsg(String message) {
        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            ConsoleView.println(message);
        } else if (GameData.getData().getViewType().equals(ViewTypes.GUI)) {
            GUIMain.getScreen().log(message);
        } else {
            // Exception
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

            if (GameData.getData().getEnemy() != null)
                BattleController.run(line);
            else if (GameData.getData().getArtifact() != null)
                LootController.run(line);
            else
                MainGameController.run(line);

            waitForInput();

        } else if (GameData.getData().getViewType().equals(ViewTypes.GUI)) {
            ; // GUI waits for user to click somewhere
        } else {
            // Exception
        }
    }

    public static void clean() {
        GUIMain.getScreen().reset();
    }

    public static void changeSubScreen() {
        if (GameData.getData().getEnemy() != null)
            GUIMain.getScreen().show(SubScreen.BATTLE);
        else if (GameData.getData().getArtifact() != null)
            GUIMain.getScreen().show(SubScreen.LOOT);
        else
            GUIMain.getScreen().show(SubScreen.MAIN);
    }

    public static void show() throws SQLException, IOException {
        changeSubScreen();
        GUIMain.getScreen().updateTable();

        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            Window.getWindow().hide();
        } else if (GameData.getData().getViewType().equals(ViewTypes.GUI)) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Window.getWindow().show();
                    GUIMain.getScreen().resetDividers();
                }
            });
        } else {
            // Exception
        }

        waitForInput();

    }

}
