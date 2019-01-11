package mrajaona.swingy.view.console;

import mrajaona.swingy.data.GameData;

public class ConsoleView {

	// Output

	public static void println(String message) {
		System.out.println(message);
	}

	public static void print(String message) {
		System.out.print(message);
	}

	// Input

	public static String getRawInput() {
		return (GameData.getInputScanner().nextLine());
	}

	public static String getLowerCaseInput() {
		return (GameData.getInputScanner().nextLine().toLowerCase(GameData.getLocale()));
	}

}
