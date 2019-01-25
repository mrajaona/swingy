package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

public class InterfaceResource_fr extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* Strings */
            {"gui",     "gui"},
            {"console", "console"},

            // Title
            {"newButton", "Nouveau"},
            {"loadButton", "Charger"},
            {"deleteButton", "Supprimer"},

            // New
            {"createButton", "Creer"},
            {"cancelButton", "Annuler"},

            // Main
            {"fightButton",  "Combattre"},
            {"runButton",    "Fuir"},
            {"equipButton",  "Equiper"},
            {"leaveButton",  "Laisser"},

            // Win
            {"winLabel", "Felicitations, vous avez gagne !"},
            {"titleButton", "Sauvegarder et retourner à l'ecran titre"},
            {"continueButton", "Sauvegarder et commencer une nouvelle carte"},
        };
    }

}
