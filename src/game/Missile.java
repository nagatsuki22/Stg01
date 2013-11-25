package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Missile extends Bullet {
	private int speed = 10;
	private double vy;
	private boolean isExploding = false;
	private long explodedtime = 0;
	private static Image bulletImage;
	public Explosion exp;
	public Missile(){
		exp = new Explosion();
		damage = 300;
		type = 1;
		width = 10;
		height = 10;
		ImageIcon icon = new ImageIcon(getClass().getResource("missile.png"));
    	bulletImage = icon.getImage();
	}
    
    public int getDamage(){
    	return damage;
    }
	public void move(){

		exp.animate();
		if(used&&!isExploding){
	        double direction = Math.atan2(targety - y, targetx - x);
	        vy = Math.sin(direction) * speed;
	        x += speed;
	        y += vy;
	        }
		if(!used) {x = -50;y = -50;}
		
		if (x < 0 || x > Render.WIDTH || y < 0 || y > Render.HEIGHT) {
            used = false;
        }
		
		if(isExploding){
			if(System.currentTimeMillis() - explodedtime>1000){
				isExploding = false;
				used = false;
			}
		}
		
		
	}
	public void jibaku(){
		exp.explode((int)x,(int)y);
		explodedtime = System.currentTimeMillis();
		isExploding = true;
		width = 100;
		height = 100;
		used = false;
	}
	
	public boolean isUsed() {
		return used;
	}
	public void draw(Graphics g) {
        g.drawImage(bulletImage, (int)x, (int)y, null);
        exp.draw(g);
	}
	public void setUnused(){
		used = false;
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
