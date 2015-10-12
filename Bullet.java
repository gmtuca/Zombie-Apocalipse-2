package Zombie;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Bullet {

    private int width, height;
    private int x, y;
    private float angle;
    private Image image;
    private Board board;

    private String fileLocation = "bullet.png";

    public Bullet(int x, int y, float angle)
    {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(fileLocation));
        image = ii.getImage();

        height = ii.getIconHeight();
        width = ii.getIconWidth();

        this.x = x;
        this.y = y;
        this.angle = angle;
        this.board = board;
    }//constructor

    public void move() {
        x += Math.cos(Math.toRadians(angle + 90.0)) * 8.0;
        y += Math.sin(Math.toRadians(angle + 90.0)) * 8.0;
    }//move

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }//getBounds

    public Image getImage() {
        return image;}

    public int getX() {
        return x;}

    public int getY() {
        return y;}
}//class
