package game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener
{
	JLabel label;
	JLabel label2,label3;
	JPanel panel1;
	Render render;
	Menu menu;
	StartMenu startmenu;
	GameClear clearpanel;
	int pressedtime;
	int frame;
	CardLayout cl;
	int BossHealth = 0;
/*  29 */   static String[] trackname = { "mari.wav" };//WAV�`����BGM�Đ��ł����
/*  30 */   boolean renderMade = false;
/*  31 */   boolean renderFocused = false;
/*     */   long lasttime;
/*     */ 
/*     */   public MainPanel()
/*     */   {
/*  35 */     for (int i = 0; i < trackname.length; i++) {
/*     */       try {
/*  37 */         WavePlayer.load(trackname[i]);
/*     */       } catch (UnsupportedAudioFileException e) {
/*  39 */         e.printStackTrace();
/*     */       } catch (IOException e) {
/*  41 */         e.printStackTrace();
/*     */       } catch (LineUnavailableException e) {
/*  43 */         e.printStackTrace();
/*     */       }
/*     */     }
/*  46 */     addKeyListener(this);
/*  47 */     setFocusable(true);
/*  48 */     this.label = new JLabel(" ");
/*  49 */     this.label2 = new JLabel(" ");
this.label3 = new JLabel("");
/*  50 */     this.menu = new Menu();
/*  51 */     this.startmenu = new StartMenu();
clearpanel = new GameClear();
/*  52 */     setLayout(new BorderLayout());
/*  53 */     JPanel panel2 = new JPanel();
/*  54 */     this.panel1 = new JPanel();
/*  55 */     this.panel1.setPreferredSize(new Dimension(1000, 700));
/*  56 */     this.panel1.setBackground(Color.BLUE);
/*  57 */     this.cl = new CardLayout();
/*  58 */     this.panel1.setLayout(this.cl);
/*  59 */     panel2.setPreferredSize(new Dimension(280, 700));
/*  60 */     panel2.setBackground(Color.lightGray);
/*  61 */     panel2.setLayout(new BorderLayout());
/*  62 */     panel2.add(this.label, "North");
panel2.add(this.label3,"Center");
/*  63 */     panel2.add(this.label2, "South");
/*  64 */     add(panel2, "East");
/*  65 */     this.render = new Render();
/*  66 */     this.panel1.add(this.menu, "menu");
/*  67 */     this.panel1.add(this.render, "render");
clearpanel = new GameClear();
panel1.add(clearpanel,"clearpanel");
/*  68 */     this.menu.isFocused = true;
/*  69 */     this.panel1.add(this.startmenu, "startmenu");
/*  70 */     add(this.panel1, "West");
/*  71 */     setBackground(Color.white);
/*  72 */     setPreferredSize(new Dimension(1280, 720));
/*  73 */     Thread thread = new Thread(this);
/*  75 */     thread.start();
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/*  80 */     JFrame frame = new JFrame("STG 1");
/*  81 */     frame.setDefaultCloseOperation(3);
/*  82 */     MainPanel p = new MainPanel();
/*  83 */     frame.add(p);
/*  84 */     frame.pack();
/*  85 */     frame.setVisible(true);
/*     */   }
/*     */ 
public void run()
{
while (true)
if (System.currentTimeMillis() - this.lasttime >= 20L)
{
	this.lasttime = System.currentTimeMillis();
	if(render.bossDefeated){
		WavePlayer.stop(0);
		clearpanel.setResults(true,render.getPoint());
		cl.show(panel1,"clearpanel");
		clearpanel.isFocused = true;
		renderFocused = false;
		render.zanki = 3;
		render.pose();
	}
	if(render.zanki < 0){
		WavePlayer.stop(0);
		clearpanel.setResults(false,render.getPoint());
		cl.show(panel1,"clearpanel");
		clearpanel.isFocused = true;
		renderFocused = false;
		render.zanki = 3;
		render.pose();
	}

	if (clearpanel.isFocused) {
		this.label.setText("�������ł��@����������ǂ����Ō��J����");
		this.label2.setText("�X�e�[�W�����ƍ�鎞�Ԃ��Ȃ�����");}
	if (this.renderFocused) {
		this.label.setText("Point" + this.render.getPoint());
		this.label2.setText("�c�@ "+render.zanki);
		if (!this.render.jiki.isAlive()&&render.zanki > -1) {
			this.render.jiki.hukkatu();
			}
/* 106 */           this.render.time += 1;
/* 107 */           if (this.render.time > 150000) break;
/* 108 */           this.render.haikei.pos = ((this.render.haikei.pos + this.render.speed) % 1000);
					for (int i = 0;i<render.jikibullet.length;i++){
/*     */           for (int j = 0; j < this.render.unitkazu; j++){
/* 112 */               if (this.render.jikibullet[i][j].isUsed())
/* 113 */                 this.render.jikibullet[i][j].move();
/*     */             }}
/*     */ 
/* 118 */           for (int k = 0; k < this.render.pointcase.length; k++) {
/* 119 */             this.render.pointcase[k].move();
/* 120 */             if ((this.render.pointcase[k].corridewithjiki(this.render.jiki)) && (this.render.pointcase[k].isUsed)) {
/* 121 */               this.render.addPoint(this.render.pointcase[k].pointgotten());
/* 122 */               this.render.pointcase[k].setUnused();
/*     */             }
/*     */           }
/*     */ 
/* 126 */           this.render.corrisionDetection();
/*     */ 
/* 128 */           for (int l = 0; l < this.render.enemys.length; l++) {
	/* 129 */             if (this.render.time == this.render.load.stage[l][0]) {
	/* 130 */               this.render.enemys[l].makeThisAlive();
/*     */             }
/* 132 */             this.render.enemys[l].move();
/* 133 */             if ((this.render.enemys[l].corridewithjiki(this.render.jiki)) && (this.render.jiki.isAlive())) {
	/* 134 */               this.render.zanki -= 1;
	/* 136 */               this.render.jiki.neutralize();
/*     */             }
if(render.enemys[l] instanceof Boss&&render.enemys[l].isAlive){
	BossHealth = render.enemys[l].health;
	label3.setText("�{�X��HP "+BossHealth);
}
					}
/* 139 */           if (this.render.upKeyPressed) {
/* 140 */             this.render.getClass(); this.render.jiki.move(0);
/* 141 */           } else if (this.render.downKeyPressed) {
/* 142 */             this.render.getClass(); this.render.jiki.move(1);
/*     */           }
/* 144 */           if (this.render.rightKeyPressed) {
/* 145 */             this.render.getClass(); this.render.jiki.move(2);
/* 146 */           } else if (this.render.leftKeyPressed) {
/* 147 */             this.render.getClass(); this.render.jiki.move(3);
/*     */           }
/*     */ 
/* 150 */           this.render.repaint();
/*     */         }
/* 152 */         else if (this.menu.isFocused) {
/* 153 */           this.label.setText("Choose Start Game to Start");
/* 154 */           switch (this.menu.whereis) {
/*     */           case 0:
/* 156 */             this.menu.label1.setText(this.menu.default1);
/* 157 */             this.menu.label2.setText(">Setting");
/* 158 */             this.menu.label3.setText(this.menu.default3);
/* 159 */             break;
/*     */           case 1:
/* 161 */             this.menu.label1.setText(this.menu.default1);
/* 162 */             this.menu.label2.setText(this.menu.default2);
/* 163 */             this.menu.label3.setText(">Exit");
/* 164 */             break;
/*     */           case 2:
/* 166 */             this.menu.label1.setText(">Start Game");
/* 167 */             this.menu.label2.setText(this.menu.default2);
/* 168 */             this.menu.label3.setText(this.menu.default3);
/*     */           }
/*     */ 
/*     */         }
/* 172 */         else if (this.startmenu.isFocused) {
/* 173 */           this.label.setText("Choose Character");
/* 174 */           if (!this.startmenu.isSoubi) {
/* 175 */             switch (this.startmenu.whereis) {
/*     */             case 0:
/* 177 */               this.startmenu.label2.setText(">Marisa Kirisame");
/* 178 */               this.startmenu.label3.setText(this.startmenu.default2);
/* 179 */               break;
/*     */             case 1:
/* 181 */               this.startmenu.label2.setText(this.startmenu.default3);
/* 182 */               this.startmenu.label3.setText(">Back");
/*     */             default:
/* 185 */               break;
/*     */             }
/* 187 */           } else if (this.startmenu.ready) {
/* 188 */             this.startmenu.label5.setText(this.startmenu.default5);
/* 189 */             this.startmenu.label6.setText(this.startmenu.default6);
/* 190 */             this.startmenu.label7.setText(this.startmenu.default7);
/* 191 */             this.startmenu.label11.setText(">Sally!<");
/*     */           }
/*     */           else {
/* 194 */             switch (this.startmenu.whereis) {
/*     */             case 0:
/* 196 */               this.startmenu.label5.setText(">Center Unit");
/* 197 */               this.startmenu.label6.setText(this.startmenu.default6);
/* 198 */               this.startmenu.label7.setText(this.startmenu.default7);
/* 199 */               switch (this.startmenu.soubiis) {
/*     */               case 0:
/* 201 */                 this.startmenu.label8.setText(this.startmenu.default9);
/* 202 */                 this.startmenu.soubi1is = 0;
/* 203 */                 break;
/*     */               case 1:
/* 205 */                 this.startmenu.label8.setText(this.startmenu.default10);
/* 206 */                 this.startmenu.soubi1is = 1;
/*     */               }
/*     */ 
/* 209 */               break;
/*     */             case 1:
/* 211 */               this.startmenu.label5.setText(this.startmenu.default5);
/* 212 */               this.startmenu.label6.setText(">Middle Unit");
/* 213 */               this.startmenu.label7.setText(this.startmenu.default7);
/* 214 */               switch (this.startmenu.soubiis) {
/*     */               case 0:
/* 216 */                 this.startmenu.label9.setText(this.startmenu.default9);
/* 217 */                 this.startmenu.soubi2is = 0;
/* 218 */                 break;
/*     */               case 1:
/* 220 */                 this.startmenu.label9.setText(this.startmenu.default10);
/* 221 */                 this.startmenu.soubi2is = 1;
/*     */               }
/*     */ 
/* 224 */               break;
/*     */             case 2:
/* 226 */               this.startmenu.label5.setText(this.startmenu.default5);
/* 227 */               this.startmenu.label6.setText(this.startmenu.default6);
/* 228 */               this.startmenu.label7.setText(">Outer Unit");
/* 229 */               switch (this.startmenu.soubiis) {
/*     */               case 0:
/* 231 */                 this.startmenu.label10.setText(this.startmenu.default9);
/* 232 */                 this.startmenu.soubi3is = 0;
/* 233 */                 break;
/*     */               case 1:
/* 235 */                 this.startmenu.label10.setText(this.startmenu.default10);
/* 236 */                 this.startmenu.soubi3is = 1;
/*     */               }
/*     */               break;
/*     */             }
/*     */           }
/*     */ 
/* 242 */           this.startmenu.soubimotteru = Math.min(Math.min(this.startmenu.soubi1is, this.startmenu.soubi2is), this.startmenu.soubi3is);
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   public void keyTyped(KeyEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void keyPressed(KeyEvent e)
/*     */   {
/* 256 */     int key = e.getKeyCode();
/*     */ 
/* 258 */     if (key == 39) {
/* 259 */       if (this.renderFocused) {
/* 260 */         this.render.rightKeyPressed = true;
/*     */       }
/* 262 */       if ((this.startmenu.isFocused) && (this.startmenu.isSoubi)) {
/* 263 */         this.startmenu.soubiis = ((this.startmenu.soubiis + 1) % 2);
/*     */       }
/*     */     }
/* 266 */     if (key == 37) {
/* 267 */       if ((this.startmenu.isFocused) && (this.startmenu.isSoubi)) {
/* 268 */         this.startmenu.soubiis = ((this.startmenu.soubiis + 1) % 2);
/*     */       }
/* 270 */       if (this.renderFocused) {
/* 271 */         this.render.leftKeyPressed = true;
/*     */       }
/*     */     }
/*     */ 
/* 275 */     if (key == 38) {
/* 276 */       if (this.renderFocused)
/* 277 */         this.render.upKeyPressed = true;
/* 278 */       if (this.menu.isFocused) {
/* 279 */         this.menu.whereis = ((this.menu.whereis + 2) % 3);
/*     */       }
/* 281 */       if ((this.startmenu.isFocused) && (!this.startmenu.isSoubi)) {
/* 282 */         this.startmenu.whereis = ((this.startmenu.whereis + 1) % 2);
/*     */       }
/* 284 */       if ((this.startmenu.isFocused) && (this.startmenu.isSoubi)) {
/* 285 */         this.startmenu.whereis = ((this.startmenu.whereis + 2) % 3);
/* 286 */         this.startmenu.soubiis = 2;
/*     */       }
/*     */     }
/* 289 */     if (key == 40) {
/* 290 */       if (this.renderFocused)
/* 291 */         this.render.downKeyPressed = true;
/* 292 */       if (this.menu.isFocused) {
/* 293 */         this.menu.whereis = ((this.menu.whereis + 1) % 3);
/*     */       }
/* 295 */       if ((this.startmenu.isFocused) && (!this.startmenu.isSoubi)) {
/* 296 */         this.startmenu.whereis = ((this.startmenu.whereis + 1) % 2);
/*     */       }
/* 298 */       if ((this.startmenu.isFocused) && (this.startmenu.isSoubi)) {
/* 299 */         this.startmenu.whereis = ((this.startmenu.whereis + 1) % 3);
/* 300 */         this.startmenu.soubiis = -1;
/*     */       }
/*     */     }
/* 303 */     if (key == 27) {
/* 304 */       if (this.renderFocused) {
/* 305 */         this.renderFocused = false;
/* 306 */         this.render.pose();
/* 307 */         this.menu = new Menu();
/* 308 */         this.panel1.add(this.menu, "menu");
/* 309 */         this.menu.isFocused = true;
/* 310 */         this.menu.whereis = 2;
/* 311 */         this.cl.show(this.panel1, "menu");
/* 312 */         System.out.println("menu.whereis=?" + this.menu.whereis);
/* 313 */         WavePlayer.stop(0);
/*     */       }
/* 315 */       else if (this.renderMade) {
/* 316 */         this.renderFocused = true;
/* 317 */         this.cl.show(this.panel1, "render");
/* 318 */         this.render.unpose();
/* 319 */         this.menu.isFocused = false;
/* 320 */         WavePlayer.restart(0);
/*     */       }
/*     */     }
/* 323 */     if ((key == 10) || (key == 32)) {
/* 324 */       if ((this.menu.isFocused) && (this.menu.whereis == 1)) {
/* 325 */         System.exit(0);
/*     */       }
/* 327 */       if ((this.menu.isFocused) && (this.menu.whereis == 2)) {
/* 328 */         this.menu.isFocused = false;
/*     */ 
/* 330 */         this.startmenu.isFocused = true;
/* 331 */         this.cl.show(this.panel1, "startmenu");
/*     */       }
/* 333 */       else if ((this.startmenu.isFocused) && (this.startmenu.whereis == 0) && (!this.startmenu.isSoubi)) {
/* 334 */         this.startmenu.soubisentaku(0);
/*     */       }
/* 336 */       else if ((this.startmenu.isFocused) && (this.startmenu.isSoubi) && (this.startmenu.soubimotteru != -1)) {
/* 337 */         this.startmenu.ready = true;
/*     */       }
/* 339 */       if (this.startmenu.ready) {
/* 340 */         this.render.restart();
/* 341 */         this.render.changeunit(this.startmenu.soubi1is, this.startmenu.soubi2is, this.startmenu.soubi3is);
/* 342 */         this.cl.show(this.panel1, "render");
/* 343 */         this.render.unpose();
/* 344 */         this.renderFocused = true;
/* 345 */         this.startmenu.isFocused = false;
/* 346 */         this.startmenu.isSoubi = false;
/* 347 */         this.startmenu.ready = false;
/* 348 */         this.renderMade = true;
/*     */         try {
/* 350 */           WavePlayer.play(0);
/*     */         }
/*     */         catch (LineUnavailableException e1) {
/* 353 */           e1.printStackTrace();
/*     */         }
/*     */         catch (IOException e1) {
/* 356 */           e1.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void keyReleased(KeyEvent e)
/*     */   {
/* 365 */     int key = e.getKeyCode();
/* 366 */     if ((key == 38) && 
/* 367 */       (this.renderFocused)) {
/* 368 */       this.render.upKeyPressed = false;
/*     */     }
/* 370 */     if ((key == 39) && 
/* 371 */       (this.renderFocused)) {
/* 372 */       this.render.rightKeyPressed = false;
/*     */     }
/*     */ 
/* 375 */     if ((key == 37) && 
/* 376 */       (this.renderFocused)) {
/* 377 */       this.render.leftKeyPressed = false;
/*     */     }
/*     */ 
/* 380 */     if ((key == 40) && 
/* 381 */       (this.renderFocused))
/* 382 */       this.render.downKeyPressed = false;
/*     */   }
/*     */ 
/*     */   public void mouseDragged(MouseEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void mouseMoved(MouseEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void mouseClicked(MouseEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void mousePressed(MouseEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void mouseReleased(MouseEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void mouseEntered(MouseEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void mouseExited(MouseEvent e)
/*     */   {
/*     */   }
}