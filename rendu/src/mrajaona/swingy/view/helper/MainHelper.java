package mrajaona.swingy.view.helper;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.view.console.ConsoleView;

public class MainHelper {

    public static void show(String message) {
        ConsoleView.println(message);
    }

    public static String[] getInput() {
        String   raw   = ask(
        	ResourceBundle.getBundle(
        		"mrajaona.swingy.locale.GameResource",
        		GameData.getData().getLocale() )
        	.getString("msgGetInput")
        	);
        String[] split = raw.split("[ \t\n\r]");
        return (split);
    }

    public static String ask(String message) {
        ConsoleView.println(message);
        return (ConsoleView.getLowerCaseInput());
    }

}
