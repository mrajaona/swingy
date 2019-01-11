package mrajaona.swingy.view.helper;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.util.Util;
import mrajaona.swingy.view.console.ConsoleView;

public class MainHelper {

    @SuppressWarnings("unused")
    private MainHelper() {}

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
