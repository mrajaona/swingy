package mrajaona.swingy.view.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mrajaona.swingy.controller.WinController;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.character.HeroData;
import mrajaona.swingy.util.ResourceMap;

public class GUIWin {

    private static GUIWin screen = new GUIWin();

    private JPanel winPanel;
    private JLabel winLabel;
    private JLabel autoSaveLabel;

    private JPanel heroPanel;
    private JLabel heroLabel;
    private JLabel lvlLabel;

    private JPanel buttonPanel;
    private JButton titleButton;
    private JButton continueButton;

    private JPanel mainPanel;


    private GUIWin() {
        try {

            // win text panel
            {
                winPanel = new JPanel(new FlowLayout());

                winLabel = new JLabel();
                winLabel.setHorizontalAlignment(JLabel.CENTER);
                winLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                autoSaveLabel = new JLabel();
                autoSaveLabel.setHorizontalAlignment(JLabel.CENTER);
                autoSaveLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                winPanel.add(winLabel);
                winPanel.add(autoSaveLabel);
            }

            // hero info panel
            {
                heroPanel = new JPanel();
                heroPanel.setLayout(
                    new BoxLayout(
                        heroPanel,
                        BoxLayout.Y_AXIS
                        )
                    );
                {
                    heroLabel = new JLabel();
                    heroLabel.setHorizontalAlignment(JLabel.CENTER);
                    heroLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                }
                {
                    lvlLabel = new JLabel();
                    lvlLabel.setHorizontalAlignment(JLabel.CENTER);
                    lvlLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                }

                heroPanel.add(Box.createVerticalGlue());
                heroPanel.add(heroLabel);
                heroPanel.add(lvlLabel);
                heroPanel.add(Box.createVerticalGlue());
            }

            // button panel
            {
                buttonPanel   = new JPanel();
                buttonPanel.setLayout(
                    new BoxLayout(
                        buttonPanel,
                        BoxLayout.X_AXIS
                        )
                    );

                // return to title button
                {
                    titleButton = new JButton();
                    titleButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            try {
                                String[] args = {
                                    "title"
                                };
                                WinController.run(args);
                            }  catch (Exception e) {
                                e.printStackTrace();
                                System.exit(1);
                            }
                        }
                    });
                    titleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                    titleButton.setAlignmentY(Component.CENTER_ALIGNMENT);
                }
                // start new map button
                {
                    continueButton = new JButton();
                    continueButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            try {
                                String[] args = {
                                    "continue"
                                };
                                WinController.run(args);
                            }  catch (Exception e) {
                                e.printStackTrace();
                                System.exit(1);
                            }
                        }
                    });
                    continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                    continueButton.setAlignmentY(Component.CENTER_ALIGNMENT);
                }

                buttonPanel.add(Box.createHorizontalGlue());
                buttonPanel.add(titleButton);
                buttonPanel.add(Box.createHorizontalGlue());
                buttonPanel.add(continueButton);
                buttonPanel.add(Box.createHorizontalGlue());
            }

            {
                mainPanel   = new JPanel(new BorderLayout());
                mainPanel.add(winPanel,     BorderLayout.PAGE_START);
                mainPanel.add(heroPanel,    BorderLayout.CENTER);
                mainPanel.add(buttonPanel,  BorderLayout.PAGE_END);
                mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            }

            localize();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static GUIWin getScreen() {
        return (screen);
    }

    public void initPanel(JPanel panel) {
        GridBagConstraints c = new GridBagConstraints();
        {
            c.fill      = GridBagConstraints.BOTH;
            c.weightx   = 1;
            c.weighty   = 1;
            c.anchor    = GridBagConstraints.CENTER;
            panel.add(mainPanel, c);
        }
    }

    public void localize() {
        ResourceBundle uiLocale   = ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() );
        winLabel.setText(uiLocale.getString("winLabel"));
        autoSaveLabel.setText(uiLocale.getString("autoSaveLabel"));
        titleButton.setText(uiLocale.getString("titleButton"));
        continueButton.setText(uiLocale.getString("continueButton"));

        HeroData hero = GameData.getData().getHero();
        if (hero == null)
            return ;

        ResourceBundle statLocale = ResourceBundle.getBundle( "mrajaona.swingy.locale.StatResource", GameData.getData().getLocale() );
        ResourceBundle heroLocale = ResourceBundle.getBundle( "mrajaona.swingy.locale.HeroResource", GameData.getData().getLocale() );

        heroLabel.setText(
            ((ResourceMap) heroLocale.getObject("ClassesList")).get(hero.getHeroClass()) + " " + hero.getHeroName()
            );
        lvlLabel.setText(
            statLocale.getString("level") + " " + Integer.toString(hero.getLevel())
            );
    }

}
