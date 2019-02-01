package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

import mrajaona.swingy.util.Util;

public class EnemyResource_fr extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* Strings */
            {Util.ENEMY_SLIME, "slime"},
            {Util.ENEMY_GOBLIN, "goblin"},
            {Util.ENEMY_KOBOLD, "kobold"},
            {Util.ENEMY_PIXIE, "pixie"},
            {Util.ENEMY_METAL_SLIME, "slime de métal"},
            {Util.ENEMY_ORC, "orc"}
       };
    }

}
