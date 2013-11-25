package game;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Boss extends Enemy{
	public Bullet[] bulletcase = new Bullet[500];
	Image Image;
	int movecount = 0;
	long lastmovedtime = 0;
	long lastshottime = 0;
	int syokix;
	int syokiy;
	int legy;
	boolean canGensoHubi = true;
	boolean canHighSpeedMove2 = true;
	Render render;
	public boolean corridewith(Bullet bullet){
		Rectangle rectB = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
		Rectangle rectE = new Rectangle(x, y, width, height);
        if(rectB.intersects(rectE))
        	return true;
        else
        	return false;

	}
	public boolean corridewithjiki(Jiki jiki){
		boolean b = false;
		for(int i=0;i<bulletcase.length;i++){
			Rectangle rectB = new Rectangle(bulletcase[i].getX(), bulletcase[i].getY(), bulletcase[i].getWidth(), bulletcase[i].getHeight());
			Rectangle rectE = new Rectangle(jiki.getX(), jiki.getY(), jiki.getWidth(), jiki.getHeight());
	        if(rectB.intersects(rectE)){
	        	b =  true;
	        	break;
	        }
		}
		return b;
	}
	public Boss(int x,int y,Render render){
		isAlive = false;
		this.x = -50;
		this.y = -50;
		syokix = x;
		syokiy = y;
		width = 50;
		height = 50;
		SPEED = 10;
		this.render = render;
		for(int i=0;i<bulletcase.length;i++){
			bulletcase[i] = new DirectionalBullet();
		}
		isBoss =true;
		health = 50000;
		ImageIcon icon = new ImageIcon(getClass().getResource("boss.png"));
		Image = icon.getImage();
	}
	public void neutralize(){
		isAlive = false;
	}
	
	public void makeThisAlive(){
		if(isAlive)return;
		isAlive = true;
		x = syokix;
		y = syokiy;
		legy = y;
	}
	
	public int getHealth(){
		return health;
	}
	
	public void move(){
		if(isAlive){
		if(health > 37500){
			if(System.currentTimeMillis() - lastmovedtime > 3000){
				if(legy > 600)y -= SPEED;
				if(legy<100)y+= SPEED;
				if(y - legy>600||legy - y>600){
					lastmovedtime = System.currentTimeMillis();
					legy = y;
				}
			}
			if(System.currentTimeMillis() - lastshottime > 2000){
				shotRound();
				lastshottime = System.currentTimeMillis();
			}
		}
		else if(health>25000){
			if(SPEED < 100&&canHighSpeedMove2 == true){
			SPEED = SPEED+5;}
			if(SPEED > 100){
				canHighSpeedMove2 = false;
			}
			if(!canHighSpeedMove2){
				SPEED = SPEED -1;
			}
			if(SPEED <= 10){
				canHighSpeedMove2 = true;
			}
			if(System.currentTimeMillis() - lastmovedtime > 1000){
				if(legy > 600)y -= SPEED;
				if(legy<100)y+= SPEED;
				if(y - legy>600||legy - y>600){
					lastmovedtime = System.currentTimeMillis();
					legy = y;
				}
			}
			if(System.currentTimeMillis() - lastshottime > 1000){
				shotRound();
				lastshottime = System.currentTimeMillis();
			}
		}
		else if (health>12500){
			if(SPEED < 100&&canGensoHubi == true){
			SPEED = SPEED+5;}
			if(SPEED > 100){
				canGensoHubi = false;
			}
			if(!canGensoHubi){
				SPEED = SPEED -1;
			}
			if(SPEED <= 10){
				canGensoHubi = true;
			}
			if(System.currentTimeMillis() - lastmovedtime > 100){
				if(legy > 600)y -= SPEED;
				if(legy<100)y+= SPEED;
				if(y - legy>600||legy - y>600){
					lastmovedtime = System.currentTimeMillis();
					legy = y;
				}
			}
			if(System.currentTimeMillis() - lastshottime > 10){
				shotBaramaki();
				lastshottime = System.currentTimeMillis();
			}
		}
		else if(health>0){
			if(SPEED < 100&&canGensoHubi == true){
			SPEED = SPEED+5;}
			if(SPEED > 100){
				canGensoHubi = false;
			}
			if(!canGensoHubi){
				SPEED = SPEED -1;
			}
			if(SPEED <= 10){
				canGensoHubi = true;
			}
			if(System.currentTimeMillis() - lastmovedtime > 100){
				if(legy > 600)y -= SPEED;
				if(legy<100)y+= SPEED;
				if(y - legy>600||legy - y>600){
					lastmovedtime = System.currentTimeMillis();
					legy = y;
				}
			}
			if(System.currentTimeMillis() - lastshottime > 5){
				shotBaramaki();
				lastshottime = System.currentTimeMillis();
			}
			
		}
		else if(health <0){
			isAlive = false;
			
		}
		}
        for(int j = 0;j<bulletcase.length;j++){
        	if(bulletcase[j].isUsed()){
        		bulletcase[j].move();
        	}
        }
        
		
	}
	
	public void shotRound(){
		int g = 0;
		for (int i = 0;g<50&&i<200;i++){
				if(!bulletcase[i].used){
				bulletcase[i].shot(x,y,2*Math.PI*(g+1)/(50));
				g++;
			}
		}
	}
	
	public void shotBaramaki(){
		int g = 0;
		double direct = 0;
		if(Math.random() >0.5){
			direct = 0 + Math.random()/10;
		}
		else
			direct = Math.PI + Math.random()/10;
		
		for (int i = 0;g<1&&i<200;i++){
				if(!bulletcase[i].used){
				bulletcase[i].shot(x,y,direct);
				g++;
			}
		}
	}
	
	public void draw(Graphics g){
        g.drawImage(Image, x, y, null);
        for(int j = 0;j<bulletcase.length;j++){
        	if(bulletcase[j].isUsed()){
        		bulletcase[j].draw(g);
        	}
        }
	}
	
    public void damage(Bullet bullet,Render render){
    	health -= bullet.getDamage();
    	if(health <= 0){
			int l = 0;
			int g = 0;
			while(g<100&&l<299){
				l++;
				if(!render.pointcase[l].isUsed){
					render.pointcase[l].create(this.x + (int)(10*Math.random()), this.y+(int)(10*Math.random()));
					g++;
				}
			}
			bullet.setUnused();
			render.bossDefeated = true;
			this.neutralize();
    	}
    }
}
