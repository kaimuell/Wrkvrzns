package tests;

import view.MainFrame;


public class MainFrameTest {
    public static void main(String[] args){
        new Thread(() -> {
            new MainFrame();
        }).start();
    }
}
