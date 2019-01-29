package mrajaona.swingy.view.gui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import mrajaona.swingy.controller.TitleScreenController;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.character.HeroData;
import mrajaona.swingy.exception.LoadHeroListException;
import mrajaona.swingy.util.ResourceMap;
import mrajaona.swingy.view.helper.TitleHelper;

public class GUITitle {

    private static GUITitle            screen = new GUITitle();

    private JSplitPane                 loadPanel;

    private JScrollPane                heroStatsScrollPane;
    private JTable                     statsField;
    private StatsTableModel            statsTable;

    private JScrollPane                heroListScrollPane;
    private DefaultListModel<HeroData> heroListModel;
    private JList<HeroData>            heroList;

    private JPanel                     controlPanel;
    private JButton                    newButton;
    private JButton                    loadButton;
    private JButton                    deleteButton;

    private DecimalFormat              format = new DecimalFormat("#");

    private GUITitle() {
        try {
            // Hero stats
            {
                statsTable          = new StatsTableModel();
                statsField          = new JTable(statsTable);
                {
                    statsField.setShowGrid(false);
                    statsField.setTableHeader(null);
                }
                heroStatsScrollPane = new JScrollPane(statsField);
            }

            // Hero list
            {
                initHeroList();
            }

            // Panel for hero info
            {
                loadPanel = new JSplitPane(
                    JSplitPane.HORIZONTAL_SPLIT,
                    false,
                    heroListScrollPane,
                    heroStatsScrollPane
                );
                loadPanel.setOneTouchExpandable(false);
                loadPanel.setContinuousLayout(true);
                loadPanel.setDividerLocation(100);
            }

            // Panel for buttons
            {
                controlPanel = new JPanel();
                controlPanel.setLayout(new FlowLayout());

                // new game button
                {
                    newButton = new JButton();
                    newButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            try {
                                String[] args = {
                                    "new"
                                };
                                TitleScreenController.run(args);
                            }  catch (Exception e) {
                                e.printStackTrace();
                                System.exit(1);
                            }
                        }
                    });
                }

