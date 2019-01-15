package mrajaona.swingy.view.gui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
import javax.swing.table.TableCellRenderer;

import mrajaona.swingy.Save;
import mrajaona.swingy.view.helper.TitleHelper;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.character.HeroData;

public class GUITitle {

    private static GUITitle        screen = new GUITitle();

    private static JSplitPane      loadPanel;

    private static JScrollPane     heroStatsScrollPane;
    private static JTable          statsField;
    private static StatsTableModel statsTable;

    private static JScrollPane     heroListScrollPane;
    private static JList<HeroData> heroList;

    private static JPanel          controlPanel;
    private static JButton         newButton;
    private static JButton         loadButton;
    private static JButton         deleteButton;

    private GUITitle() {
        try {
            // Hero stats
            statsTable          = new StatsTableModel();
            statsField          = new JTable(statsTable);
            statsField.setShowGrid(false);
            statsField.setTableHeader(null);
            heroStatsScrollPane = new JScrollPane(statsField);

            // Hero list
            heroList            = new JList<HeroData>(Save.getManager().listHeroes());
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
            heroListScrollPane  = new JScrollPane(heroList);

            // Panel for hero info
            loadPanel = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                false,
                heroListScrollPane,
                heroStatsScrollPane
            );
            loadPanel.setOneTouchExpandable(false);
            loadPanel.setContinuousLayout(true);
            loadPanel.setDividerLocation(200);

            // Panel for buttons
            controlPanel = new JPanel();
            controlPanel.setLayout(new FlowLayout());

            newButton = new JButton("New game");
            newButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    TitleHelper.newHero();
                }
            });

            loadButton = new JButton("Load");
            loadButton.setEnabled(false);
            loadButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    TitleHelper.loadHero(heroList.getSelectedValue().getId());
                }
            });

            deleteButton = new JButton("Delete");
            deleteButton.setEnabled(false);
            deleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    TitleHelper.deleteHero(heroList.getSelectedValue().getId());
                }
            });

            controlPanel.add(newButton);
            controlPanel.add(loadButton);
            controlPanel.add(deleteButton);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static GUITitle getScreen() {
        return (screen);
    }

    public static void createAndShowGUI() {
        /*
        Window.getWindow().getFrame().removeAll();
        Window.getWindow().getFrame().revalidate();
        Window.getWindow().getFrame().repaint();
        */

        Window.getWindow().getFrame().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill    = GridBagConstraints.BOTH;
        c.gridy   = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.insets  = new Insets(5, 5, 2, 5);
        Window.getWindow().getFrame().add(loadPanel, c);

        c.fill    = GridBagConstraints.HORIZONTAL;
        c.gridy   = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.insets  = new Insets(0,0,0,0);
        Window.getWindow().getFrame().add(controlPanel, c);

        Window.getWindow().show();
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

            if (hero != null)
                updateTable(hero);
            else
                resetTable();
        }

        public void resetTable() {
            for (int i = 0 ; i < data.length ; i++)
                data[i][1]  = "";
            fireTableDataChanged();
        }

        public void updateTable(HeroData hero) {
            ResourceBundle locale = ResourceBundle.getBundle( "mrajaona.swingy.locale.StatResource", GameData.getData().getLocale() );
            ResourceBundle artifactLocale = ResourceBundle.getBundle( "mrajaona.swingy.locale.ArtifactResource", GameData.getData().getLocale() );

            data[0][1] = hero.getHeroName();
            data[1][1] = hero.getHeroClass();
            data[2][1] = Integer.toString(hero.getLevel());
            data[3][1] = Double.toString(hero.getExperience());
            data[4][1] = artifactLocale.getString(hero.getHelm().getName())   + " (" + Integer.toString(hero.getHelm().getModifier())   + ")";
            data[5][1] = artifactLocale.getString(hero.getArmor().getName())  + " (" + Integer.toString(hero.getArmor().getModifier())  + ")";
            data[6][1] = artifactLocale.getString(hero.getWeapon().getName()) + " (" + Integer.toString(hero.getWeapon().getModifier()) + ")";
            data[7][1] = Integer.toString(hero.getBaseAttack())    + " + " + Integer.toString(hero.getHelm().getModifier());
            data[8][1] = Integer.toString(hero.getBaseDefense())   + " + " + Integer.toString(hero.getArmor().getModifier());
            data[9][1] = Integer.toString(hero.getBaseHitPoints()) + " + " + Integer.toString(hero.getWeapon().getModifier());

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
