package mrajaona.swingy.view.helper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import mrajaona.swingy.controller.TitleScreenController;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.util.SaveManager;
import mrajaona.swingy.util.Util.GameScreen;
import mrajaona.swingy.util.Util.ViewTypes;
import mrajaona.swingy.view.console.ConsoleView;
import mrajaona.swingy.view.gui.GUITitle;
import mrajaona.swingy.view.gui.Window;

public class TitleHelper {

    @SuppressWarnings("unused")
    private TitleHelper() {}

    public static void printMsg(String message) {
        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            ConsoleView.println(message);
        } else if (GameData.getData().getViewType().equals(ViewTypes.GUI)) {
            ; // TODO : modal
        } else {
            // Exception
        }
    }

    public static Object[] getHeroesList() throws SQLException, IOException {
        return ( SaveManager.getManager().listHeroes().toArray() );
    }

    public static void newHero() throws SQLException, IOException {
        GameModel.changeScreen(GameScreen.NEW);
    }

    public static void loadHero(long id) throws SQLException, IOException {
        GameModel.loadFile(id);
    }

    public static void deleteHero(long id) throws SQLException, IOException {
        GameModel.deleteHero(id);
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

            while (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
                line = ConsoleView.getSplitInput();
                TitleScreenController.run(line);
            }
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

        ConsoleView.println(locale.getString("title"));

        // TODO : list for console view // Mandatory

    }

    public static void reload() throws SQLException, IOException {
        GUITitle.getScreen().initHeroList();
    }

    public static void show() throws SQLException, IOException {
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
