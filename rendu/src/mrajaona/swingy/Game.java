package mrajaona.swingy;

import java.util.Locale;

import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.view.View;

/*
** Main loop for the game
*/

public class Game {

    private static Game loop = new Game();

    private Game() {
    	Locale.setDefault(new Locale("en"));
    }

    public static Game getLoop() {
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

            View.show();
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
