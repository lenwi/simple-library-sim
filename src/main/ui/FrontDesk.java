package ui;

import model.library.Book;
import model.library.Library;
import model.library.Newspaper;
import model.members.Member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FrontDesk {

    private static final String SIGN_UP = "sign up";
    private static final String CHECK_MEMBERS = "check";
    private static final String BROWSE = "browse";
    private static final String NEWS = "news";
    private static final String BORROW = "borrow";
    private static final String RETURN = "return";
    private static final String QUIT = "quit";
    private static final String FANTASY = "Fantasy";
    private static final String MYSTERY = "Mystery";
    private static final String CHILDREN_BOOK = "Children's book";
    private static final String HORROR = "Horror";

    private Scanner input;
    private boolean runProgram;
    private Library library;
    private Newspaper newspaper;
    private Member member;

    public FrontDesk(Library library) {
        input = new Scanner(System.in);
        runProgram = true;
        this.library = library;
        this.newspaper = new Newspaper("UBC NEWS", "01", 10);
    }

    // EFFECTS: parses user input until user quits
    public void handleInput() throws IOException {
        System.out.println("What would you like to do?");
        printInstructions();
        String str;

        while (runProgram) {
            if (input.hasNext()) {
                str = input.nextLine();
                str = makeString(str);
                parseInputOne(str);
            }
        }
    }

    private void printInstructions() {
        System.out.println("\nEnter: \n'" + SIGN_UP + "' to activate a library account.");
        System.out.println("'" + CHECK_MEMBERS + "' to find other members and any books they have borrowed.");
        System.out.println("'" + BROWSE + "' to browse books.");
        System.out.println("'" + NEWS + "' to read the news.");
        System.out.println("'" + BORROW + "' to check out a book.");
        System.out.println("'" + RETURN + "' to return a book.");
        System.out.println("\n'" + QUIT + "' to quit.");
    }

    // EFFECTS: prints menu options and info depending on user input
    private void parseInputOne(String str) throws IOException {
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
                default:
                    parseInputTwo(str);
            }
        }
    }

    // EFFECTS: prints menu options and info depending on user input
    private void parseInputTwo(String str) throws IOException {
        if (str.length() > 0) {
            switch (str) {
                case NEWS:
                    readNews();
                    break;
                case BORROW:
                    borrowBook();
                    break;
                case RETURN:
                    returnBook();
                    break;
                case QUIT:
                    runProgram = false;
                    break;
                default:
                    System.out.println("Wrong input, try again.");
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: sign up using prompts and user inputs
    private void signUpMember() throws IOException {
        System.out.println("To set up your account, \nPlease enter a username.");
        String name = input.nextLine();
        member = new Member(name);
        System.out.println("Next, please enter your age so we can place you in the appropriate"
                + " age group.");
        if (input.hasNextInt()) {
            member.sortAge(input.nextInt());

            library.addMember(member);
            library.addMemberHash(name);
            library.writeFile("members.txt");

            System.out.println(member.getName() + " has been signed up in the: "
                    + member.getAgeGroup()
                    + " group.");

            printInstructions();
        } else {
            System.out.println("Sorry wrong input, try again.");

            printInstructions();
        }
    }

    // EFFECTS: prints profiles of all members in the form Name: AgeGroup
    private void checkMembers() throws IOException {
        System.out.println("Username: Age||\n");
        library.readFile("members.txt");
        printInstructions();
    }

    // EFFECTS: prints all books currently in the library in the form Title: Genre
    private void browseBooks() {
        System.out.println("Title: Genre||\n");
        for (Book b: library.getBooks()) {
            System.out.println(b.getTitle() + ": " + b.getCategory());
        }
        printInstructions();
    }

    // EFFECTS: takes out a newspaper
    private void readNews() {
        try {
            newspaper.readFile("newspaper.txt");
        } catch (IOException e) {
            System.out.println("File not found, returning to menu.");
        }
        newspaper.takeNewspaper();
        try {
            newspaper.writeFile("newspaper.txt");
        } catch (IOException e) {
            System.out.println("File not found, returning to menu.");
        } finally {
            System.out.println("\n \n Enter any key to go back to the front desk.");
            input.nextLine();
            printInstructions();
        }
    }

    private void borrowBook() {
        System.out.println("Please enter your username.");
        String user = input.nextLine();
        ArrayList<Book> bb = library.getBookList(user);
        if (library.containsMember(user)) {
            System.out.println("Here are the books you currently have borrowed: \n"
                    + library.displayBorrowedBooks(bb));
            System.out.println("Next, enter the title of the book you want to borrow.");
            String book = input.nextLine();
            library.borrow(user, library.findBook(book));
            System.out.println("Here you go, enjoy!");
        } else {
            System.out.println("You are not registered, please sign up!");
        }
        printInstructions();
    }

    private void returnBook() {
        System.out.println("Please enter your username.");
        String user = input.nextLine();
        ArrayList<Book> bb = library.getBookList(user);
        System.out.println("Your currently borrowed books are: \n" + library.displayBorrowedBooks(bb));
        System.out.println("Which book would you like to return?");
        String book = input.next();
        library.returnBook(user, library.findBorrowedBook(user, book));
        System.out.println("Thank you, have a nice day!");
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
