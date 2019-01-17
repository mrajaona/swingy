package mrajaona.swingy.view.gui;

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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.util.ResourceMap;
import mrajaona.swingy.util.Util;
import mrajaona.swingy.view.helper.BuildHelper;

public class GUINew {

    private static GUINew    screen = new GUINew();

    private static JPanel    paramsPanel;

    private static JComboBox<String> classField;
    private static JLabel            classLabel;
    private static JTextField        nameField;
    private static JLabel            nameLabel;

    private static JPanel    controlPanel;
    private static JButton   createButton;
    private static JButton   cancelButton;

    private GUINew() {
        try {
            // Panel for hero parameters
            {
                paramsPanel = new JPanel();
                paramsPanel.setLayout(new GridBagLayout());

                GridBagConstraints c = new GridBagConstraints();

                // Class setting
                {
                    classLabel   = new JLabel();
                    classField   = new JComboBox<String>(); // TODO : localize
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
                                BuildHelper.create(className, heroName);
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

                localize();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static GUINew getScreen() {
        return (screen);
    }

    public static void reset() {
        classField.setSelectedIndex(0);
        nameField.setText(new String());
    }

    public static void localize() {
        ResourceBundle statsLocale = ResourceBundle.getBundle( "mrajaona.swingy.locale.StatResource", GameData.getData().getLocale() );
        ResourceBundle locale = ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() );

        classLabel.setText(statsLocale.getString("class"));
        nameLabel.setText(statsLocale.getString("name"));

        createButton.setText(locale.getString("createButton"));
        cancelButton.setText(locale.getString("cancelButton"));

        localizeTextField();
    }

    private static void localizeTextField() {
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

    public static void initPanel(JPanel panel) {
        GridBagConstraints c = new GridBagConstraints();
        {
            c.fill    = GridBagConstraints.HORIZONTAL;
            c.gridy   = 0;
            c.weightx = 0;
            c.weighty = 0;
            c.insets  = new Insets(0,0,0,0);
            panel.add(paramsPanel, c);
        }
        {
            c.fill    = GridBagConstraints.HORIZONTAL;
            c.gridy   = 1;
            c.weightx = 0;
            c.weighty = 0;
            c.insets  = new Insets(0,0,0,0);
            panel.add(controlPanel, c);
        }
    }

}
