package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

public class GameResource_en extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* Strings */
            {"msgGetInput",   "What do you want to do ?"},
            {"msgMove",       " moves "},

            {"msgEncounterA", "A wild "},
            {"msgEncounterB", " appeared !"},

            {"msgRun",        " tries to run..."},
            {"msgRunFail",    "...but fails."},
            {"msgRunSuccess", "...and succeeds !"},

            {"msgAttack",     " attacked"},
            {"msgDamageA",    " takes "},
            {"msgDamageB",    " damage."},
            {"msgDied",       " died."},

            {"msgDrop",       "The enemy dropped a "},
            {"msgEquip",      " equips the "},
            {"msgUnequip",      " unequips the "},
            {"msgLeave",      "The artifact magically vanishes."},

            {"msgLvlUp",      " is now level "},

            {"msgSave",       "Game saved."},

            /* commands */
            // common
            {"exit",     ""},
            {"help",     ""},
            {"gui",      ""},
            {"console",  ""},
            {"language", ""},

            // title
            {"new",      ""},
            {"load",     ""},
            {"delete",   ""},

            // new
            {"create",   ""},

            // main
            {"move",     ""},
            {"status",   ""},
            {"save",     ""},
       };
    }

}
