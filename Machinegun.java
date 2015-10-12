package Zombie;

import java.util.HashSet;
import java.util.Set;

public class Machinegun extends Gun
{
    public Machinegun(Player owner)
    {
      super(owner);
      ammo = 15;
    }//constructor

    public synchronized void shoot(float angle)
    {
      if(--ammo > 0)
      {
        int spacing = 5;
        try{
          bullets.add(new Bullet(owner.getMiddleX(),owner.getMiddleY(),angle));
          bullets.add(new Bullet(owner.getMiddleX(),owner.getMiddleY(),angle));
          bullets.add(new Bullet(owner.getMiddleX(),owner.getMiddleY(),angle));
          bullets.add(new Bullet(owner.getMiddleX(),owner.getMiddleY(),angle));
          bullets.add(new Bullet(owner.getMiddleX(),owner.getMiddleY(),angle));
        }catch(Exception e){
          System.err.println(e);
        }
      }//if
      else
        ammo = 0;
    }//shoot

    public String toString() {
      return "Machinegun"; }

    public String shortStr(){
      return "Mg: " + ammo;}

}//class
