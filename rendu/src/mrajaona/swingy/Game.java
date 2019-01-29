package mrajaona.swingy;

import java.util.Locale;
import java.util.concurrent.SynchronousQueue;

import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.view.View;

/*
** Main method for the game
*/

public class Game {

    private static Game game = new Game();

    private boolean exit    = false;
    private Thread  gameThread;

    private SynchronousQueue<Runnable> queue;


    private Game() {
        Locale.setDefault(new Locale("en"));
    }

    public static Game getGame() {
        return (game);
    }

    // Game Loop
    Runnable gameLoop = new Runnable() {
        public void run() {
            try {
                while (!exit) {
                    View.waitForInput();
                    consume(queue.take());
                }
            }
            catch (InterruptedException e) {}
            catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    };

    private void consume(Runnable command) {
        command.run();
    }

    public void insertToQueue(Runnable command) {
        queue.offer(command);
    }

    public void exitGame() {
        exit = true;
    }

    public void play(String[] args) {

        if (args.length != 1)
            return ;

        queue = new SynchronousQueue<Runnable>();

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
