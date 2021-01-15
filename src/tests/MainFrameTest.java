package tests;

import gui.MainFrame;

import javax.swing.*;


public class MainFrameTest {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
        /*
        new Thread(() -> {
            new MainFrame();
        }).start();

         */
    }
}
