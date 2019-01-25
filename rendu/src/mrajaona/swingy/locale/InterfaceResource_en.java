package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

public class InterfaceResource_en extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* Strings */
            {"gui",     "gui"},
            {"console", "console"},

            // Title
            {"newButton",    "New game"},
            {"loadButton",   "Load"},
            {"deleteButton", "Delete"},

            // New
            {"createButton", "Create"},
            {"cancelButton", "Cancel"},

            // Main
            {"fightButton",  "Fight"},
            {"runButton",    "Run"},
            {"equipButton",  "Equip"},
            {"leaveButton",  "Leave"},

            // Win
            {"winLabel", "Congratulations, you won !"},
            {"titleButton", "Save and return to title"},
            {"continueButton", "Save and start a new map"},

            // Game Over
            {"loseLabel", "It seems you have died."},
            {"giveUpButton", "Return to title"},
            {"reloadButton", "Load last save"},

            // Menu
       };
    }

}
