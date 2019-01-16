package mrajaona.swingy.view.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lombok.Getter;
import mrajaona.swingy.util.Util;

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
			private JPanel menuPanel;
			private JPanel winPanel;
			private JPanel losePanel;


    private Window (String name) {
    	{
	        frame = new JFrame(name);

	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(
	            600, // width
	            500  // height
	        );
	        frame.setMinimumSize(
	    		new Dimension(
	        		600, // width
	            	500  // height
	            )
	    	);
		}

		{
            //Create the "cards".
			titlePanel   = new JPanel(new GridBagLayout());
				GUITitle.initPanel(titlePanel);
			newHeroPanel = new JPanel(new GridBagLayout());
				GUINew.initPanel(newHeroPanel);
			mainPanel    = new JPanel(new GridBagLayout());
			menuPanel    = new JPanel(new GridBagLayout());
			winPanel     = new JPanel(new GridBagLayout());
			losePanel    = new JPanel(new GridBagLayout());

            //Create the panel that contains the "cards".
	        cards = new JPanel(new CardLayout());
	        {
				cards.add(titlePanel,   Util.GameScreen.TITLE.toString());
				cards.add(newHeroPanel, Util.GameScreen.NEW.toString());
				cards.add(mainPanel,    Util.GameScreen.MAIN.toString());
				cards.add(menuPanel,    Util.GameScreen.MENU.toString());
				cards.add(winPanel,     Util.GameScreen.WIN.toString());
				cards.add(losePanel,    Util.GameScreen.LOSE.toString());
	    	}
    	}

    	frame.setContentPane(cards);
    }

    public void show(Util.GameScreen screen) {
    	// TODO reset previous screen
    	// Except for Main / Menu

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
