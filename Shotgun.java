package Zombie;

import java.util.HashSet;
import java.util.Set;

public class Shotgun extends Gun
{
    public Shotgun(Player owner)
    {
      super(owner);
      ammo = 10;
    }//constructor

    public void shoot(float angle)
    {
      if(--ammo > 0)
      {
        int spacing = 10;
        bullets.add(new Bullet(owner.getMiddleX(),owner.getMiddleY(),angle - 2 * spacing));
        bullets.add(new Bullet(owner.getMiddleX(),owner.getMiddleY(),angle - spacing));
        bullets.add(new Bullet(owner.getMiddleX(),owner.getMiddleY(),angle));
        bullets.add(new Bullet(owner.getMiddleX(),owner.getMiddleY(),angle + spacing));
        bullets.add(new Bullet(owner.getMiddleX(),owner.getMiddleY(),angle + 2 * spacing));
      }//if
      else
        ammo = 0;
    }//shoot

    public String toString() {
      return "Shotgun"; }

    public String shortStr(){
      return "Sg: " + ammo;}

}//class
