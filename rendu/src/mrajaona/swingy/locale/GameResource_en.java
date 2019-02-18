package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

public class GameResource_en extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* commands */
            // common
            {"menuCmds",     "-- gui, language en/fr, exit"},
            {"exit",         "exit"},
            {"gui",          "gui"},
            {"console",      "console"},
            {"language",     "language"},

            // title
            {"titleCmds",    "-- new, load <id>, delete <id>"},
            {"new",          "new"},
            {"load",         "load"},
            {"delete",       "delete"},

            // new
            {"create",       "create"},

            // main
            {"mainCmds",     "-- save, move north/south/east/west, status"},
            {"battleCmds",   "-- fight, run"},
            {"lootCmds",     "-- equip, leave"},

            {"move",         "move"},
            {"status",       "status"},
            {"save",         "save"},

            {"fight",        "fight"},
            {"run",          "run"},

            {"equip",        "equip"},
            {"leave",        "leave"},

            // win
            {"winCmds",      "-- title, continue (new map) (your game will be saved)"},
            {"continue",     "continue"},

            // game over
            {"gameOverCmds", "-- title, reload (your game will not be saved)"},
            {"reload",       "reload"},

            {"title",        "title"}

        };
    }

}
