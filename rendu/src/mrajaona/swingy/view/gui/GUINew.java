package mrajaona.swingy.view.gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GUINew {

    private static GUINew         screen = new GUINew();

    // private static /*selectfield*/ classField
    // private static /*textfield*/ nameField

    private static JPanel           controlPanel;
    private static JButton          createButton;
    private static JButton          cancelButton;

    private GUINew() {
        try {

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

    public static void createAndShowGUI() {
        Window.getWindow().getFrame().removeAll();
        Window.getWindow().getFrame().revalidate();
        Window.getWindow().getFrame().repaint();

        Window.getWindow().getFrame().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        {
            c.fill    = GridBagConstraints.HORIZONTAL;
            c.gridy   = 0;
            c.weightx = 0;
            c.weighty = 0;
            c.insets  = new Insets(0,0,0,0);
            Window.getWindow().getFrame().add(controlPanel, c);
        }
        Window.getWindow().show();
    }

}
