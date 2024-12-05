package fr.exatio.game;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        GamePanel panel = new GamePanel();

        window.add(panel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("My Little Knight");

        window.setVisible(true);

        panel.startGameThread();

    }

}