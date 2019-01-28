package mrajaona.swingy;

import java.util.Locale;

import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.view.View;

/*
** Main method for the game
*/

public class Game {

    private static Game game = new Game();

    private boolean waiting = false;
    private Thread  gameThread;

    private Game() {
        Locale.setDefault(new Locale("en"));
    }

    public static Game getGame() {
        return (game);
    }

    Runnable gameLoop = new Runnable() {
        public void run() {
            try {
                while (true) {
                    System.out.println("loop");

                    View.waitForInput();

                    if (waiting) {
                        synchronized (gameThread) {
                            gameThread.wait();
                        }
                    }
                    System.out.println("loop end");

                }
            }
            catch (InterruptedException e) {}
            catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    };

    public void waiting(boolean isWaiting) {
        if (isWaiting) {
            waiting = true;
        } else if (waiting) {
            waiting = false;
            synchronized (gameThread) {
                gameThread.notify();
            }
        }
    }

    public void play(String[] args) {

        if (args.length != 1)
            return ;


        try {
            GameModel.init(new Locale("en"), args[0]);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        gameThread = new Thread(gameLoop);
        gameThread.start();

    }

}
