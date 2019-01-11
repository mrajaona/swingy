package mrajaona.swingy.view.helper;

import java.util.Scanner;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.util.Util;
import mrajaona.swingy.view.console.ConsoleView;

public class BuildHelper {

    @SuppressWarnings("unused")
    private BuildHelper() {}

    public static String ask(String message) {
        if (GameData.getViewType().equals(Util.VIEW_TYPE_CONSOLE)) {
            ConsoleView.println(message);
            return (ConsoleView.getLowerCaseInput());
        } else if (GameData.getViewType().equals(Util.VIEW_TYPE_GUI)) {
            ;
        } else {
            // Exception
        }
        return (null);
    }

}
