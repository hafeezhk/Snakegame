package SnakeGame;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    JFrame frame;
    Main(){
        frame = new JFrame("Snake Game");
        frame.setBounds(10, 10,905,700);
        GamePanel panel = new GamePanel();
        panel.setBackground(Color.GRAY);
        frame.add(panel);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
    public static void main(String[] args) {
        Main main = new Main();


        //System.out.println("Hello world!");
    }
}