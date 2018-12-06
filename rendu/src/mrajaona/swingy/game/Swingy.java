package mrajaona.swingy.game;

import mrajaona.swingy.elements.characters.hero.HeroController;
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

        HeroFactory hFactory          = HeroFactory.getFactory();
        HeroController heroController = hFactory.newHero();

        heroController.updateView();

    }
    
}
