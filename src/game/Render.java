/*     */ package game;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class Render extends JPanel
/*     */   implements MouseListener, MouseMotionListener
/*     */ {
/*     */   Jiki jiki;
/*  15 */   public int zanki = 3;
/*  16 */   public int shootcount = 0;
/*     */   public static final int WIDTH = 1000;
/*     */   public static final int HEIGHT = 700;
/*  19 */   boolean upKeyPressed = false;
/*  20 */   boolean downKeyPressed = false;
/*  21 */   boolean rightKeyPressed = false;
/*  22 */   boolean leftKeyPressed = false;
/*  23 */   final int UP = 0;
/*  24 */   final int DOWN = 1;
/*  25 */   final int RIGHT = 2;
/*  26 */   final int LEFT = 3;
boolean bossDefeated = false;
/*  27 */   private int point = 0;
/*  28 */   public boolean sleep = true;
/*  29 */   private boolean visible = false;
/*  30 */   public int unitkazu = 5;
/*     */   public Enemy[] enemys;
/*     */   public LoadStage load;
/*     */   public Haikei haikei;
/*  34 */   public int speed = 5;
/*  35 */   public Unit[] u = new Unit[this.unitkazu];
/*  36 */   public Point[] pointcase = new Point[300];
/*  37 */   public final int jikimax = 20;
/*     */   public long time1;
/*     */   public long time2;
/*     */   public long draggedtime;
/*  41 */   public int time = 0;
/*  42 */   public Bullet[][] jikibullet = new Bullet[20][this.unitkazu];
/*     */ 
/*  44 */   public Render() { setFocusable(true);
/*  45 */     addMouseListener(this);
/*  46 */     addMouseMotionListener(this);
/*  47 */     this.load = new LoadStage();
/*  48 */     this.load.load("stage1.dat");
/*  49 */     this.enemys = new Enemy[this.load.enes];
/*  50 */     for (int i = 0; i < this.pointcase.length; i++) {
/*  51 */       this.pointcase[i] = new Point(this);
/*     */     }
/*  53 */     for (int i = 0; i < this.load.enes; i++) {
/*  54 */       this.enemys[i] = this.load.getEnemy(this.load.stage[i][1], this.load.stage[i][2], this.load.stage[i][3], this);
/*     */     }
/*  56 */     this.haikei = new Haikei(this);
/*  57 */     this.jiki = new Jiki(100, 100, this);
/*  58 */     for (int i = 0; i < this.unitkazu; i++) {
/*  59 */       this.u[i] = new Unit(this, this.jiki, i, 0);
/*     */     }
/*  61 */     setBackground(Color.white);
/*  62 */     setPreferredSize(new Dimension(1000, 700)); }
/*     */ 
/*     */   public int getHeight()
/*     */   {
/*  66 */     return 700;
/*     */   }
/*     */ 
/*     */   public int getWidth() {
/*  70 */     return 1000;
/*     */   }
/*     */   public void restart() {
/*  73 */     this.zanki = 3;
/*  74 */     this.shootcount = 0;
/*  75 */     this.upKeyPressed = false;
/*  76 */     this.downKeyPressed = false;
/*  77 */     this.rightKeyPressed = false;
/*  78 */     this.leftKeyPressed = false;
/*  79 */     this.time = 0;
/*  80 */     this.point = 0;
/*  81 */     this.sleep = true;
/*  82 */     this.visible = false;
/*  83 */     this.load = new LoadStage();
/*  84 */     this.load.load("stage1.dat");
/*  85 */     this.enemys = new Enemy[this.load.enes];
/*  86 */     for (int i = 0; i < this.pointcase.length; i++) {
/*  87 */       this.pointcase[i] = new Point(this);
/*     */     }
/*  89 */     for (int i = 0; i < this.load.enes; i++) {
/*  90 */       this.enemys[i] = this.load.getEnemy(this.load.stage[i][1], this.load.stage[i][2], this.load.stage[i][3], this);
/*     */     }
/*  92 */     this.haikei = new Haikei(this);
/*  93 */     this.jiki = new Jiki(100, 100, this);
/*  94 */     for (int i = 0; i < this.unitkazu; i++)
/*  95 */       this.u[i] = new Unit(this, this.jiki, i, 0);
/*     */   }
/*     */ 
/*     */   public void changeunit(int unit1, int unit2, int unit3)
/*     */   {
/* 101 */     this.u[2] = new Unit(this, this.jiki, 2, unit1);
/* 102 */     this.u[1] = new Unit(this, this.jiki, 1, unit2);
/* 103 */     this.u[3] = new Unit(this, this.jiki, 3, unit2);
/* 104 */     this.u[0] = new Unit(this, this.jiki, 0, unit3);
/* 105 */     this.u[4] = new Unit(this, this.jiki, 4, unit3);
/*     */   }
/*     */   public void corrisionDetection() {
/* 108 */     for (int i = 0; i < 20; i++)
/* 109 */       for (int j = 0; j < this.unitkazu; j++)
/* 110 */         for (int k = 0; k < this.enemys.length; k++)
/* 111 */           if ((this.enemys[k].corridewith(this.jikibullet[i][j])) && (this.enemys[k].isAlive)) {
/* 112 */             this.enemys[k].damage(this.jikibullet[i][j], this);
/* 113 */             this.jikibullet[i][j].jibaku();
System.out.println("bullet"+i+","+j+"hitted enemy "+k);
/* 114 */             break;
/*     */           }
/*     */   }
/*     */ 
/*     */   public boolean isVisible()
/*     */   {
/* 121 */     return this.visible;
/*     */   }
/*     */ 
/*     */   public void pose() {
/* 125 */     this.visible = false;
/* 126 */     this.sleep = true;
/*     */   }
/*     */ 
/*     */   public void unpose() {
/* 130 */     this.visible = true;
/* 131 */     this.sleep = false;
/*     */   }
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/* 136 */     super.paintComponent(g);
/* 137 */     this.haikei.draw(g);
/* 138 */     for (int i = 0; i < this.enemys.length; i++) {
/* 139 */       this.enemys[i].draw(g);
/*     */     }
/* 141 */     this.jiki.draw(g);
/* 142 */     for (int i = 0; i < this.u.length; i++) {
/* 143 */       this.u[i].draw(g, this.jiki);
/*     */     }
/* 145 */     for (int i = 0; i < this.pointcase.length; i++)
/* 146 */       this.pointcase[i].draw(g); 
/*     */   }
/* 151 */   public int getPoint() { return this.point; }
/*     */ 
/*     */   public void addPoint(int a) {
/* 154 */     this.point += a;
/*     */   }
/*     */   public void mouseClicked(MouseEvent e) {
/*     */   }
/*     */ 
/*     */   public void mouseEntered(MouseEvent e) {
/*     */   }
/*     */ 
/*     */   public void mouseExited(MouseEvent arg0) {
/*     */   }
/*     */ 
/*     */   public void mousePressed(MouseEvent e) {
/* 170 */     for (int i = 0; i < this.u.length; i++) {
/* 171 */       this.u[i].fire(e, this.jiki, i);
/*     */     }
/* 173 */     this.time1 = System.currentTimeMillis();
/*     */   }
/*     */   public void mouseReleased(MouseEvent e) {
/* 176 */     this.time2 = System.currentTimeMillis();
/* 177 */     this.draggedtime = (this.time2 - this.time1);
/* 178 */     if (this.draggedtime > 1000L)
/* 179 */       for (int i = 0; i < this.u.length; i++)
/* 180 */         this.u[i].specialfire(e, this.jiki, i);
/*     */   }
/*     */ 
/*     */   public void mouseDragged(MouseEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void mouseMoved(MouseEvent e) {
/* 188 */     for (int i = 0; i < 20; i++)
/* 189 */       for (int j = 0; j < this.unitkazu; j++)
/* 190 */         if (this.jikibullet[i][j].type == 1) {
/* 191 */           this.jikibullet[i][j].targetx = e.getX();
/* 192 */           this.jikibullet[i][j].targety = e.getY();
/*     */         }
/*     */   }
/*     */ }