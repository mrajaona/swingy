package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

import mrajaona.swingy.util.ResourceMap;

public class ArtifactResource_fr extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* Strings */
            {"armor",  "armure"},
            {"helm",   "casque"},
            {"weapon", "arme"},

            /* Objects */
            {
                "ArmorList",
                new ResourceMap(
                    new String[][] {
            			{"none", "rien"},
                        {"shirt", "chemise"},
                        {"robe", "robe"},
                        {"tunic", "tunique"},
                        {"suit and tie", "costard cravate"},
                        {"swimsuit", "maillot de bain"}
                    }
                )
            },

            {
                "HelmList",
                new ResourceMap(
                    new String[][] {
            			{"none", "rien"},
                        {"hat", "chapeau"},
                        {"straw hat", "chapeau de paille"},
                        {"winged cap", "casquette à ailes"},
                        {"night cap", "bonnet de nuit"}
                    }
                )
            },

            {
                "WeaponList",
                new ResourceMap(
                    new String[][] {
            			{"none", "rien"},
                        {"sword", "épée"},
                        {"master sword", "épée de légende"},
                        {"lance", "lance"},
                        {"axe", "hache"},
                        {"bow", "arc"}
                    }
                )
            },

       };
    }

}
