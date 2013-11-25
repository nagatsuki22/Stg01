package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class DirectionalBullet extends Bullet{
    private double vx, vy;
    private double speed;
    private static Image bulletImage;
    
    public DirectionalBullet() {
    	width = 8;
    	height = 8;
        x = y = -10;
        vx = vy = 0;
        speed = 5;
        type = 0;

        used = false;
        ImageIcon icon = new ImageIcon(getClass().getResource("enemybullet.png"));
        bulletImage = icon.getImage();
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
    
    public void shot(int x,int y,double direction){
    	this.x = x;
    	this.y = y;
        vx = Math.cos(direction) * speed;
        vy = Math.sin(direction) * speed;
        used = true;
    }

    public void shot(Point start, Point target) {
    	System.out.println("yamete");
    }

    public void move() {
        x += vx;
        y += vy;
        if (x < 0 || x > Render.WIDTH || y < 0 || y > Render.HEIGHT) {
            used = false;
        }
    }
    public void draw(Graphics g) {
        g.drawImage(bulletImage, (int)x, (int)y, null);
    }

    public boolean isUsed() {
        return used;
    }

}