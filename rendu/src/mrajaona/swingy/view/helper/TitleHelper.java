package mrajaona.swingy.view.helper;

import java.io.IOException;
import java.sql.SQLException;

import mrajaona.swingy.controller.TitleScreenController;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.util.SaveManager;
import mrajaona.swingy.util.Util;
import mrajaona.swingy.view.console.ConsoleView;
import mrajaona.swingy.view.gui.Window;

public class TitleHelper {

    @SuppressWarnings("unused")
    private TitleHelper() {}

    public static Object[] getHeroesList() throws SQLException, IOException {
        return ( SaveManager.getManager().listHeroes().toArray() );
    }

    public static void newHero() throws SQLException, IOException {
        GameModel.changeScreen(Util.GameScreen.NEW);
    }

    public static void loadHero(long id) throws SQLException, IOException {
        GameModel.loadHero(id);
    }

    public static void deleteHero(long id) throws SQLException, IOException  {
        GameModel.deleteHero(id);
    }

    public static void show() throws SQLException, IOException {
        if (GameData.getViewType().equals(Util.ViewTypes.CONSOLE)) {
            String[] line;

            ConsoleView.println("< SWINGY >"); // Title // TODO

            while (GameData.getHero() == null) {
                line = MainHelper.getInput();
                TitleScreenController.run(line);
                // TODO : check view type change
            }
        } else if (GameData.getViewType().equals(Util.ViewTypes.GUI)) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Window.getWindow().show(Util.GameScreen.TITLE);
                }
            });
        } else {
            // Exception
        }
    }

}
