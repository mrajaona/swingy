package mrajaona.swingy.view.helper;

import java.io.IOException;
import java.sql.SQLException;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.exception.InvalidViewTypeException;
import mrajaona.swingy.exception.SwingyException;
import mrajaona.swingy.model.GameModel;
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
