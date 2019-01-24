package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

import mrajaona.swingy.util.ResourceMap;

public class ArtifactResource_en extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* Strings */
            {"armor",  "armor"},
            {"helm",   "helm"},
            {"weapon", "weapon"},

            /* Objects */
            {
                "ArmorList",
                new ResourceMap(
                    new String[][] {
            			{"none",   "none"},
            			{"shirt",  "shirt"}
                    }
                )
            },

            {
                "HelmList",
                new ResourceMap(
                    new String[][] {
            			{"none",   "none"},
            			{"hat",    "hat"}
                    }
                )
            },

            {
                "WeaponList",
                new ResourceMap(
                    new String[][] {
            			{"none",   "none"},
            			{"sword",  "sword"}
                    }
                )
            },

       };
    }

}
