package game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

public abstract class Bullet {
	protected int damage;
	protected double x, y;
    protected int width;
    protected int height;
    protected int type;
    protected boolean used;
	public int targetx;
	public int targety;
	public void move() {}
	public void move(MouseEvent e) {}
	public int getDamage(){return 0;}
	public boolean isUsed() {
		return used;
	}
	public void draw(Graphics g) {
	}
	public void setUnused(){
		
	}
	public void jibaku(){
		
	}
	public void shot(Point start, Point target) {
	}
	public void shot(int x,int y,double direction) {
	}
	
	public int getX(){return 0;}
	public int getY(){return 0;}
	public int getWidth(){return 0;}
	public int getHeight(){return 0;}
}
