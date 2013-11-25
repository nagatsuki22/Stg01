package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Wanderer extends Enemy{
	private long moveinterval = 200;
	private int x;
	public boolean isAlive = true;
	private int y;
	private long time1 = 0;
	private long time2 = 0;
	private int SPEED = 100;
	private int width;
	private int height;
	public static Image wandImage;
	public boolean corridewith(Bullet bullet){
		Rectangle rectB = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
		Rectangle rectE = new Rectangle(x, y, width, height);
        if(rectB.intersects(rectE))
        	return true;
        else
        	return false;

	}
	public Wanderer(int x,int y,Render render){
		this.x = x;
		this.y = y;
		ImageIcon icon = new ImageIcon(getClass().getResource("jiki.png"));
        wandImage = icon.getImage();
        width = wandImage.getWidth(render);
        height = wandImage.getHeight(render);
	}
	public void move(){
		if(isAlive){

		time2 = System.currentTimeMillis();
		if(time2 - time1 >= moveinterval){
			if(Math.random()<0.5)
				y -= SPEED;
			else y += SPEED;
			if (y < 0) {
				y = 0;
			}
			if (y > Render.HEIGHT) {
				y = Render.HEIGHT;
			}
			time1 = time2;}
		}
		if(!isAlive){
			x = -50;
			y = -50;
		}
		
	}
	public void draw(Graphics g) {
        g.drawImage(wandImage, x, y, null);
	}

}
