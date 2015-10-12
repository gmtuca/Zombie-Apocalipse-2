package Zombie;

import java.util.HashSet;
import java.util.Set;

public class Handgun extends Gun
{
    public Handgun(Player owner)
    {
      super(owner);
      ammo = 25;
    }//constructor

    public void shoot(float angle)
    {
      if(--ammo > 0)
        bullets.add(new Bullet(owner.getMiddleX(),owner.getMiddleY(),angle));
      else
        ammo = 0;
    }//shoot

    public void addAmmo(int moreAmmo){
      ammo += moreAmmo; }

    public String toString() {
      return "Handgun"; }

    public String shortStr(){
      return "Hg: " + ammo;}

}//class
