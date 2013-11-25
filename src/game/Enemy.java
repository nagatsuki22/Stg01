package game;

import java.awt.Graphics;

public abstract class Enemy {
	protected int health;
	boolean isBoss;
	public int x;
	public int y;
	protected int SPEED;
	protected Render render;
	public boolean isAlive = false;
	protected int width;
	protected static int point;
	protected int height;
    protected boolean used;
	public boolean corridewith(Bullet bullet){
		return true;
	}
	public void makeThisAlive(){
		
	}
	public void damage(Bullet bullet,Render render){
		
	}
	public boolean corridewithjiki(Jiki jiki){
		return true;
	}
	public int getPoint(){
		return 0;
	}
	public void move(){
	}
	public void neutralize(){
		
	}
	public void draw(Graphics g) {
    }
	

}
