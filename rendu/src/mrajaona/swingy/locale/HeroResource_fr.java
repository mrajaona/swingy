package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

import mrajaona.swingy.util.ResourceMap;

// TODO : accents

public class HeroResource_fr extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* Strings */
            {"CreateHeroClass", "Choisissez votre classe (Guerrier, Voleur, Magicien, Pretre) :"},
            {"CreateHeroName", "Nommez votre hero :"},

            /* Objects */
            // Classes
            {
                "ClassesList",
                new ResourceMap(
                    new String[][] {
                        {"Warrior", "Guerrier"},
                        {"Thief", "Voleur"},
                        {"Mage", "Magicien"},
                        {"Priest", "Pretre"},
                    }
                )
            },

       };
    }

}
