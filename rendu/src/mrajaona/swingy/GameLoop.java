package mrajaona.swingy;

import java.util.Locale;

import mrajaona.swingy.controller.MainGameController;
import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.view.helper.MainHelper;
import mrajaona.swingy.view.helper.TitleHelper;

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

            // TODO
            // "en", "fr"
            GameModel.setLocale(new Locale("en"));

            TitleHelper.show();
/*
            String[] line;
            while (true) { // exit
                line = MainHelper.getInput();
                MainGameController.run(line);
            }
*/
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

}
