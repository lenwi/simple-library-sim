package ui;

import model.library.Book;
import model.library.Library;
import model.members.Member;

import java.util.Scanner;

public class FrontDesk {

    private static final String SIGN_UP = "sign up";
    private static final String CHECK_MEMBERS = "check";
    private static final String BROWSE = "browse";
    private static final String BORROW = "borrow";
    private static final String RETURN = "return";
    private static final String QUIT = "quit";

    private Scanner input;
    private boolean runProgram;
    private Library library;
    private Member m;

    public FrontDesk(Library library) {
        input = new Scanner(System.in);
        runProgram = true;
        this.library = library;
    }

    // EFFECTS: parses user input until user quits
    public void handleInput() {
        System.out.println("What would you like to do?");
        printInstructions();
        String str;

        while(runProgram) {
            if(input.hasNext()) {
                str = input.nextLine();
                str = makeString(str);
                parseInput(str);
            }
        }
    }

    private void printInstructions() {
        System.out.println("\nEnter: \n'"+ SIGN_UP +"' to activate a library account.");
        System.out.println("'"+ CHECK_MEMBERS +"' to find other members and any books they have borrowed.");
        System.out.println("'"+ BROWSE +"' to browse books.");
        System.out.println("'"+ BORROW +"' to check out a book.");
        System.out.println("'"+ RETURN +"' to return a book.");
        System.out.println("\n'"+ QUIT +"' to quit.");
    }

    // EFFECTS: prints menu options and info depending on user input
    private void parseInput(String str) {
        if (str.length() > 0) {
            switch (str) {
                case SIGN_UP:
                    signUpMember();
                    break;
                case CHECK_MEMBERS:
                    checkMembers();
                    break;
                case BROWSE:
                    browseBooks();
                    break;
//                case BORROW:
//                    borrowBook();
//                    break;
//                case RETURN:
//                    returnBook();
//                    break;
                case QUIT:
                    runProgram = false;
                    break;
                default:
                    System.out.println("Wrong input, try again.");
                    break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sign up using prompts and user inputs
    private void signUpMember() {
        System.out.println("To set up your account, \nPlease enter your name.");
        String name = input.nextLine();
        m = new Member(name);
        System.out.println("Next, please enter your age so we can place you in the appropriate" +
                " age group.");
        m.sortAge(input.nextInt());

        library.addMember(m);

        System.out.println(m.getName() + " has been signed up in the: " + m.getAgeGroup() +
                " group.");
        printInstructions();
    }

    // EFFECTS: prints profiles of all members in the form Name: AgeGroup
    private void checkMembers() {
        System.out.println("Name: Age||\n");
        for (Member m: library.getMembers()) {
            System.out.println(m.getName() + ": " + m.getAgeGroup());
        }
        printInstructions();
    }

    // EFFECTS: prints all books currently in the library in the form Title: Genre
    private void browseBooks() {
        System.out.println("Title: Genre||\n");
        for (Book b: library.getBooks()) {
            System.out.println(b.getTitle() + ": " + b.getGenre());
        }
        printInstructions();
    }


    // EFFECTS: removes white space and quotation around string
    private String makeString(String s) {
        s = s.toLowerCase();
        s = s.trim();
        s = s.replaceAll("\"|\'", "");
        return s;
    }

    // EFFECTS: stops receiving user input
    public void endProgram() {
        System.out.println("[QUITING]");
        input.close();
    }

}
