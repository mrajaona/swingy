package mrajaona.swingy.view;

import java.io.IOException;
import java.sql.SQLException;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.util.Util;
import mrajaona.swingy.view.helper.MainHelper;
import mrajaona.swingy.view.helper.TitleHelper;

public class View {
	@SuppressWarnings("unused")
	private View() {}

    public static void show() throws SQLException, IOException {
        Util.GameScreen screen = GameData.getScreen();
        switch (screen) {
            case TITLE :
                TitleHelper.show();
                break;
            case MAIN :
                MainHelper.show();
                break;
            /*
            case MENU :
                MenuHelper.show();
                break;
            */
            /*
            case WIN :
                WinHelper.show();
                break;
            */
            /*
            case LOSE :
                LoseHelper.show();
                break;
            */
            default :
                // exception
                break;
        }
    }
}
