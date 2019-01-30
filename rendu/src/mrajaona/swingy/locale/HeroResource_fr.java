package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

import mrajaona.swingy.util.ResourceMap;

public class HeroResource_fr extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* Strings */
            {"msgCreateHeroClass", "Choisissez votre classe (guerrier, voleur, magicien, prêtre) :"},
            {"msgCreateHeroName", "Nommez votre héro :"},

            {"msgListClasses", "Voici les classes disponibles :"},
            {"msgClass", "> %1$s : ATQ %2$d, DEF %3$d, PV %4$d"},// class, atk, def, hp
            {"msgLevel", "Tous les héros commencent au niveau 1, avec 0 EXP."},

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
