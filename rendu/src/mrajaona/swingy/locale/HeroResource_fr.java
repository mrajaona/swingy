package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

import mrajaona.swingy.util.ResourceMap;

// TODO : accents

public class HeroResource_fr extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* Strings */
            {"msgCreateHeroClass", "Choisissez votre classe (guerrier, voleur, magicien, prêtre) :"},
            {"msgCreateHeroName", "Nommez votre héro :"},

            /* Objects */
            // Classes
            {
                "ClassesList",
                new ResourceMap(
                    new String[][] {
                        {"warrior", "guerrier"},
                        {"thief",   "voleur"},
                        {"mage",    "magicien"},
                        {"priest",  "prêtre"},
                    }
                )
            },

       };
    }

}
