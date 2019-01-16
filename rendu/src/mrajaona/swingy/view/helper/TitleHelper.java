package mrajaona.swingy.view.helper;

import java.io.IOException;
import java.sql.SQLException;

import mrajaona.swingy.controller.TitleScreenController;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.util.Util;
import mrajaona.swingy.view.console.ConsoleView;
import mrajaona.swingy.view.gui.GUITitle;

public class TitleHelper {

    @SuppressWarnings("unused")
    private TitleHelper() {}

    public static void newHero() {
        System.out.println("new");
        /*
        GameModel.createHero();
        // TODO : StartGame
        */
    }

    public static void loadHero(long id) {
        System.out.println("load " + id);
        /*
        GameModel.loadHero(id);
        // TODO : Start game
        */
    }

    public static void deleteHero(long id) {
        System.out.println("delete " + id);
        /*
        GameModel.deleteHero(id);
        */
    }

    public static void show()  throws SQLException, IOException {
        if (GameData.getViewType().equals(Util.ViewTypes.CONSOLE)) {
            String[] line;

            ConsoleView.println("Title"); // Debug // TODO

            while (GameData.getHero() == null) {
                line = MainHelper.getInput();
                TitleScreenController.run(line);
            }
        } else if (GameData.getViewType().equals(Util.ViewTypes.GUI)) {
            //Schedule a job for the event-dispatching thread:
            //creating and showing this application's GUI.
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    GUITitle.createAndShowGUI();
                }
            });
        } else {
            // Exception
        }
    }

}
