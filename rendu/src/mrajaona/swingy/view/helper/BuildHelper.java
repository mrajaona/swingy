package mrajaona.swingy.view.helper;

import java.util.Scanner;

import mrajaona.swingy.view.console.ConsoleView;

public class BuildHelper {

    public static String ask(String message) {
        Scanner inputScanner = new Scanner(System.in);
        ConsoleView.println(message);

        String answer = inputScanner.nextLine();

        inputScanner.close();

        return (answer);
    }

}
