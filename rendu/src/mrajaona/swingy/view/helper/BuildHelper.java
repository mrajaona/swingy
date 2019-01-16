package mrajaona.swingy.view.helper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.util.Util;
import mrajaona.swingy.view.console.ConsoleView;
import mrajaona.swingy.view.gui.GUINew;

public class BuildHelper {

    @SuppressWarnings("unused")
    private BuildHelper() {}

    public static String ask(String message) {
        if (GameData.getViewType().equals(Util.ViewTypes.CONSOLE)) {
            ConsoleView.println(message);
            return (ConsoleView.getLowerCaseInput());
        } else if (GameData.getViewType().equals(Util.ViewTypes.GUI)) {
            ;
        } else {
            // Exception
        }
        return (null);
    }

    public static void next() throws SQLException, IOException {
        GameModel.changeScreen(Util.GameScreen.MAIN);
    }

    public static void create(String heroClass, String heroName) throws SQLException, IOException {
        GameModel.createHero(heroClass, heroName);
    }

    public static void show() throws SQLException, IOException {
        if (GameData.getViewType().equals(Util.ViewTypes.CONSOLE)) {
            GameModel.createHero();
        } else if (GameData.getViewType().equals(Util.ViewTypes.GUI)) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    GUINew.createAndShowGUI();
                }
            });
        } else {
            // Exception
        }
    }

}
