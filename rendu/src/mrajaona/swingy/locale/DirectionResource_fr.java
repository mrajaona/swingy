package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

import mrajaona.swingy.util.ResourceMap;

public class DirectionResource_fr extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* Objects */
            // Classes
            {
                "DirectionList",
                new ResourceMap(
                    new String[][] {
                        {"north", "nord"},
                        {"south", "sud"},
                        {"east",  "est"},
                        {"west",  "ouest"},
                    }
                )
            },
        };
    }

}
