package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

public class CreateHeroResource extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            // {"Key", "Content"},
            {"CreateHeroClass", "Choose your class (Warrior, Thief, Mage, Priest) :"},
            {"CreateHeroName", "Name your hero :"},
            // Classes
            {"Warrior", "Warrior"},
            {"Thief", "Thief"},
            {"Mage", "Mage"},
            {"Priest", "Priest"},
       };
    }

}
