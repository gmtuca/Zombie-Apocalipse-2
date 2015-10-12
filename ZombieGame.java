package Zombie;

import javax.swing.JFrame;

public class ZombieGame extends JFrame {

   public int sizeX = 500, sizeY = 500;

    public ZombieGame()
    {

        add(new Board(sizeX,sizeY));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(sizeX, sizeY);
        setLocationRelativeTo(null);
        setTitle("Zombie Game");
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ZombieGame();
    }
}
