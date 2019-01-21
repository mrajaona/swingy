package mrajaona.swingy.view.helper;

import java.io.IOException;
import java.sql.SQLException;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.util.Util.GameScreen;
import mrajaona.swingy.util.Util.ViewTypes;
import mrajaona.swingy.view.console.ConsoleView;
import mrajaona.swingy.view.gui.Window;

public class BuildHelper {

    @SuppressWarnings("unused")
    private BuildHelper() {}

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

    public static void prev() throws SQLException, IOException {
        GameModel.changeScreen(GameScreen.TITLE);
    }

    public static void show() throws SQLException, IOException {
        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            GameModel.createHero();
        } else if (GameData.getData().getViewType().equals(ViewTypes.GUI)) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Window.getWindow().show(GameScreen.NEW);
                }
            });
        } else {
            // Exception
        }
    }

}
