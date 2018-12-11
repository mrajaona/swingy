package mrajaona.swingy.game;

import mrajaona.swingy.elements.characters.enemy.EnemyController;
import mrajaona.swingy.elements.characters.hero.HeroController;
import mrajaona.swingy.save.DatabaseManager;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class Swingy {

    @Getter(AccessLevel.PROTECTED) @Setter(AccessLevel.PROTECTED)
    private static DatabaseManager dbManager       = null;

    @Getter(AccessLevel.PROTECTED) @Setter(AccessLevel.PROTECTED)
    private static HeroController heroController   = null;

    @Getter(AccessLevel.PROTECTED) @Setter(AccessLevel.PROTECTED)
    private static EnemyController enemyController = null;
}
