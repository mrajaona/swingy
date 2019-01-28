package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

public class GameResource_fr extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* commands */
            // common
            {"menuCmds",     "-- aide, gui, langue en/fr, quitter"},
            {"quitter",      "exit"},
            {"aide",         "help"},
            {"gui",          "gui"},
            {"console",      "console"},
            {"langue",       "language"},

            // title
            {"titleCmds",    "-- nouveau, charger <id>, supprimer <id>"},
            {"nouveau",      "new"},
            {"charger",      "load"},
            {"supprimer",    "delete"},

            // new
            {"creer",        "create"},

            // main
            {"mainCmds",     "-- sauvegarder, aller nord/sud/est/ouest, statut"},
            {"battleCmds",   "-- sauvegarder, combattre, fuir"},
            {"lootCmds",     "-- sauvegarder, equiper, laisser"},

            {"aller",        "move"},
            {"statut",       "status"},
            {"sauvegarder",  "save"},

            {"combattre",    "fight"},
            {"fuir",         "run"},

            {"equiper",      "equip"},
            {"laisser",      "leave"},

            // win
            {"winCmds",      "-- titre, continuer (nouvelle carte) (votre partie sera sauvegardee)"},
            {"continuer",    "continue"},

            // game over
            {"gameOverCmds", "-- titre, recharger (votre partie ne sera pas sauvegardee)"},
            {"recharger",    "reload"},

            {"titre",        "title"}

        };
    }

}
