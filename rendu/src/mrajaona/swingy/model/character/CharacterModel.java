package mrajaona.swingy.model.character;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.character.CharacterData;
import mrajaona.swingy.data.character.EnemyData;
import mrajaona.swingy.data.character.HeroData;
import mrajaona.swingy.exception.InvalidViewTypeException;
import mrajaona.swingy.view.helper.MainHelper;

public class CharacterModel {

    private CharacterModel() {}

    // manage HP

    public static void fullRecover(CharacterData character) {
        character.setHitPoints(character.getMaxHitPoints());
    }

    public static void recoverHP(CharacterData character, int amount) {
        character.setHitPoints(character.getHitPoints() + amount);
        if (character.getHitPoints() > character.getMaxHitPoints())
            character.setHitPoints(character.getMaxHitPoints());
    }

    public static void loseHP(CharacterData character, int amount) throws InvalidViewTypeException {
        if (amount <= 0) {
            amount = 1;
        }

        String identity;

        if (character instanceof EnemyData)
            identity = ((EnemyData) character).getEnemyType();
        else if (character instanceof HeroData)
            identity = ((HeroData) character).getHeroName();
        else {
            // Exception
            return ;
        }

        String msg = String.format(
            ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() ).getString("msgDamage"),
            identity, // %1$s
            amount
        );
        MainHelper.printMsg(msg);

        character.setHitPoints(character.getHitPoints() - amount);
        if (character.getHitPoints() < 0) {
            character.setHitPoints(0);
        }
    }

    public static boolean isDead(CharacterData character) {
        return (character.getHitPoints() == 0 ? true : false);
    }

    // battle

    public static void attack(CharacterData attacker, CharacterData target) throws InvalidViewTypeException {
        String identity;

        if (attacker instanceof EnemyData)
            identity = ((EnemyData) attacker).getEnemyType();
        else if (attacker instanceof HeroData)
            identity = ((HeroData) attacker).getHeroName();
        else {
            // Exception
            return ;
        }

        // TODO : wait here

        String msg = String.format(
            ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() ).getString("msgAttack"),
            identity // %1$s
        );
        MainHelper.printMsg(msg);

        loseHP(target, attacker.getAttack() - target.getDefense());
    }

    public static void fight(CharacterData fighter1, CharacterData fighter2) throws InvalidViewTypeException {
        if (fighter1 == null || fighter2 == null) {
            // Exception
            return ;
        }

        attack(fighter1, fighter2);
        if (!isDead(fighter2))
            attack(fighter2, fighter1);
        // update gui
    }

}
