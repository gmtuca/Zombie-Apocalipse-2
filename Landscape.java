package Zombie;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Landscape
{
  private String fileLocation = "landscape.png";
  private int x, y;
  private Image image;
  private int height, width;
  private Board board;

  public Landscape(Board board)
  {
    ImageIcon ii = new ImageIcon(this.getClass().getResource(fileLocation));
    image = ii.getImage();

    height = ii.getIconHeight();
    width = ii.getIconWidth();

    this.board = board;
    this.x = 0;
    this.y = 0;
  }//constructor

  public int getHeight(){
    return height;}

  public int getWidth(){
    return width;}

  public int getX() {
      return x;}

  public int getY() {
      return y;}

  public Image getImage() {
      return image;}

  public void forcedMove(int dx, int dy)
  {
    if(allowedToMoveHorizontally(dx))
      x += dx;

    if(allowedToMoveVertically(dy))
      y += dy;
  }//move

  public boolean allowedToMoveHorizontally(int dx){
    return x + dx < 0 && x + dx > board.sizeX - width;
  }

  public boolean allowedToMoveVertically(int dy){
    return y + dy < 0 && y + dy > board.sizeY - height;
  }


}//class
