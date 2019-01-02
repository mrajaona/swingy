package mrajaona.swingy.locale;

import java.util.ListResourceBundle;
import java.util.Set;

public class LocaleResource extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            // {"Key", "Content"},
        	{"CreateHeroClass", "Choose your class (Warrior, Thief, Mage, Priest) :"},
        	{"CreateHeroName", "Name your hero :"},
       };
    }

}
