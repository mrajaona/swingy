package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

import mrajaona.swingy.util.ResourceMap;

public class HeroResource_en extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* Strings */
            {"msgCreateHeroClass", "Choose your class (warrior, thief, mage, priest) :"},
            {"msgCreateHeroName", "Name your hero :"},

            {"msgListClasses", "Here are the available classes :"},
            {"msgClass", "> %1$s : ATK %2$d, DEF %3$d, HP %4$d"}, // class, atk, def, hp
            {"msgLevel", "All heroes start with 0 EXP at level 1."},

            /* Objects */
            // Classes
            {
                "ClassesList",
                new ResourceMap(
                    new String[][] {
                        {"warrior", "warrior"},
                        {"thief", "thief"},
                        {"mage", "mage"},
                        {"priest", "priest"},
                    }
                )
            },
       };
    }

}