                // load button
                {
                    loadButton = new JButton();
                    loadButton.setEnabled(false);
                    loadButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            try {
                                String[] args = {
                                    "load",
                                    String.valueOf(heroList.getSelectedValue().getId())
                                };
                                TitleScreenController.run(args);
                            }  catch (Exception e) {
                                e.printStackTrace();
                                System.exit(1);
                            }
                        }
                    });
                }

                // delete button
                {
                    deleteButton = new JButton();
                    deleteButton.setEnabled(false);
                    deleteButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            try {
                                String[] args = {
                                    "delete",
                                    String.valueOf(heroList.getSelectedValue().getId())
                                };
                                TitleScreenController.run(args);
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.exit(1);
                            }
                            ((DefaultListModel<HeroData>) heroList.getModel()).removeElementAt(heroList.getSelectedIndex());
                        }
                    });
                }

                controlPanel.add(newButton);
                controlPanel.add(loadButton);
                controlPanel.add(deleteButton);

                localize();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static GUITitle getScreen() {
        return (screen);
    }

    public void initPanel(JPanel panel) {
        GridBagConstraints c = new GridBagConstraints();
        {
            c.fill    = GridBagConstraints.BOTH;
            c.gridy   = 0;
            c.weightx = 1;
            c.weighty = 1;
            c.insets  = new Insets(5, 5, 2, 5);
            panel.add(loadPanel, c);
        }
        {
            c.fill    = GridBagConstraints.HORIZONTAL;
            c.gridy   = 1;
            c.weightx = 0;
            c.weighty = 0;
            c.insets  = new Insets(0, 0, 0, 0);
            panel.add(controlPanel, c);
        }
    }

    public void reset() {
        heroList.clearSelection();
        statsTable.resetTable();
    }

    public void localize() {
        ResourceBundle locale = ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() );

        newButton.setText(locale.getString("newButton"));
        loadButton.setText(locale.getString("loadButton"));
        deleteButton.setText(locale.getString("deleteButton"));

        statsTable.updateLocale(heroList.getSelectedValue());
    }

    public void initHeroList() throws SQLException, IOException, LoadHeroListException {
        heroListModel       = new DefaultListModel<HeroData>();
        {
            Object[] list = TitleHelper.getHeroesList();
            if (list == null)
                throw (new LoadHeroListException()); // Empty != null
            for (int i = 0 ; i < list.length ; i++) {
                heroListModel.addElement((HeroData) list[i]);
            }
        }
        heroList            = new JList<HeroData>(heroListModel);
        {
            heroList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            heroList.setLayoutOrientation(JList.VERTICAL);
            heroList.setVisibleRowCount(-1);
            heroList.setCellRenderer(new HeroListCellRenderer());
            heroList.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    HeroData hero = heroList.getSelectedValue();

                    loadButton.setEnabled(hero == null ? false : true);
                    deleteButton.setEnabled(hero == null ? false : true);

                    statsTable.updateTable(hero);
                }
            });
        }
        heroListScrollPane  = new JScrollPane(heroList);
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
            updateLocale(null);
        }

        public boolean isCellEditable(int row, int col) {
            return false;
        }

        public void updateLocale(HeroData hero) {
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
            data[9][0] = locale.getString("maxHitPoints");

            updateTable(hero);
        }

        public void resetTable() {
            for (int i = 0 ; i < data.length ; i++)
                data[i][1]  = "";
            fireTableDataChanged();
        }

        public void updateTable(HeroData hero) {
            if (hero == null) {
                resetTable();
                return ;
            }

            ResourceBundle locale = ResourceBundle.getBundle( "mrajaona.swingy.locale.HeroResource", GameData.getData().getLocale() );
            ResourceBundle artifactLocale = ResourceBundle.getBundle( "mrajaona.swingy.locale.ArtifactResource", GameData.getData().getLocale() );
            ResourceMap    helmLocale     = (ResourceMap) artifactLocale.getObject( "HelmList" );
            ResourceMap    armorLocale    = (ResourceMap) artifactLocale.getObject( "ArmorList" );
            ResourceMap    weaponLocale   = (ResourceMap) artifactLocale.getObject( "WeaponList" );

            data[0][1] = hero.getHeroName();
            data[1][1] = ((ResourceMap) locale.getObject("ClassesList")).get(hero.getHeroClass());
            data[2][1] = Integer.toString(hero.getLevel());
            data[3][1] = format.format(hero.getExperience());
            data[4][1] = helmLocale.get(hero.getHelm().getName())     + " (" + Integer.toString(hero.getHelm().getModifier())   + ")";
            data[5][1] = armorLocale.get(hero.getArmor().getName())   + " (" + Integer.toString(hero.getArmor().getModifier())  + ")";
            data[6][1] = weaponLocale.get(hero.getWeapon().getName()) + " (" + Integer.toString(hero.getWeapon().getModifier()) + ")";
            data[7][1] = Integer.toString(hero.getAttack());
            data[8][1] = Integer.toString(hero.getDefense());
            data[9][1] = Integer.toString(hero.getMaxHitPoints());

            fireTableDataChanged();
        }

        public int getRowCount() { return data.length; }
        public int getColumnCount() { return columnNames.length; }
        public String getValueAt(int row, int col) {
            return data[row][col];
        }
    }

    // Class for heroList custom renderer
    private class HeroListCellRenderer extends JLabel implements ListCellRenderer<Object> {
        private static final long serialVersionUID = 1L;

        @SuppressWarnings("unused")
        public HeroListCellRenderer() {
            setOpaque(true);
            setHorizontalAlignment(LEFT);
        }

        public Component getListCellRendererComponent(
            JList<?> list,
            Object   value,
            int      index,
            boolean  isSelected,
            boolean  cellHasFocus
        ) {

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            HeroData data = (HeroData) value;

            setText(data.getHeroName());

            return this;
        }
    }

}
