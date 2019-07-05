package ui;

public class main {
    public static void main(String[] args) {

        System.out.println("is it me?");
        
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
