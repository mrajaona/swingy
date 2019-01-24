package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

public class GameResource_en extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* Strings */
            {"msgGetInput",   "What do you want to do ?"},
            {"msgMove",       "%1$s moves %2$s (%3$d, %4$d)."}, // hero name, direction, x, y

            {"msgEncounter", "A wild level %1$d %2$s appeared !"}, // level, enemy type

            {"msgRun",        "%1$s tries to run..."}, // hero name
            {"msgRunFail",    "...but fails."},
            {"msgRunSuccess", "...and succeeds !"},

            {"msgAttack",     "%1$s attacks."}, // hero name / enemy type
            {"msgDamage",     "%1$s takes %2$d damage."}, // hero name / enemy type, damage
            {"msgDied",       "%1$s died."}, // hero name / enemy type

            {"msgDrop",       "The enemy dropped %1$s. (%2$s : %3$s + %4$d)"}, // artifact name, type, stat, modifier
            {"msgEquip",      "%1$s equips the %2$s."}, // hero name, artifact name
            {"msgUnequip",    "%1$s unequips the %1$s."}, // hero name, artifact name
            {"msgLeave",      "The artifact magically vanishes."},

            {"msgLvlUp",      "%1$s is now level %2$d !"}, // hero name, level

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
