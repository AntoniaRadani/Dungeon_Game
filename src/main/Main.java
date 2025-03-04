package main;

import javax.swing.*;

public class Main {

    private static void createWindow(){
        //creating the windows
        JFrame windows = new JFrame();
        //the programs terminates when the windows in closed
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windows.setResizable(true);

        GamePanel gamePanel = new GamePanel();
        windows.add(gamePanel);

        //size the windows on its components (in our case to gamePanel)
        windows.pack();
        //center on screen
        windows.setLocationRelativeTo(null);
        //make it visible
        windows.setVisible(true);
    }

    public static void main(String[] args) {

        createWindow();

    }
}