package ui;

import java.util.Observable;
import java.util.Observer;

public class NewMember implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("New member: \n" + arg);
    }
}
