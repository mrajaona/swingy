package mrajaona.swingy.view.helper;

import java.util.Scanner;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.view.console.ConsoleView;

public class BuildHelper {

    public static String ask(String message) {
        ConsoleView.println(message);
        String answer = GameData.getInputScanner().nextLine();
        return (answer);
    }

}
