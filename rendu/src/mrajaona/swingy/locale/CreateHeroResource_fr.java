package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

public class CreateHeroResource_fr extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            // {"Key", "Content"},
            {"CreateHeroClass", "Choisissez votre classe (Guerrier, Voleur, Magicien, Prêtre) :"},
            {"CreateHeroName", "Nommez votre héro :"},
            // Classes
            {"Warrior", "Guerrier"},
            {"Thief", "Voleur"},
            {"Mage", "Magicien"},
            {"Priest", "Prêtre"},
       };
    }

}
