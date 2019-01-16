package mrajaona.swingy.view.gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mrajaona.swingy.util.Util;

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
                    classLabel   = new JLabel("Class");
                    classField   = new JComboBox<String>(Util.heroTypes); // TODO : localize
                    classField.setSelectedIndex(0);
                        // String className = (String) classField.getSelectedItem();

                }
                // Name setting
                {
                    nameLabel   = new JLabel("Name");
                    nameField   = new JTextField(20);
                    nameField.setEditable(true);
                        // String heroName = nameField.getText();
                }

                {
                    c.fill    = GridBagConstraints.HORIZONTAL;
                    c.gridx   = 0;
                    c.gridy   = 0;
                    c.insets  = new Insets(5, 0, 5, 0);
                    c.anchor  = GridBagConstraints.LINE_START;
                    paramsPanel.add(classLabel, c);
                }
                {
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
                    createButton = new JButton("Create");
                    createButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            // BuildHelper.create(class, name);
                        }
                    });
                }

                // load button
                {
                    cancelButton = new JButton("Cancel");
                    cancelButton.setEnabled(false);
                    cancelButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            // TODO : go to title screen
                        }
                    });
                }

                controlPanel.add(createButton);
                controlPanel.add(cancelButton);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static GUINew getScreen() {
        return (screen);
    }

    public static void localize() {
        // TODO : localize buttons and labels
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
