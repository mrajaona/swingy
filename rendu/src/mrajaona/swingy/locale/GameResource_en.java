package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

public class GameResource_en extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* commands */
            // common
            {"exit",     ""},
            {"help",     ""},
            {"gui",      ""},
            {"console",  ""},
            {"language", ""},

            // title
            {"new",      ""},
            {"load",     ""},
            {"delete",   ""},

            // new
            {"create",   ""},

            // main
            {"move",     ""},
            {"status",   ""},
            {"save",     ""},
       };
    }

}
