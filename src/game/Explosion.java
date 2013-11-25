package game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
//爆発エフェクト　ドット絵描く時間なかったのでいろいろあれである
public class Explosion {
	private int x;
	private int y;
	Image image;
	int width = 4;
	int height = 4;
	boolean exploding = false;
	public Explosion(){
		x = -45;
		y = -45;
		ImageIcon icon = new ImageIcon(getClass().getResource("explosion.png"));
		image = icon.getImage();
	}
	
	public void draw(Graphics g){
		g.drawImage(image,x,y,width,height,null);
	}
	
	public void animate(){
		if(exploding){
			width += 2;height += 2;
			if(width == 128){
				exploding = false;
				height = 4;
				width = 4;
			}
		}
		else{ x = -45;y=-45;}
	}
	
	public void explode(int x,int y){
		this.x = x;
		this.y = y;
		exploding = true;
		
	}

}
