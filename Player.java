package Zombie;

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.awt.Rectangle;

public class Player
{
    private String fileLocation = "marineDOWN.png";

    private int height, width;
    private int dx,dy;
    private int x, y;
    private float angle = 0;
    private Image image;

    private int middleX, middleY;

    private Board board;

    private ArrayList<Gun> allGuns = new ArrayList<Gun>();
    private Gun currentGun;

    protected int currentGunIndex = 0;
    public Player(Board board)
    {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(fileLocation));
        image = ii.getImage();

        height = ii.getIconHeight();
        width = ii.getIconWidth();

        this.board = board;
        this.x = board.sizeX / 2;
        this.y = board.sizeY / 2;

        middleX = x + width/2;
        middleY = y + height/2;
    
        allGuns.add(new Handgun(this));
        allGuns.add(new Shotgun(this));
        allGuns.add(new Machinegun(this));

        currentGun = allGuns.get(currentGunIndex);
    }//constructor

    public void setAngle(float angle){
    this.angle = angle;}

    public ArrayList<Gun> getAllGuns() {
      return allGuns;}

    public Gun getCurrentGun(){
      return currentGun;}

    public void setCurrentGun(Gun gunToSet){
      this.currentGun = gunToSet;}

    public float getAngle(){
      return angle;}

    public int moveX(){
      return dx;}

    public int moveY(){
      return dy;}

    public int getHeight(){
      return height;}

    public int getWidth(){
      return width;}

    public int getMiddleX(){
      return middleX;}

    public int getMiddleY(){
      return middleY;}

    public int getX(){
        return x;}

    public int getY(){
        return y;}

    public Image getImage(){
        return image;}

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height); }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_1) { currentGun = allGuns.get(0); currentGunIndex = 0;}
        if (key == KeyEvent.VK_2) { currentGun = allGuns.get(1); currentGunIndex = 1;}
        if (key == KeyEvent.VK_3) { currentGun = allGuns.get(2); currentGunIndex = 2;}

        if (key == KeyEvent.VK_A) { dx = -1;}
        if (key == KeyEvent.VK_D) { dx =  1;}
        if (key == KeyEvent.VK_W) { dy = -1;}
        if (key == KeyEvent.VK_S) { dy =  1;}
    }//keyPressed

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A || key == KeyEvent.VK_D)
          dx = 0;
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_S)
          dy = 0;
    }//keyReleased
}
