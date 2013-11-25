package game;

import java.awt.Graphics;

public abstract class Item {
	public void create(int x,int y){}
	public void move(){}
	public void draw(Graphics g){}
	public int pointgotten(){return 0;}
	public boolean corridewithjiki(){return false;}

}
