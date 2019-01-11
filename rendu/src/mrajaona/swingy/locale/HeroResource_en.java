package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

import mrajaona.swingy.util.ResourceMap;

public class HeroResource_en extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* Strings */
            {"msgCreateHeroClass", "Choose your class (warrior, thief, mage, priest) :"},
            {"msgCreateHeroName", "Name your hero :"},

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
