package mrajaona.swingy.game.view;

public class SwingyConsoleView implements SwingyView {

    private static SwingyConsoleView view = new SwingyConsoleView();

    private SwingyConsoleView() {}

    public static SwingyView getView() {
        return (view);
    }

    @Override
    public void println(String text) {
        System.out.println(text);
    }

    @Override
    public void print(String text) {
        System.out.print(text);
    }

}
