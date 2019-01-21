package mrajaona.swingy.view.helper;

import java.io.IOException;
import java.sql.SQLException;

import mrajaona.swingy.controller.TitleScreenController;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.util.SaveManager;
import mrajaona.swingy.util.Util.GameScreen;
import mrajaona.swingy.util.Util.ViewTypes;
import mrajaona.swingy.view.console.ConsoleView;
import mrajaona.swingy.view.gui.Window;

public class TitleHelper {

    @SuppressWarnings("unused")
    private TitleHelper() {}

    public static Object[] getHeroesList() throws SQLException, IOException {
        return ( SaveManager.getManager().listHeroes().toArray() );
    }

    public static void newHero() throws SQLException, IOException {
        GameModel.changeScreen(GameScreen.NEW);
    }

    public static void loadHero(long id) throws SQLException, IOException {
        GameModel.loadFile(id);
    }

    public static void deleteHero(long id) throws SQLException, IOException  {
        GameModel.deleteHero(id);
    }

    public static void show() throws SQLException, IOException {
        if (GameData.getData().getViewType().equals(ViewTypes.CONSOLE)) {
            String[] line;

            ConsoleView.println("< SWINGY >"); // Title // TODO

            while (
                GameData.getData().getViewType().equals(ViewTypes.CONSOLE)
                && GameData.getData().getHero() == null
                ) {
                line = MainHelper.getInput();
                TitleScreenController.run(line);
                // TODO : check view type change
            }
        } else if (GameData.getData().getViewType().equals(ViewTypes.GUI)) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Window.getWindow().show(GameScreen.TITLE);
                }
            });
        } else {
            // TODO : Exception
        }
    }

}
