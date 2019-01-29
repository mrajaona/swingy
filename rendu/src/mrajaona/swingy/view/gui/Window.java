package mrajaona.swingy.view.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import lombok.Getter;
import mrajaona.swingy.controller.MainGameController;
import mrajaona.swingy.controller.MenuController;
import mrajaona.swingy.data.GameData;
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

            private JMenuBar menuBar;
            private JMenu    menu;
            private JMenu    langMenu;

            private JMenuItem saveItem;
            private JMenuItem consoleItem;
            private JMenuItem enItem;
            private JMenuItem frItem;
            private JMenuItem exitItem;

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
            menuBar = new JMenuBar();
            menu = new JMenu();

            // Menu items

            {
                saveItem = new JMenuItem();
                saveItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            String[] args = {
                                "save"
                            };
                            MainGameController.run(args);
                        }  catch (Exception e) {
                            e.printStackTrace();
                            System.exit(1);
                        }
                    }
                });
                menu.add(saveItem);
            }

            {
                consoleItem = new JMenuItem();
                consoleItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            String[] args = {
                                "console"
                            };
                            MenuController.run(args);
                        }  catch (Exception e) {
                            e.printStackTrace();
                            System.exit(1);
                        }
                    }
                });
                menu.add(consoleItem);
            }

            {
                // Language submenu // TODO : disable selected
                menu.addSeparator();
                langMenu = new JMenu();

                {
                    enItem = new JMenuItem();
                    enItem.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            try {
                                String[] args = {
                                    "language",
                                    "en"
                                };
                                MenuController.run(args);
                            }  catch (Exception e) {
                                e.printStackTrace();
                                System.exit(1);
                            }
                        }
                    });
                    langMenu.add(enItem);
                }

                {
                    frItem = new JMenuItem();
                    frItem.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            try {
                                String[] args = {
                                    "language",
                                    "fr"
                                };
                                MenuController.run(args);
                            }  catch (Exception e) {
                                e.printStackTrace();
                                System.exit(1);
                            }
                        }
                    });
                    langMenu.add(frItem);
                }

                menu.add(langMenu);
            }

            {
                exitItem = new JMenuItem();
                exitItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            String[] args = {
                                "exit"
                            };
                            MenuController.run(args);
                        }  catch (Exception e) {
                            e.printStackTrace();
                            System.exit(1);
                        }
                    }
                });
                menu.add(exitItem);
            }

            menuBar.add(menu);
            frame.setJMenuBar(menuBar);
        }

        {
            // Create the "cards".
            titlePanel   = new JPanel(new GridBagLayout());
                GUITitle.getScreen().initPanel(titlePanel);
            newHeroPanel = new JPanel(new GridBagLayout());
                GUINew.getScreen().initPanel(newHeroPanel);
            mainPanel    = new JPanel(new GridBagLayout());
                GUIMain.getScreen().initPanel(mainPanel);
            winPanel     = new JPanel(new GridBagLayout());
                GUIWin.getScreen().initPanel(winPanel);
            losePanel    = new JPanel(new GridBagLayout());
                GUIGameOver.getScreen().initPanel(losePanel);

            // Create the panel that contains the "cards".
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
        localize();
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

    public void resize() {
        GameScreen screen = GameData.getData().getScreen();

        if (screen.equals(GameScreen.MAIN)) {
            resize(800, 500);
        } else {
            resize(400, 300);
        }
    }

    public void show() {
        GameScreen screen = GameData.getData().getScreen();

        CardLayout layout = (CardLayout) cards.getLayout();
        layout.show(cards, screen.toString());
        resize();

        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(false);
    }

    public void close() {
        frame.dispose();
    }

    public void localize() {
        GUITitle.getScreen().localize();
        GUINew.getScreen().localize();
        GUIMain.getScreen().localize();
        GUIWin.getScreen().localize();
        GUIGameOver.getScreen().localize();

        ResourceBundle locale = ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() );

        menu.setText(locale.getString("menu"));

        saveItem.setText(locale.getString("saveItem"));
        consoleItem.setText(locale.getString("consoleItem"));
        exitItem.setText(locale.getString("exitItem"));

        langMenu.setText(locale.getString("langMenu"));

        enItem.setText(locale.getString("enItem"));
        frItem.setText(locale.getString("frItem"));
    }

    public void updateMenu() {
        saveItem.setVisible( GameData.getData().getScreen() == GameScreen.MAIN ? true : false );
        saveItem.setEnabled( GameData.getData().getScreen() == GameScreen.MAIN ? true : false );
    }

    public void update() {
        updateMenu();
        localize();
    }

}
