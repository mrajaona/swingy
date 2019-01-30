package mrajaona.swingy.view.gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;

import mrajaona.swingy.controller.NewHeroController;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.util.ResourceMap;
import mrajaona.swingy.util.Util;
import mrajaona.swingy.view.helper.BuildHelper;

public class GUINew {

    private static GUINew     screen = new GUINew();

    private JSplitPane mainPanel;

    private JPanel    inputPanel; // GridBagLayout

    private JPanel    paramsPanel;

    private JComboBox<String> classField;
    private JLabel            classLabel;
    private JTextField        nameField;
    private JLabel            nameLabel;

    private JPanel    controlPanel;
    private JButton   createButton;
    private JButton   cancelButton;

    private StatsTableModel statsTable;
    private JTable statsField;
    private JScrollPane heroStatsScrollPane;


    private GUINew() {
        try {

            // inputPanel
            {
                inputPanel = new JPanel(new GridBagLayout());

                // Panel for hero parameters
                {
                    paramsPanel = new JPanel();
                    paramsPanel.setLayout(new GridBagLayout());

                    GridBagConstraints c = new GridBagConstraints();

                    // Class setting
                    {
                        classLabel   = new JLabel();
                        classField   = new JComboBox<String>();
                        classField.addItemListener(new ItemListener() {
                            public void itemStateChanged(ItemEvent e) {
                                statsTable.updateTable((String) classField.getSelectedItem());
                            }
                        });
                    }
                    // Name setting
                    {
                        nameLabel   = new JLabel();
                        nameField   = new JTextField(20);
                        nameField.setEditable(true);

                        nameField.getDocument().addDocumentListener(new DocumentListener() {
                            @Override
                            public void changedUpdate(DocumentEvent event) {
                            }

                            @Override
                            public void insertUpdate(DocumentEvent e) {
                                String heroName = nameField.getText();

                                if (heroName == null || heroName.trim().isEmpty()) {
                                    createButton.setEnabled(false);
                                } else {
                                    createButton.setEnabled(true);
                                }

                            }

                            @Override
                            public void removeUpdate(DocumentEvent e) {
                                String heroName = nameField.getText();

                                if (heroName == null || heroName.trim().isEmpty()) {
                                    createButton.setEnabled(false);
                                } else {
                                    createButton.setEnabled(true);
                                }
                            }
                        });

                        nameField.addKeyListener(new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent e) {
                                char c = e.getKeyChar();
                                if(Character.isLetter(c) || c == ' ' || Character.isISOControl(c))
                                    ; // valid key
                                else
                                    e.consume(); // prevent unauthorized characters
                            }

                            @Override
                            public void keyPressed(KeyEvent e) {}

                            @Override
                            public void keyReleased(KeyEvent e) {
                                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                                    createButton.doClick();
                            }
                        });
                    }

                    {
                        c.fill    = GridBagConstraints.HORIZONTAL;
                        c.gridx   = 0;
                        c.gridy   = 0;
                        c.insets  = new Insets(5, 0, 5, 0);
                        c.anchor  = GridBagConstraints.LINE_START;
                        paramsPanel.add(classLabel, c);
                    }
                    { // here
                        c.fill    = GridBagConstraints.HORIZONTAL;
                        c.gridx   = 1;
                        c.gridy   = 0;
                        c.insets  = new Insets(5, 10, 5, 0);
                        c.anchor  = GridBagConstraints.LINE_END;
                        paramsPanel.add(classField, c);
                    }
                    {
                        c.fill    = GridBagConstraints.HORIZONTAL;
                        c.gridx   = 0;
                        c.gridy   = 1;
                        c.insets  = new Insets(5, 0, 5, 0);
                        c.anchor  = GridBagConstraints.LINE_START;
                        paramsPanel.add(nameLabel, c);
                    }
                    {
                        c.fill    = GridBagConstraints.HORIZONTAL;
                        c.gridx   = 1;
                        c.gridy   = 1;
                        c.insets  = new Insets(5, 10, 5, 0);
                        c.anchor  = GridBagConstraints.LINE_END;
                        paramsPanel.add(nameField, c);
                    }
                }
                {
                    GridBagConstraints c = new GridBagConstraints();
                    c.fill    = GridBagConstraints.HORIZONTAL;
                    c.gridy   = 0;
                    inputPanel.add(paramsPanel, c);
                }

                // Panel for buttons
                {
                    controlPanel = new JPanel();
                    controlPanel.setLayout(new FlowLayout());

                    // new game button
                    {
                        createButton = new JButton();
                        createButton.setEnabled(false);
                        createButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent event) {
                                String className = (String) classField.getSelectedItem();
                                String heroName = nameField.getText().trim();
                                try {
                                    NewHeroController.create(className, heroName);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    System.exit(1);
                                }
                            }
                        });
                    }

                    // cancel button
                    {
                        cancelButton = new JButton();
                        cancelButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent event) {
                                try {
                                    BuildHelper.prev();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    System.exit(1);
                                }
                            }
                        });
                    }

                    controlPanel.add(createButton);
                    controlPanel.add(cancelButton);

                }
                {
                    GridBagConstraints c = new GridBagConstraints();
                    c.fill    = GridBagConstraints.HORIZONTAL;
                    c.gridy   = 1;
                    inputPanel.add(controlPanel, c);
                }


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

            // Panel for hero info
            {
                mainPanel = new JSplitPane(
                    JSplitPane.VERTICAL_SPLIT,
                    false,
                    heroStatsScrollPane,
                    inputPanel
                );
                mainPanel.setOneTouchExpandable(false);
                mainPanel.setContinuousLayout(true);
                mainPanel.setDividerLocation(100);
            }

            localize();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static GUINew getScreen() {
        return (screen);
    }

    public void initPanel(JPanel panel) {
        GridBagConstraints c = new GridBagConstraints();
        {
            c.fill    = GridBagConstraints.BOTH;
            c.weightx = 1;
            c.weighty = 1;
            c.insets  = new Insets(5, 5, 5, 5);
            panel.add(mainPanel, c);
        }
    }

    public void reset() {
        classField.setSelectedIndex(0);
        nameField.setText(new String());
    }

    public void localize() {
        ResourceBundle statsLocale = ResourceBundle.getBundle( "mrajaona.swingy.locale.StatResource", GameData.getData().getLocale() );
        ResourceBundle locale = ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() );

        classLabel.setText(statsLocale.getString("class"));
        nameLabel.setText(statsLocale.getString("name"));

        createButton.setText(locale.getString("createButton"));
        cancelButton.setText(locale.getString("cancelButton"));

        localizeTextField();
        statsTable.updateLocale();

    }

    private void localizeTextField() {
        ResourceBundle locale = ResourceBundle.getBundle( "mrajaona.swingy.locale.HeroResource", GameData.getData().getLocale() );
        ResourceMap map = (ResourceMap) locale.getObject("ClassesList");

        String[] heroTypes = Util.heroTypes;

        int index = (classField != null && classField.getSelectedIndex() >= 0) ? classField.getSelectedIndex() : 0;
        classField.removeAllItems();

        for (int i = 0 ; i < heroTypes.length ; i++) {
            classField.addItem(map.get(heroTypes[i]));
        }

        classField.setSelectedIndex(index);
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

            data[0][0] = locale.getString("level");
            data[1][0] = locale.getString("experience");
            data[2][0] = locale.getString("attack");
            data[3][0] = locale.getString("defense");
            data[4][0] = locale.getString("hitPoints");

            updateTable(null); // TODO
        }

        public void resetTable() {
            for (int i = 0 ; i < data.length ; i++)
                data[i][1]  = "";
            fireTableDataChanged();
        }

        public void updateTable(String type) {
            if (type == null) {
                return ;
            }

            ResourceBundle locale = ResourceBundle.getBundle( "mrajaona.swingy.locale.HeroResource", GameData.getData().getLocale() );
            ResourceMap map = (ResourceMap) locale.getObject("ClassesList");
            String      heroClass = map.getKeyByValue((String) classField.getSelectedItem());

            Util.HeroBaseStats stats = Util.HERO_BASE_STATS_MAP.get(heroClass);

            if (stats == null) {
                resetTable();
                return ;
            }

            data[0][1] = Integer.toString(1);
            data[1][1] = Integer.toString(0);
            data[2][1] = Integer.toString(stats.getAtk() * 5);
            data[3][1] = Integer.toString(stats.getDef() * 5);
            data[4][1] = Integer.toString(stats.getHp() * 25);

            fireTableDataChanged();
        }

        public int getRowCount() { return data.length; }
        public int getColumnCount() { return columnNames.length; }
        public String getValueAt(int row, int col) {
            return data[row][col];
        }
    }

}
