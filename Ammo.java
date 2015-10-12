package Zombie;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Ammo
{

    protected Image image;
    protected String fileLocation = "handgunAmmo.png";;
    protected int amount;
    protected int width, height;
    protected int x, y;

    public Ammo(int x, int y)
    {
      ImageIcon ii = new ImageIcon(this.getClass().getResource(fileLocation));
      image = ii.getImage();

      height = ii.getIconHeight();
      width = ii.getIconWidth();

      this.x = x;
      this.y = y;
    }//constructor

}//class
