package mrajaona.swingy.game.view;

public class SwingyGUIView implements SwingyView {

    private static SwingyGUIView view = new SwingyGUIView();

    private SwingyGUIView() {}

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
