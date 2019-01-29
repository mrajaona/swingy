package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

public class ErrorResource_en extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* Strings */
            {"defaultError", "Error Message"},
            {"defaultException", "Exception Message"},

            /* errors */
            {"invalidClass", "Not a valid class (warrior, hief, mage, priest)."},
            {"invalidName", "Not a valid name : only letters and spaces allowed."},
            {"invalidDirection", "Invalid direction (north, south east, west)."},
            {"invalidLanguage", "Invalid language (en, fr)."},

            /* exceptions */
            {"builderException",         "Could not build valid Object."},
            {"enemyBuilderException",    "Could not build valid enemy."},
            {"gameMapBuilderException",  "Could not build valid map."},
            {"heroBuilderException",     "Could not build valid hero."},
            {"saveFileBuilderException", "Could not build valid save file."},

            {"invalidArtifactException", "Invalid artifact."},
            {"invalidCoordException",    "Invalid coordinates."},
            {"invalidSaveFileException", "Invalid save file."},
            {"invalidScreenException",   "Invalid screen."},
            {"invalidViewTypeException", "Invalid view type."},

            {"loadHeroListException",    "Could not load hero list."},
            {"resourceMapException",     "Could not load resource."},
            {"dataException",            "Problem loading data."}

       };
    }


}
