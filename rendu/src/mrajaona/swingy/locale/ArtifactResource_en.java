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
                        {"none", "none"},
                        {"shirt", "shirt"},
                        {"robe", "robe"},
                        {"tunic", "tunic"},
                        {"suit and tie", "suit and tie"},
                        {"swimsuit", "swimsuit"}
                    }
                )
            },

            {
                "HelmList",
                new ResourceMap(
                    new String[][] {
                        {"none", "none"},
                        {"hat", "hat"},
                        {"straw hat", "straw hat"},
                        {"winged cap", "winged cap"},
                        {"night cap", "night cap"}
                    }
                )
            },

            {
                "WeaponList",
                new ResourceMap(
                    new String[][] {
                        {"none", "none"},
                        {"sword", "sword"},
                        {"master sword", "master sword"},
                        {"lance", "lance"},
                        {"axe", "axe"},
                        {"bow", "bow"}
                    }
                )
            },

       };
    }

}
