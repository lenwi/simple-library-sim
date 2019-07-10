package ui;

public class Game {
    public static void main(String[] args) {
        //TODO create new game()
        //TODO set new game/load in

        System.out.println("Welcome to RPS Encounter!");
        //TODO ********* maybe this goes into interface
        System.out.println("Please enter your name: ");
        //TODO name input
        //TODO insert name into sout line
        System.out.println("Hello xxxx, what would you like to do?");

        //TODO check current streak

        //TODO check highest streak

        //TODO start new game

        // new game options
        //TODO get opponent into sout line
        System.out.println("Your current opponent is: " + "insert opp");
        System.out.println("What would you like to do?");

        System.out.println("Type 'check' to check the opponent's stats.");
        //TODO display opponent stats

        System.out.println("Type 'surrender' to surrender.");
        //TODO end current game (cstreak = 0)
        System.out.println("Thanks for playing!");
        //TODO **********************

        System.out.println("Type 'fight' to begin.");
        //TODO move to selection

        // player selection
        System.out.println("Please make your selection by typing: ");
        System.out.println("'rock'");
        System.out.println("'paper' or");
        System.out.println("'scissors'");

        //confirm selection
        System.out.println("Type 'confirm' to proceed or 'back' to reselect.");

        // fight display
        //TODO opponent throws out
        //TODO you throws out

        //TODO method to determine result
        //TODO display result
        //TODO loops back to find opponent
        
        IsMe(3);

        TryAgain();
    }

    private static void IsMe(int i) {
        if (i == 6){

            System.out.println("yes");
        }

        else {

            System.out.println("not me");
        }
    }

    private static void TryAgain() {

        System.out.println("try again");
    }
}
