package mrajaona.swingy;

import java.util.Locale;

import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.view.View;

/*
** Main method for the game
*/

public class Game {

    private static Game game = new Game();

    private Game() {
        Locale.setDefault(new Locale("en"));
    }

    public static Game getGame() {
        return (game);
    }

    public void play(String[] args) {

        if (args.length != 1)
            return ;

        try {
            GameModel.init(new Locale("en"), args[0]);

            while (true) {
                System.out.println("loop");
                View.waitForInput();
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

}
