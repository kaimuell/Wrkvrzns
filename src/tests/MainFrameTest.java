package tests;

import view.MainFrame;

import javax.swing.*;

public class MainFrameTest {
    public static void main(String[] args){
        new Thread(new Runnable() {
            @Override
            public void run() {
                MainFrame mainframe = new MainFrame();
            }
        }).start();
    }
}
