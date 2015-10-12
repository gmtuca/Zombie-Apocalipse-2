package Zombie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.geom.AffineTransform;
import java.awt.Point;
import java.awt.MouseInfo;
import java.util.HashSet;
import java.util.Set;
import java.awt.Font;
import java.awt.FontMetrics;

public class Board extends JPanel implements ActionListener
{

    private Timer timer;
    public Player player;
    public Landscape landscape;
    public int sizeX = 0, sizeY = 0;

    private Set<Zombie> zombies = new HashSet<Zombie>();

    public Board()
    {
        addKeyListener(new TAdapter());
        addMouseWheelListener(new WeaponChanger());

        setFocusable(true);
        setDoubleBuffered(true);

        addMouseListener(new MouseShooting());

        timer = new Timer(5, this);
        timer.start();
    }

    public Board(int sizeX, int sizeY)
    {
      this();
      this.sizeX = sizeX;
      this.sizeY = sizeY;
      landscape = new Landscape(this);
      player = new Player(this);
    }

    AffineTransform identity = new AffineTransform();

    public void paint(Graphics g)
    {
      super.paint(g);
      Graphics2D g2d = (Graphics2D)g;

      handlePlayer(g2d);
      handleBullets(g2d);
      handleZombies(g2d);

      Toolkit.getDefaultToolkit().sync();
      g.dispose();
    }//paint

    public void actionPerformed(ActionEvent e)
    {
      //player.move();
      repaint();  
    }//ap

    public void handlePlayer(Graphics2D g2d)
    {
      landscape.forcedMove(-player.moveX(), -player.moveY());

      for(Zombie zombie : zombies)
      {
        zombie.forcedMove(-player.moveX(), -player.moveY());
        if(zombie.getBounds().intersects(player.getBounds()))
          gg();
      }//for

      g2d.drawImage(landscape.getImage(),landscape.getX(), landscape.getY(), this);

      Point mouseLocation = MouseInfo.getPointerInfo().getLocation();

      player.setAngle(findAngle(player,mouseLocation) - 90);

      AffineTransform trans = new AffineTransform();
      trans.setTransform(identity);
      trans.setToTranslation(player.getMiddleX() - player.getWidth(), player.getMiddleY() - player.getHeight());
      trans.rotate( Math.toRadians(player.getAngle()), player.getWidth()/2,player.getHeight()/2);

      g2d.drawImage(player.getImage(), trans, this);

      Font small = new Font("Helvetica", Font.BOLD, 14);
      FontMetrics metr = this.getFontMetrics(small);
      g2d.setColor(Color.WHITE);
      g2d.setFont(small);

      g2d.drawString(player.getCurrentGun().shortStr(), player.getX() - 15, player.getY() - 20);

      String ammoStrTop = "";
      int gunsC = 1;
      for(Gun gun : player.getAllGuns())
        ammoStrTop += "(" + (gunsC++) + ")" + gun.toString() + ": " + gun.getAmmo() + "  ";
      g2d.drawString(ammoStrTop, 5, 15);

    }//handlePlayer

    public void handleBullets(Graphics2D g2d)
    {
      Set<Bullet> removalSet = new HashSet<Bullet>();
      Set<Zombie> deadZombies = new HashSet<Zombie>();

      for(Bullet bullet : player.getCurrentGun().getBullets())
      {
        if(bullet != null)
        {
         g2d.drawImage(bullet.getImage(),bullet.getX(), bullet.getY(), this);

         if(insideTheField(bullet))
           bullet.move();
         else
           removalSet.add(bullet);

        for(Zombie zombie : zombies)
          if(bullet.getBounds().intersects(zombie.getBounds()))
          {
            zombiesKilledCount++;
            deadZombies.add(zombie);
            removalSet.add(bullet);
          }//if
        }//if
      }//for

      player.getCurrentGun().getBullets().removeAll(removalSet);
      zombies.removeAll(deadZombies);
    }//handleBullets

    private int zombieSpawn = 0;
    private int SPAWN_DELAY = 100;

    public void handleZombies(Graphics2D g2d)
    {
      if(zombieSpawn++ == SPAWN_DELAY)
      {
        Zombie zombieToSpawn = new Zombie(this);
        zombies.add(zombieToSpawn);

        zombieSpawn = 0;
      }//if zombieSpawn

      for(Zombie zombie : zombies)
      {
        zombie.chasePlayer();
        g2d.drawImage(zombie.getImage(), zombie.getX(), zombie.getY(), this);
      }//for

    }//handleZombies

    private int zombiesKilledCount = 0;
    public void gg()
    {
      System.out.println("YOU ARE DEAD MOTHER FUCKER");
      System.out.println("Zombies killed: " + zombiesKilledCount);
      System.exit(0);
    }//gg

    public static float findAngle(Player pla, Point poi) {
      return (float)Math.toDegrees(Math.atan2(poi.getY() - pla.getMiddleY(),poi.getX() - pla.getMiddleX())); }

    public boolean insideTheField(Bullet b) {
      return b.getX() >= 0 && b.getY() >= 0 && b.getX() <= sizeX && b.getY() <= sizeY; }

    private class TAdapter extends KeyAdapter
    {
      public void keyReleased(KeyEvent e) { player.keyReleased(e); }
      public void keyPressed(KeyEvent e) { player.keyPressed(e); }
    }//innerclass

    private class MouseShooting implements MouseListener
    {
      public void mousePressed(MouseEvent e) {
        player.getCurrentGun().shoot(player.getAngle()); }//mousePressed

      public void mouseExited(MouseEvent e){}
      public void mouseEntered(MouseEvent e){}
      public void mouseClicked(MouseEvent e){}
      public void mouseReleased(MouseEvent e){}
    }//innerclass

    public class WeaponChanger implements MouseWheelListener
    {
      public void mouseWheelMoved(MouseWheelEvent e)
      {
        int indexToGo = player.currentGunIndex + e.getWheelRotation();

        if(indexToGo < 0)
          indexToGo = player.getAllGuns().size() - 1;
        if(indexToGo >= player.getAllGuns().size())
          indexToGo = 0;

        player.currentGunIndex = indexToGo;
        player.setCurrentGun(player.getAllGuns().get(indexToGo));
      }//mouseWheelMoved
    }//innerclass

}//classa
