package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class StraightBullet extends Bullet{
    protected double speed;
    private static Image bulletImage;
    public StraightBullet(){
    	x = -10;y = -10;
    	width = 8;height = 8;
    	speed = 10;
    	ImageIcon icon = new ImageIcon(getClass().getResource("enemybullet.png"));
    	bulletImage = icon.getImage();
    }
	public void move() {
		x = x - speed/3;
		if (x < 0 || x > Render.WIDTH || y < 0 || y > Render.HEIGHT) {
            used = false;
        }
	}
	public boolean isUsed() {
		return used;
	}
	public void setUnused(){
		used = false;
	}
	public void draw(Graphics g) {
        g.drawImage(bulletImage, (int)x, (int)y, null);
	}
	public void shot(Point start, Point target) {
        x = start.x;
        y = start.y;
        used = true;
	}
	public int getX(){return (int)x;}
	public int getY(){return (int)y;}
	public int getWidth(){return width;}
	public int getHeight(){return height;}

}
