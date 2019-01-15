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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import mrajaona.swingy.Save;
import mrajaona.swingy.view.helper.TitleHelper;
import mrajaona.swingy.data.character.HeroData;

public class GUITitle {

    private static GUITitle    screen = new GUITitle();

    private static JSplitPane  loadPanel;

    private static JScrollPane     heroListScrollPane;
    private static JList<HeroData> heroList;

    private static JScrollPane heroStatsScrollPane;
    private static JTextArea   statsField;

    private static JPanel      controlPanel;
    private static JButton     newButton;
    private static JButton     loadButton;

    private GUITitle() {
        try {
            // Hero list
            heroList            = new JList<HeroData>(Save.getManager().listHeroes());
            heroList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            heroList.setLayoutOrientation(JList.VERTICAL);
            heroList.setVisibleRowCount(-1);
            heroList.setCellRenderer(new HeroListCellRenderer());
            heroListScrollPane  = new JScrollPane(heroList);

            // Hero stats
            statsField          = new JTextArea();
            heroStatsScrollPane = new JScrollPane(statsField);

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

            loadButton = new JButton("Load game");
            loadButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    TitleHelper.loadHero();
                }
            });

            controlPanel.add(newButton);
            controlPanel.add(loadButton);
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

    // Class for heroList custom renderer

    private class HeroListCellRenderer extends JLabel implements ListCellRenderer<Object> {
		private static final long serialVersionUID = 1L;

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
