package mrajaona.swingy.view.helper;

import java.util.Scanner;

import mrajaona.swingy.view.console.ConsoleView;

public class BuildHelper {

    public static String ask(String message) {
        ConsoleView.println(message);
        return (ConsoleView.getLowerCaseInput());
    }

}
