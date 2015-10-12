package Zombie;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Zombie{

    private String fileLocation = "zombieDOWN.png";

    private int height, width;
    private int dx,dy;
    private int x, y;
    private float angle = 0;
    private Image image;
    private int middleX, middleY;
    private Board board;

    public Zombie(Board board)
    {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(fileLocation));
        image = ii.getImage();

        height = ii.getIconHeight();
        width = ii.getIconWidth();

        this.board = board;
        spawn();

    }//constructor

    private int zombieSpeedDelay = 0;
    private int SPEED_DELAY = 3;

    public void chasePlayer()
    {
      if(zombieSpeedDelay++ == SPEED_DELAY)
      {
        int goX = 0, goY = 0;

        if(this.x < board.player.getX())
          goX = 1;
        else
          goX = -1;

       if(this.y < board.player.getY())
          goY = 1;
        else
          goY = -1;

        move(goX,goY);

        zombieSpeedDelay = 0;
      }//if
    }//chase

    public void forcedMove(int dx, int dy)
    {
      if(board.landscape.allowedToMoveHorizontally(dx))
        x += dx;

      if(board.landscape.allowedToMoveVertically(dy))
       y += dy;
    }//move

    public void move(int dx, int dy)
    {
      x += dx;
      y += dy;
    }//move

    public void spawn()
    {
      double rand = Math.random();
      int randomX = (int)(Math.random() * board.landscape.getWidth());
      int randomY = (int)(Math.random() * board.landscape.getHeight());

      if(rand <= 0.25)
      {
        this.x = 0;
        this.y = randomY;
      }
      else if(rand <= 0.50)
      {
        this.x = randomX;
        this.y = 0;
      }
      else if(rand <= 0.75)
      {
        this.x = board.sizeX;
        this.y = randomY;
      }
      else
      {
        this.x = randomX;
        this.y = board.sizeY;
      }//else


        middleX = this.x + width/2;
        middleY = this.y + height/2;
    }//spawn

    public void setAngle(float angle){
    this.angle = angle;}

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
        return new Rectangle(x, y, width, height);}

}
