package mrajaona.swingy.game;

import mrajaona.swingy.elements.characters.enemy.EnemyFactory;
import mrajaona.swingy.elements.characters.hero.Hero;
import mrajaona.swingy.elements.characters.hero.HeroFactory;

public class Swingy {

    public static void main(String[] args) {
        System.out.println("Hello Maven!" + System.lineSeparator() + "Welcome to Swingy!"); // DEBUG
        
        /*
        String  userInput;
        Scanner inputScanner = new Scanner(System.in);
             
        userInput = inputScanner.nextLine(); // wait for user input
        
        System.out.println("You wrote: " + userInput);
        */

        EnemyFactory eFactory   = EnemyFactory.getFactory();
        HeroFactory hFactory    = HeroFactory.getFactory();
        eFactory.newEnemy("Slime", 1);
        Hero hero = hFactory.newHero();

        System.out.println(
            "Name       : " + hero.getHeroName() + System.lineSeparator() +
            "Class      : " + hero.getHeroClass() + System.lineSeparator() +
            "Level      : " + hero.getLevel() + System.lineSeparator() +
            "Experience : " + hero.getExperience() + System.lineSeparator() +
            "Base Atk   : " + hero.getBaseAttack() + System.lineSeparator() +
            "Base Def   : " + hero.getBaseDefense() + System.lineSeparator() +
            "Base HP    : " + hero.getBaseHitPoints()
		);
        
    }
    
}
