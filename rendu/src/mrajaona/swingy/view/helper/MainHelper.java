package mrajaona.swingy.view.helper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import mrajaona.swingy.controller.MainGameController;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.util.Util.GameScreen;
import mrajaona.swingy.util.Util.ViewTypes;
import mrajaona.swingy.view.console.ConsoleView;
import mrajaona.swingy.view.gui.Window;

public class MainHelper {

    @SuppressWarnings("unused")
    private MainHelper() {}


    public static void print(String message) {
        ConsoleView.println(message);
    }

    public static String[] getInput() {
        String   raw   = ask(
        	ResourceBundle.getBundle(
        		"mrajaona.swingy.locale.GameResource",
        		GameData.getData().getLocale() )
        	.getString("msgGetInput")
        	);
        String[] split = raw.split("[ \t\n\r]");
        return (split);
    }

    public static String ask(String message) {
        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            ConsoleView.println(message);
            return (ConsoleView.getLowerCaseInput());
        } else if (GameData.getData().getViewType().equals(ViewTypes.GUI)) {
            ;
        } else {
            // Exception
        }
        return (null);
    }

    public static void show() throws SQLException, IOException {
        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            String[] line;

            while (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) { // TODO
                line = MainHelper.getInput();
                MainGameController.run(line);
            }
        } else if (GameData.getData().getViewType().equals(ViewTypes.GUI)) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Window.getWindow().show(GameScreen.MAIN);
                }
            });
        } else {
            // Exception
        }
    }

}
