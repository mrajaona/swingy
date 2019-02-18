package mrajaona.swingy.view.console;

import java.util.Scanner;

import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.data.GameData;

public class ConsoleView {

    // Output

    public static void println(String message) {
        System.out.println(message);
    }

    // Input

    public static String getLowerCaseInput() {
        Scanner scan = GameData.getData().getInputScanner();

        if (!scan.hasNextLine()) { // ctrl-d
            GameModel.exitGame();
            return ("");
        }


        return (scan.nextLine().toLowerCase(GameData.getData().getLocale()));
    }

    public static String[] getSplitInput() {
        String   raw   = getLowerCaseInput();
        String[] split = raw.split("[ \t\n\r]");
        return (split);
    }

}
