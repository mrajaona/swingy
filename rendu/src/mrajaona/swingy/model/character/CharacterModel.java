package mrajaona.swingy.model.character;

import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.SwingUtilities;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.character.CharacterData;
import mrajaona.swingy.data.character.EnemyData;
import mrajaona.swingy.data.character.HeroData;
import mrajaona.swingy.exception.DataException;
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

    public static void loseHP(CharacterData character, int amount) throws InvalidViewTypeException, DataException {
        if (amount <= 0) {
            amount = 1;
        }

        String identity;

        if (character instanceof EnemyData)
            identity = ((EnemyData) character).getEnemyType();
        else if (character instanceof HeroData)
            identity = ((HeroData) character).getHeroName();
        else {
            throw (new DataException());
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

    private static void doAttack(CharacterData attacker, CharacterData target, String identity) throws InvalidViewTypeException, DataException {
        String msg = String.format(
            ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() ).getString("msgAttack"),
            identity // %1$s
        );
        MainHelper.printMsg(msg);

        Random rand     = new Random();

        if (rand.nextInt(10) == 0) { // miss
            msg = String.format(
                ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() ).getString("msgMissed"),
                identity // %1$s
            );
            MainHelper.printMsg(msg);
        } else {
            loseHP(target, attacker.getAttack() - target.getDefense());
        }
    }

    public static void attack(final CharacterData attacker, final CharacterData target) throws InvalidViewTypeException, DataException {
        String identity;

        if (attacker instanceof EnemyData)
            identity = ((EnemyData) attacker).getEnemyType();
        else if (attacker instanceof HeroData)
            identity = ((HeroData) attacker).getHeroName();
        else {
            throw (new DataException());
        }

        if (SwingUtilities.isEventDispatchThread()) { // sleep freezes the GUI
            doAttack(attacker, target, identity);
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            doAttack(attacker, target, identity);
        }

        if (target instanceof HeroData)
            MainHelper.update();
    }

    public static void fight(CharacterData fighter1, CharacterData fighter2) throws InvalidViewTypeException, DataException {
        if (fighter1 == null || fighter2 == null) {
            throw (new DataException());
        }

        attack(fighter1, fighter2);
        if (!isDead(fighter2))
            attack(fighter2, fighter1);
    }

}
