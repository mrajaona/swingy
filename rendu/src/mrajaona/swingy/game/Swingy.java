package mrajaona.swingy.game;

// import java.util.Scanner; // user input on console

import mrajaona.swingy.util.EnemyFactory;
import mrajaona.swingy.util.HeroFactory;

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
        hFactory.newHero();
    }
    
}
