package mrajaona.swingy.view.console;

import mrajaona.swingy.data.GameData;

public class ConsoleView {

    // Output

    public static void println(String message) {
        System.out.println(message);
    }

    // Input

    public static String getLowerCaseInput() {
        return (GameData.getData().getInputScanner().nextLine().toLowerCase(GameData.getData().getLocale()));
    }

    public static String[] getSplitInput() {
        String   raw   = getLowerCaseInput();
        String[] split = raw.split("[ \t\n\r]");
        return (split);
    }

}
