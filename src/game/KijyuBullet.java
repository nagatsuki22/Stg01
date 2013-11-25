package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class KijyuBullet extends Bullet{
    private double vx, vy;
    private double speed;
    private static Image bulletImage;
    
    public KijyuBullet() {
    	damage = 100;
    	width = 8;
    	height = 8;
        x = y = -10;
        vx = vy = 0;
        speed = 20;
        type = 0;

        used = false;
        ImageIcon icon = new ImageIcon(getClass().getResource("bullet.png"));
        bulletImage = icon.getImage();
    }
    public int getDamage(){
    	return damage;
    }
    public int getX(){
    	int x = (int)this.x;
    	return x;
    	
    }
    public int getY(){
    	int y = (int)this.y;
    	return y;
    }
    public int getWidth(){
    	return width;
    }
    public int getHeight(){
    	return height;
    }
    public void setUnused(){
    	used = false;
    }

    public void shot(Point start, Point target) {
        x = start.x;
        y = start.y;
        double direction = Math.atan2(target.y - start.y, target.x - start.x);
        vx = Math.cos(direction) * speed;
        vy = Math.sin(direction) * speed;
        used = true;
    }

    public void move() {
        x += vx;
        y += vy;
        if (x < 0 || x > Render.WIDTH || y < 0 || y > Render.HEIGHT) {
            used = false;
        }
        if(!used){
        	x = -10;
        	y = -10;
        }
    }
    public void draw(Graphics g) {
        g.drawImage(bulletImage, (int)x, (int)y, null);
    }

    public boolean isUsed() {
        return used;
    }

}
