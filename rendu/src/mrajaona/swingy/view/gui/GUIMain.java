package mrajaona.swingy.view.gui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;

import mrajaona.swingy.controller.BattleController;
import mrajaona.swingy.controller.LootController;
import mrajaona.swingy.controller.MainGameController;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.character.HeroData;
import mrajaona.swingy.util.ResourceMap;
import mrajaona.swingy.util.Util.SubScreen;

public class GUIMain {

    private static GUIMain  screen = new GUIMain();

    private JSplitPane      mainPane;

    private JScrollPane     consolePane;
    private JTextArea       consoleArea;

    private JSplitPane      utilPane;

    private JScrollPane     heroStatsScrollPane;
    private JTable          statsField;
    private StatsTableModel statsTable;

    private JPanel          cards;

    private JPanel          mainPanel;
    private JButton         northButton;
    private JButton         southButton;
    private JButton         eastButton;
    private JButton         westButton;

    private JPanel          battlePanel;
    private JButton         fightButton;
    private JButton         runButton;

    private JPanel          lootPanel;
    private JButton         equipButton;
    private JButton         leaveButton;

    private DecimalFormat   format = new DecimalFormat("#");

    private GUIMain() {
        try {

            // Console
            {
                consoleArea = new JTextArea();
                consolePane = new JScrollPane(consoleArea);
            }

            // Hero stats
            {
                statsTable = new StatsTableModel();
                statsField = new JTable(statsTable);
                {
                    statsField.setShowGrid(false);
                    statsField.setTableHeader(null);
                }
                heroStatsScrollPane = new JScrollPane(statsField);
            }

            {
                //Create the "cards".

                // Main panel
                {
                    mainPanel   = new JPanel(new GridBagLayout());
                    // north button
                    {
                        northButton = new JButton("N");
                        northButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent event) {
                                try {
                                    String[] args = {
                                        "move",
                                        "north"
                                    };
                                    MainGameController.run(args);
                                }  catch (Exception e) {
                                    e.printStackTrace();
                                    System.exit(1);
                                }
                            }
                        });
                    }
                    {
                        southButton = new JButton("S");
                        southButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent event) {
                                try {
                                    String[] args = {
                                        "move",
                                        "south"
                                    };
                                    MainGameController.run(args);
                                }  catch (Exception e) {
                                    e.printStackTrace();
                                    System.exit(1);
                                }
                            }
                        });
                    }
                    {
                        eastButton = new JButton("E");
                        eastButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent event) {
                                try {
                                    String[] args = {
                                        "move",
                                        "east"
                                    };
                                    MainGameController.run(args);
                                }  catch (Exception e) {
                                    e.printStackTrace();
                                    System.exit(1);
                                }
                            }
                        });
                    }
                    {
                        westButton = new JButton("W");
                        westButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent event) {
                                try {
                                    String[] args = {
                                        "move",
                                        "west"
                                    };
                                    MainGameController.run(args);
                                }  catch (Exception e) {
                                    e.printStackTrace();
                                    System.exit(1);
                                }
                            }
                        });
                    }

                    // TODO : icons
                    GridBagConstraints c = new GridBagConstraints();
                    {
                        c.fill      = GridBagConstraints.NONE;
                        c.gridx     = 1;
                        c.gridy     = 0;
                        c.weightx   = 1;
                        c.weighty   = 1;
                        c.anchor    = GridBagConstraints.SOUTH;
                        mainPanel.add(northButton, c);
                    }
                    {
                        c.fill      = GridBagConstraints.NONE;
                        c.gridx     = 1;
                        c.gridy     = 2;
                        c.weightx   = 1;
                        c.weighty   = 1;
                        c.anchor    = GridBagConstraints.NORTH;
                        mainPanel.add(southButton, c);
                    }
                    {
                        c.fill      = GridBagConstraints.NONE;
                        c.gridx     = 3;
                        c.gridy     = 1;
                        c.weightx   = 1;
                        c.weighty   = 1;
                        c.anchor    = GridBagConstraints.WEST;
                        mainPanel.add(eastButton, c);
                    }
                    {
                        c.fill      = GridBagConstraints.NONE;
                        c.gridx     = 0;
                        c.gridy     = 1;
                        c.weightx   = 1;
                        c.weighty   = 1;
                        c.anchor    = GridBagConstraints.EAST;
                        mainPanel.add(westButton, c);
                    }
                }

                // Battle panel
                {
                    battlePanel = new JPanel();
                    battlePanel.setLayout(
                        new BoxLayout(
                            battlePanel,
                            BoxLayout.Y_AXIS
                            )
                        );

                    // fight button
                    {
                        fightButton = new JButton();
                        fightButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent event) {
                                try {
                                    String[] args = {
                                        "fight"
                                    };
                                    BattleController.run(args);
                                }  catch (Exception e) {
                                    e.printStackTrace();
                                    System.exit(1);
                                }
                            }
                        });
                        fightButton.setAlignmentY(Component.CENTER_ALIGNMENT);
                    }
                    battlePanel.add(fightButton);

                    // run button
                    {
                        runButton = new JButton();
                        runButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent event) {
                                try {
                                    String[] args = {
                                        "run"
                                    };
                                    BattleController.run(args);
                                }  catch (Exception e) {
                                    e.printStackTrace();
                                    System.exit(1);
                                }
                            }
                        });
                        runButton.setAlignmentY(Component.CENTER_ALIGNMENT);
                    }
                    battlePanel.add(runButton);
                }

                // Loot panel
                {
                    lootPanel   = new JPanel();
                    lootPanel.setLayout(
                        new BoxLayout(
                            lootPanel,
                            BoxLayout.Y_AXIS
                            )
                        );


                    // equip button
                    {
                        equipButton = new JButton();
                        equipButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent event) {
                                try {
                                    String[] args = {
                                        "equip"
                                    };
                                    LootController.run(args);
                                }  catch (Exception e) {
                                    e.printStackTrace();
                                    System.exit(1);
                                }
                            }
                        });
                        equipButton.setAlignmentY(Component.CENTER_ALIGNMENT);
                    }
                    lootPanel.add(equipButton);

                    // leave button
                    {
                        leaveButton = new JButton();
                        leaveButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent event) {
                                try {
                                    String[] args = {
                                        "leave"
                                    };
                                    LootController.run(args);
                                }  catch (Exception e) {
                                    e.printStackTrace();
                                    System.exit(1);
                                }
                            }
                        });
                        leaveButton.setAlignmentY(Component.CENTER_ALIGNMENT);
                    }
                    lootPanel.add(leaveButton);
                }

                //Create the panel that contains the "cards".
                cards = new JPanel(new CardLayout());
                {
                    cards.add(mainPanel,   SubScreen.MAIN.toString());
                    cards.add(battlePanel, SubScreen.BATTLE.toString());
                    cards.add(lootPanel,   SubScreen.LOOT.toString());
                }
                show(SubScreen.MAIN);
            }

            // Panel for hero info and controls
            {
                    utilPane = new JSplitPane(
                    JSplitPane.VERTICAL_SPLIT,
                    false,
                    heroStatsScrollPane,
                    cards
                );
                utilPane.setOneTouchExpandable(false);
                utilPane.setContinuousLayout(true);
                utilPane.setDividerLocation(300);
            }

            {
                mainPane = new JSplitPane(
                    JSplitPane.HORIZONTAL_SPLIT,
                    false,
                    consolePane,
                    utilPane
                );
                mainPane.setOneTouchExpandable(false);
                mainPane.setContinuousLayout(true);
                mainPane.setDividerLocation(500);
            }

        localize();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static GUIMain getScreen() {
        return (screen);
    }

    public void show(SubScreen screen) {
        CardLayout layout = (CardLayout) cards.getLayout();
        layout.show(cards, screen.toString());
    }

    public void reset() {
        statsTable.resetTable();
    }

    public void localize() {
        ResourceBundle locale = ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() );

        fightButton.setText(locale.getString("fightButton"));
        runButton.setText(locale.getString("runButton"));

        equipButton.setText(locale.getString("equipButton"));
        leaveButton.setText(locale.getString("leaveButton"));

        statsTable.updateLocale();
    }

    public void log(String msg) {
        consoleArea.append(msg + System.lineSeparator());
    }

    public void initPanel(JPanel panel) {
        GridBagConstraints c = new GridBagConstraints();
        {
            c.fill      = GridBagConstraints.BOTH;
            c.weightx   = 1;
            c.weighty   = 1;
            c.anchor    = GridBagConstraints.CENTER;
            panel.add(mainPane, c);
        }
    }

    public void updateTable() {
        statsTable.updateTable();
    }

    // Class for statsField custom table
    private class StatsTableModel extends AbstractTableModel {
        private static final long serialVersionUID = 1L;
        private String[] columnNames = {
            "key",
            "value"
        };
        private String[][] data = {
            { "", "" },
            { "", "" },
            { "", "" },
            { "", "" },
            { "", "" },
            { "", "" },
            { "", "" },
            { "", "" },
            { "", "" },
            { "", "" }
        };

        public StatsTableModel() {
            updateLocale();
        }

        public boolean isCellEditable(int row, int col) {
            return false;
        }

        public void updateLocale() {
            ResourceBundle locale = ResourceBundle.getBundle( "mrajaona.swingy.locale.StatResource", GameData.getData().getLocale() );

            data[0][0] = locale.getString("name");
            data[1][0] = locale.getString("class");
            data[2][0] = locale.getString("level");
            data[3][0] = locale.getString("experience");
            data[4][0] = locale.getString("helm");
            data[5][0] = locale.getString("armor");
            data[6][0] = locale.getString("weapon");
            data[7][0] = locale.getString("attack");
            data[8][0] = locale.getString("defense");
            data[9][0] = locale.getString("hitPoints");

            updateTable();
        }

        public void resetTable() {
            for (int i = 0 ; i < data.length ; i++)
                data[i][1]  = "";
            fireTableDataChanged();
        }

        public void updateTable() {
            HeroData hero = GameData.getData().getHero();

            if (hero == null) {
                resetTable();
                return ;
            }

            ResourceBundle locale = ResourceBundle.getBundle( "mrajaona.swingy.locale.HeroResource", GameData.getData().getLocale() );
            ResourceBundle artifactLocale = ResourceBundle.getBundle( "mrajaona.swingy.locale.ArtifactResource", GameData.getData().getLocale() );

            data[0][1] = hero.getHeroName();
            data[1][1] = ((ResourceMap) locale.getObject("ClassesList")).get(hero.getHeroClass());
            data[2][1] = Integer.toString(hero.getLevel()); // TODO : EXP to next level
            data[3][1] = format.format(hero.getExperience());
            data[4][1] = artifactLocale.getString(hero.getHelm().getName())   + " (" + Integer.toString(hero.getHelm().getModifier())   + ")";
            data[5][1] = artifactLocale.getString(hero.getArmor().getName())  + " (" + Integer.toString(hero.getArmor().getModifier())  + ")";
            data[6][1] = artifactLocale.getString(hero.getWeapon().getName()) + " (" + Integer.toString(hero.getWeapon().getModifier()) + ")";
            data[7][1] = Integer.toString(hero.getAttack());
            data[8][1] = Integer.toString(hero.getDefense());
            data[9][1] = Integer.toString(hero.getHitPoints()) + " / " + Integer.toString(hero.getBaseHitPoints() + hero.getWeapon().getModifier());

            fireTableDataChanged();
        }

        public int getRowCount() { return data.length; }
        public int getColumnCount() { return columnNames.length; }
        public String getValueAt(int row, int col) {
            return data[row][col];
        }
    }

}
