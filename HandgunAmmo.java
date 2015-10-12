package Zombie;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class HandgunAmmo extends Ammo
{
  public HandgunAmmo(int x, int y)
  {
    super(x,y);
    amount = 10;
  }//constructor

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
