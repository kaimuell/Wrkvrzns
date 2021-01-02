package tests;

import adressbook.view.AdressBookFrame;

public class ABFrameTest {
    public static void main(String[] args){
        new Thread(() -> {
            new AdressBookFrame();
        }).start();

    }

}
