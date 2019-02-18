package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

public class GameResource_fr extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* commands */
            // common
            {"menuCmds",     "-- gui, langue en/fr, quitter"},
            {"quitter",      "exit"},
            {"gui",          "gui"},
            {"console",      "console"},
            {"langue",       "language"},

            // title
            {"titleCmds",    "-- nouveau, charger <id>, supprimer <id>"},
            {"nouveau",      "new"},
            {"charger",      "load"},
            {"supprimer",    "delete"},

            // new
            {"créer",        "create"},

            // main
            {"mainCmds",     "-- sauvegarder, aller nord/sud/est/ouest, statut"},
            {"battleCmds",   "-- combattre, fuir"},
            {"lootCmds",     "-- équiper, laisser"},

            {"aller",        "move"},
            {"statut",       "status"},
            {"sauvegarder",  "save"},

            {"combattre",    "fight"},
            {"fuir",         "run"},

            {"équiper",      "equip"},
            {"laisser",      "leave"},

            // win
            {"winCmds",      "-- titre, continuer (nouvelle carte) (votre partie sera sauvegardée)"},
            {"continuer",    "continue"},

            // game over
            {"gameOverCmds", "-- titre, recharger (votre partie ne sera pas sauvegardée)"},
            {"recharger",    "reload"},

            {"titre",        "title"}

        };
    }

}
