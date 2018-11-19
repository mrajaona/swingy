package mrajaona.swingy.game;

import java.util.Scanner; // user input on console

public class Swingy {

    public static void main(String[] args) {
        System.out.println("Hello Maven!" + System.lineSeparator() + "Welcome to Swingy!"); // DEBUG
        
        String	userInput;
        Scanner	inputScanner = new Scanner(System.in);
             
        userInput = inputScanner.nextLine(); // wait for user input
        
        System.out.println("You wrote: " + userInput);
    }
	
}
