package mrajaona.swingy;

import java.util.Scanner;

import lombok.Getter;
import lombok.Setter;
import mrajaona.swingy.controller.TestController;
import mrajaona.swingy.data.character.EnemyData;
import mrajaona.swingy.data.character.HeroData;

/*
** Main loop for the game
*/

public class GameLoop {

    private static GameLoop loop = new GameLoop();

    private GameLoop() {}

    public static GameLoop getLoop() {
        return (loop);
    }

    public static final String VIEW_TYPE_GUI     = "gui";
    public static final String VIEW_TYPE_CONSOLE = "console";
    public static enum ViewType {
        VIEW_TYPE_GUI,
        VIEW_TYPE_CONSOLE
    }

    public static ViewType viewType = ViewType.VIEW_TYPE_CONSOLE;

    @Getter @Setter public static HeroData hero;
    // current enemy
    @Getter @Setter public static EnemyData enemy;
    // all enemies on map
    // TODO : map

    @Getter public static Scanner inputScanner = new Scanner(System.in);

    public void game(String[] args) {

        if (args.length != 1)
            return ;

        try {

            switch(args[0]) {
                case VIEW_TYPE_GUI :
                    viewType = ViewType.VIEW_TYPE_GUI;
                    break;
                case VIEW_TYPE_CONSOLE :
                    viewType = ViewType.VIEW_TYPE_CONSOLE;
                    break;
                default :
                    // Exception
                    break;
            }

            System.out.println("Hello Maven!" + System.lineSeparator() + "Welcome to Swingy!");

            // TestController.getController().newHero();
            TestController.getController().loadHero(1);
            TestController.getController().printHero();
            // TestController.getController().removeWeapon();
            // TestController.getController().printHero();
            TestController.getController().saveHero();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
