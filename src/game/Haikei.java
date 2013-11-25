package game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Haikei {
	private Image haikeiimg;
	private int height;
	private int width;
	public int pos = 0;
	public Haikei(Render render){
        ImageIcon icon = new ImageIcon(getClass().getResource("haikei.png"));
        haikeiimg = icon.getImage();
        width = haikeiimg.getWidth(render);
        height = haikeiimg.getHeight(render);
	}
	public void draw(Graphics g){
		g.drawImage(haikeiimg,0,0,width/2,height,pos,0,pos+1000,height,null);
	}

}
