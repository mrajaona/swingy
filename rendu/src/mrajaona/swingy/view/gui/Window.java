package mrajaona.swingy.view.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lombok.Getter;
import mrajaona.swingy.util.Util.GameScreen;

public class Window {

    private static Window window   = new Window("Swingy");

    @SuppressWarnings("unused")
    private Window() {}

    public static Window getWindow() {
        return (window);
    }

    private JFrame frame;

    @Getter private JPanel cards;
            private JPanel titlePanel;
            private JPanel newHeroPanel;
            private JPanel mainPanel;
            private JPanel winPanel;
            private JPanel losePanel;


    private Window (String name) {
        {
            frame = new JFrame(name);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(
                400, // width
                300  // height
            );
            frame.setMinimumSize(
                new Dimension(
                    400, // width
                    300  // height
                )
            );
        }

        {
            //Create the "cards".
            titlePanel   = new JPanel(new GridBagLayout());
                GUITitle.getScreen().initPanel(titlePanel);
            newHeroPanel = new JPanel(new GridBagLayout());
                GUINew.getScreen().initPanel(newHeroPanel);
            mainPanel    = new JPanel(new GridBagLayout());
                GUIMain.getScreen().initPanel(mainPanel);
            winPanel     = new JPanel(new GridBagLayout());
            losePanel    = new JPanel(new GridBagLayout());

            //Create the panel that contains the "cards".
            cards = new JPanel(new CardLayout());
            {
                cards.add(titlePanel,   GameScreen.TITLE.toString());
                cards.add(newHeroPanel, GameScreen.NEW.toString());
                cards.add(mainPanel,    GameScreen.MAIN.toString());
                cards.add(winPanel,     GameScreen.WIN.toString());
                cards.add(losePanel,    GameScreen.LOSE.toString());
            }
        }

        frame.setContentPane(cards);
    }

    private void resize(int w, int h) {
        frame.setSize(
            w, // width
            h  // height
        );
        frame.setMinimumSize(
            new Dimension(
                w, // width
                h  // height
            )
        );
    }

    public void show(GameScreen screen) {
        // TODO reset previous screen data
        // Except menu - main

        if (
            screen.equals(GameScreen.MAIN)
            || screen.equals(GameScreen.MENU)
            ) {
            resize(800, 500);
        } else {
            resize(400, 300);
        }

        CardLayout layout = (CardLayout) cards.getLayout();
        layout.show(cards, screen.toString());
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(false);
    }

    public void localize() {
        // TODO
    }

}
