package mrajaona.swingy.view;

import java.io.IOException;
import java.sql.SQLException;

import mrajaona.swingy.util.Util.GameScreen;
import mrajaona.swingy.view.gui.Window;
import mrajaona.swingy.view.helper.BuildHelper;
import mrajaona.swingy.view.helper.MainHelper;
import mrajaona.swingy.view.helper.TitleHelper;
import mrajaona.swingy.view.helper.WinHelper;
import mrajaona.swingy.view.helper.GameOverHelper;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.exception.InvalidScreenException;
import mrajaona.swingy.exception.SwingyException;

public class View {
    @SuppressWarnings("unused")
    private View() {}

    public static void show() throws SQLException, IOException, SwingyException {
        GameScreen screen = GameData.getData().getScreen();

        Window.getWindow().updateMenu();
        Window.getWindow().resize();

        switch (screen) {
            case TITLE :
                TitleHelper.show();
                break;
            case NEW :
                BuildHelper.show();
                break;
            case MAIN :
                MainHelper.show();
                break;
            case WIN :
                WinHelper.show();
                break;
            case LOSE :
                GameOverHelper.show();
                break;
            default :
                throw (new InvalidScreenException());
        }
    }

    public static void update() {
        Window.getWindow().update();

    }

    public static void waitForInput() throws SQLException, IOException, SwingyException {
        GameScreen screen = GameData.getData().getScreen();

        switch (screen) {
            case TITLE :
                TitleHelper.waitForInput();
                break;
            case NEW :
                BuildHelper.waitForInput();
                break;
            case MAIN :
                MainHelper.waitForInput();
                break;
            case WIN :
                WinHelper.waitForInput();
                break;
            case LOSE :
                GameOverHelper.waitForInput();
                break;
            default :
                throw (new InvalidScreenException());
        }
    }

}
