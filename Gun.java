package Zombie;

import java.util.HashSet;
import java.util.Set;
//import java.awt.Image;
//import javax.swing.ImageIcon;
//import java.awt.Rectangle;

public class Gun {

    protected Player owner;
    protected Set<Bullet> bullets = new HashSet<Bullet>();
    protected int ammo;

//    private int width, height;
//    private int x, y;
//    private float angle;
 //   private Image image;

 //   private String fileLocation = "bullet.png";

    public Gun(Player owner)
    {
      this.owner = owner;
    }

    public void shoot(float angle) {}//shoot

    public Player getOwner(){
      return owner;}

    public Set<Bullet> getBullets(){
      return bullets;}

    public String toString() {
      return "Gun"; }
  
    public String shortStr(){
      return "G: " + ammo ;}

    public int getAmmo() {
      return ammo; }

}//class
