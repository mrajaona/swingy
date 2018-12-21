package mrajaona.swingy;

import lombok.Getter;
import lombok.Setter;
import mrajaona.swingy.controller.TestController;
import mrajaona.swingy.data.character.EnemyData;
import mrajaona.swingy.data.character.HeroData;
import mrajaona.swingy.model.GameModel;

/*
** Main loop for the game
*/

public class GameLoop {

    private static GameLoop loop = new GameLoop();

    private GameLoop() {}

    public static GameLoop getLoop() {
        return (loop);
    }

    public void game(String[] args) {

        if (args.length != 1)
            return ;

        try {

            GameModel.getModel().changeViewType(args[0]);

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
