package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Point{
	int x;
	Render render;
	int y;
	int width = 30;
	int height = 30;
	boolean isUsed = false;
	Image Image;
	public Point(Render render){
		x = -50;y= -50;
		this.render = render;
		ImageIcon icon = new ImageIcon(getClass().getResource("point.png"));
        Image = icon.getImage();
        
	}
	public void create(int x,int y){
		isUsed = true;
		this.x = x;
		this.y = y;
	}
	public void move(){
		x -= render.speed;
		if(!isUsed){
			x = -50;y =-50;
		}
	}
	public void draw(Graphics g){
		g.drawImage(Image,x,y,null);
	}
	
	public int pointgotten(){
		return 100;
	}
	
	public void setUnused(){
		isUsed = false;
	}
	
	public boolean corridewithjiki(Jiki jiki){
		Rectangle RectJ = new Rectangle(jiki.getX(),jiki.getY(),100,100);
		Rectangle RectP = new Rectangle(x,y,width,height);
		if (RectJ.intersects(RectP)){
			return true;
		}
		else return false;
	}
}
