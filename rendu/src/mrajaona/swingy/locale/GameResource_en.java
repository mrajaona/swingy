package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

public class GameResource_en extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* Strings */
            {"msgGetInput",   "What do you want to do ?"},
            {"msgMove",       "<hero> moves <direction> (<x>, <y>)."},

            {"msgEncounterA", "A wild level <level> <enemy> appeared !"},

            {"msgRun",        "<hero> tries to run..."},
            {"msgRunFail",    "...but fails."},
            {"msgRunSuccess", "...and succeeds !"},

            {"msgAttack",     "<subject> attacks."},
            {"msgDamage",     "<target> takes <hp> damage."},
            {"msgDied",       "<subject> died."},

            {"msgDrop",       "The enemy dropped a <artifact>"},
            {"msgEquip",      "<hero> equips the <artifact>"},
            {"msgUnequip",    "<hero> unequips the <artifact>"},
            {"msgLeave",      "The artifact magically vanishes."},

            {"msgLvlUp",      "<hero> is now level <level> !"},

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
