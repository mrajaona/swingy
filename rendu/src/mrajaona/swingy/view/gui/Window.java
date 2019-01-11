package mrajaona.swingy.view.gui;

import javax.swing.JFrame;

import lombok.Getter;

public class Window {

    private static Window window = new Window("Swingy");

    @SuppressWarnings("unused")
    private Window() {}

    public static Window getWindow() {
        return (window);
    }

    @Getter private JFrame frame;

    public Window (String name) {
        frame = new JFrame(name);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(
            400, // width
            500  // height
        );
        frame.setLayout(null);
    }

    public void show() {
        frame.setVisible(true);
    }

}
