package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

import mrajaona.swingy.util.ResourceMap;

public class HeroResource_en extends ListResourceBundle {



    protected Object[][] getContents() {
        return new Object[][] {
            /* Strings */
            {"CreateHeroClass", "Choose your class (Warrior, Thief, Mage, Priest) :"},
            {"CreateHeroName", "Name your hero :"},

            /* Objects */
            // Classes
            {
                "ClassesList",
                new ResourceMap(
                    new String[][] {
                        {"Warrior", "Warrior"},
                        {"Thief", "Thief"},
                        {"Mage", "Mage"},
                        {"Priest", "Priest"},
                    }
                )
            },
       };
    }

}
