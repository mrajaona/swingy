package mrajaona.swingy.view.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GUITitle {

    private static GUITitle screen = new GUITitle();

    @SuppressWarnings("unused")
    private GUITitle() {
        controlPanel = new JPanel();
        newButton = new JButton("New game");
        loadButton = new JButton("Load game");

        controlPanel.setLayout(new FlowLayout());

        controlPanel.add(newButton);
        controlPanel.add(loadButton);
    }

    public static GUITitle getScreen() {
        return (screen);
    }

    private static JPanel controlPanel;
    private static JButton newButton;
    private static JButton loadButton;

    public static void createAndShowGUI() {
        /*
        Window.getWindow().getFrame().removeAll();
        Window.getWindow().getFrame().revalidate();
        Window.getWindow().getFrame().repaint();
        */

        Window.getWindow().getFrame().setLayout(new GridLayout(3, 1));

        Window.getWindow().getFrame().add(controlPanel);
        Window.getWindow().show();
    }

}
