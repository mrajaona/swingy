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
            {"builderException", ""},
            {"enemyBuilderException", ""},
            {"heroBuilderException", ""},
            {"gameMapBuilderException", ""},
            {"saveFileBuilderException", ""},
            {"", ""},
            {"", ""},
            {"", ""},
            {"", ""},
            {"", ""},
            {"", ""},
            {"", ""},
            {"", ""},
            {"", ""},
            {"", ""},
       };
    }


}
