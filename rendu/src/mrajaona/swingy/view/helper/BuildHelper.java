package mrajaona.swingy.view.helper;

import java.util.Scanner;

import mrajaona.swingy.GameLoop;
import mrajaona.swingy.view.console.ConsoleView;

public class BuildHelper {

    public static String ask(String message) {
        ConsoleView.println(message);
        String answer = GameLoop.getInputScanner().nextLine();
        return (answer);
    }

}
