package mrajaona.swingy;

import java.util.Locale;

import mrajaona.swingy.controller.MainGameController;
import mrajaona.swingy.controller.TitleScreenController;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.view.helper.MainHelper;

/*
** Main loop for the game
*/

public class GameLoop {

    private static GameLoop loop = new GameLoop();

    private GameLoop() {
    	Locale.setDefault(new Locale("en"));
    }

    public static GameLoop getLoop() {
        return (loop);
    }

    public void game(String[] args) {

        if (args.length != 1)
            return ;

        try {

            GameModel.changeViewType(args[0]);

            // "en", "fr"
            GameModel.setLocale(new Locale("en"));

            System.out.println("Hello Maven!" + System.lineSeparator() + "Welcome to Swingy!");

            String[] line;
            while (GameData.getHero() == null) {
                MainHelper.show("Title screen"); // Debug // TODO
                line = MainHelper.getInput();
                TitleScreenController.run(line);
            }
            while (true) { // exit
                line = MainHelper.getInput();
                MainGameController.run(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
