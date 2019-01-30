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
            			{"none",   "rien"},
            			{"shirt",  "chemise"}
                    }
                )
            },

            {
                "HelmList",
                new ResourceMap(
                    new String[][] {
            			{"none",   "rien"},
            			{"hat",    "chapeau"}
                    }
                )
            },

            {
                "WeaponList",
                new ResourceMap(
                    new String[][] {
            			{"none",   "rien"},
            			{"sword",  "épée"}
                    }
                )
            },

       };
    }

}
