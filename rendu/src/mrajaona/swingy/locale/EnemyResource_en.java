package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

import mrajaona.swingy.util.Util;

public class EnemyResource_en extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* Strings */
            {Util.ENEMY_SLIME, "slime"},
            {Util.ENEMY_GOBLIN, "goblin"},
            {Util.ENEMY_KOBOLD, "kobold"},
            {Util.ENEMY_PIXIE, "pixie"},
            {Util.ENEMY_METAL_SLIME, "metal slime"},
            {Util.ENEMY_ORC, "orc"}
       };
    }

}
