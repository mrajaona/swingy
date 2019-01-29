package mrajaona.swingy;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.concurrent.SynchronousQueue;

import mrajaona.swingy.exception.SwingyException;
import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.view.View;

/*
** Main method for the game
*/

public class Game {

    private static Game game = new Game();

    private boolean exit      = false;
    private SynchronousQueue<Runnable> queue;

    private Thread waiterThread;
    private Runnable waiter = new Runnable() {
        public void run() {

            while (!exit) {
                synchronized (lock) {
                    try {
                        View.waitForInput();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                }
            }

        }
    };

    private static Object lock = new Object();

    private Game() {
        Locale.setDefault(new Locale("en"));
    }

    public static Game getGame() {
        return (game);
    }

    private void consume(Runnable command) {
        synchronized (lock) {
            command.run();
            lock.notify();
        }
    }

    public void insertToQueue(Runnable command) {
        try {
            queue.put(command);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

            waiterThread = new Thread(waiter, "waiter");
            waiterThread.start();

            while (!exit) {
                consume(queue.take());
            }

        }
        catch (InterruptedException e) {}
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

}
