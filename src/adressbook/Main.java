package adressbook;

import adressbook.view.AdressBookFrame;

public class Main {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AdressBookFrame abf = new AdressBookFrame();
            }
        }).start();
    }
}
