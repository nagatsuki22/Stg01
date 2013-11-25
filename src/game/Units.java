package game;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class Units {
	public int x;
	public int y;
	public Jiki jiki;
	public Render render;
	public void draw(Graphics g,Jiki jiki){};
	public void fire(MouseEvent e, Jiki jiki,int part){};
	public void specialfire(MouseEvent e,Jiki jiki,int part){};

}
