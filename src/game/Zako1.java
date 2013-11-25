/*     */ package game;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import javax.swing.ImageIcon;
/*     */ 
/*     */ public class Zako1 extends Enemy
/*     */ {
/*     */   private int syokix;
/*     */   private int syokiy;
/*     */   private static final int point = 100;
/*  14 */   private int SPEED = 3;
/*  15 */   private int Ryusoku = 0;
/*     */   private int width;
/*  17 */   private long lastfiredtime = 0L;
/*  18 */   private long interval = 100L;
/*  19 */   private int firedtime = 0;
/*     */   private int height;
/*     */   private Explosion exp;
/*  22 */   public Bullet[] bulletcase = new Bullet[100];
/*     */   public static Image Image;
/*     */ 
/*     */   public boolean corridewith(Bullet bullet)
/*     */   {
/*  25 */     Rectangle rectB = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
/*  26 */     Rectangle rectE = new Rectangle(this.x, this.y, this.width, this.height);
/*  27 */     if (rectB.intersects(rectE)) {
/*  28 */       return true;
/*     */     }
/*  30 */     return false;
/*     */   }
/*     */ 
/*     */   public void damage(Bullet bullet, Render render)
/*     */   {
/*  35 */     this.health -= bullet.getDamage();
/*  36 */     if (this.health <= 0) {
/*  37 */       int l = 0;
/*  38 */       int g = 0;
/*  39 */       while ((g < 5) && (l < 299)) {
/*  40 */         l++;
/*  41 */         if (!render.pointcase[l].isUsed) {
/*  42 */           render.pointcase[l].create(this.x + (int)(10.0D * Math.random()), this.y + (int)(10.0D * Math.random()));
/*  43 */           g++;
/*     */         }
/*     */       }
/*  46 */       bullet.setUnused();
/*     */ 
/*  48 */       neutralize();
/*     */     }
/*     */   }
/*     */ 
/*  52 */   public boolean corridewithjiki(Jiki jiki) { boolean b = false;
/*  53 */     for (int i = 0; i < this.bulletcase.length; i++) {
/*  54 */       Rectangle rectB = new Rectangle(this.bulletcase[i].getX(), this.bulletcase[i].getY(), this.bulletcase[i].getWidth(), this.bulletcase[i].getHeight());
/*  55 */       Rectangle rectE = new Rectangle(jiki.getX(), jiki.getY(), jiki.getWidth(), jiki.getHeight());
/*  56 */       if (rectB.intersects(rectE)) {
/*  57 */         b = true;
/*  58 */         break;
/*     */       }
/*     */     }
/*  61 */     return b; }
/*     */ 
/*     */   public Zako1(int x, int y, Render render) {
/*  64 */     this.exp = new Explosion();
/*  65 */     this.health = 100;
/*  66 */     this.isBoss = false;
/*     */ 
/*  68 */     this.x = -50;
/*  69 */     this.y = -50;
/*     */ 
/*  71 */     this.syokix = x;
/*  72 */     this.syokiy = y;
/*     */ 
/*  74 */     if (this.syokiy > 700) {
/*  75 */       this.SPEED *= -1;
/*     */     }
/*     */ 
/*  78 */     for (int k = 0; k < this.bulletcase.length; k++) {
/*  79 */       this.bulletcase[k] = new StraightBullet();
/*     */     }
/*  81 */     ImageIcon icon = new ImageIcon(getClass().getResource("zako1.png"));
/*  82 */     Image = icon.getImage();
/*  83 */     this.width = 20;
/*  84 */     this.height = 20;
/*  85 */     this.Ryusoku = render.speed;
/*     */   }
/*     */ 
/*     */   public void makeThisAlive()
/*     */   {
/*  90 */     if (this.isAlive) return;
/*  91 */     this.isAlive = true;
/*  92 */     this.x = this.syokix;
/*  93 */     this.y = this.syokiy;
/*     */   }
/*     */   public int getPoint() {
/*  96 */     return 100;
/*     */   }
/*     */   public void neutralize() {
/*  99 */     this.isAlive = false;
/* 100 */     this.exp.explode(this.x, this.y);
/*     */   }
/*     */ 
/*     */   public void fire() {
/* 104 */     if (System.currentTimeMillis() - this.lastfiredtime < this.interval) {
/* 105 */       return;
/*     */     }
/* 107 */     if ((this.firedtime == 3) && (System.currentTimeMillis() - this.lastfiredtime < this.interval * 10L)) {
/* 108 */       return;
/*     */     }
/*     */ 
/* 111 */     this.firedtime = ((this.firedtime + 1) % 4);
/* 112 */     this.lastfiredtime = System.currentTimeMillis();
/* 113 */     java.awt.Point start = new java.awt.Point(this.x, this.y);
/* 114 */     java.awt.Point target = new java.awt.Point(0, 0);
/* 115 */     for (int i = 0; i < this.bulletcase.length; i++)
/* 116 */       if (!this.bulletcase[i].isUsed()) {
/* 117 */         this.bulletcase[i].shot(start, target);
/* 118 */         break;
/*     */       }
/*     */   }
/*     */ 
/*     */   public void move() {
/* 123 */     this.exp.animate();
/* 124 */     if (this.isAlive) {
/* 125 */       fire();
/* 126 */       this.x -= this.Ryusoku;
/* 127 */       this.y += this.SPEED;
/*     */     }
/* 129 */     else if (this.x > 0) {
/* 130 */       this.x += -10; this.y += 10; } else {
/* 131 */       this.x = -50; this.y = -50;
/*     */     }
/*     */ 
/* 134 */     for (int j = 0; j < this.bulletcase.length; j++) {
/* 135 */       if (this.bulletcase[j].isUsed()) {
/* 136 */         this.bulletcase[j].move();
/*     */       }
/*     */     }
/* 139 */     if (this.x < 0)
/* 140 */       this.isAlive = false;
/*     */   }
/*     */ 
/*     */   public void draw(Graphics g) {
/* 144 */     this.exp.draw(g);
/* 145 */     g.drawImage(Image, this.x, this.y, null);
/* 146 */     for (int j = 0; j < this.bulletcase.length; j++)
/* 147 */       if (this.bulletcase[j].isUsed())
/* 148 */         this.bulletcase[j].draw(g);
/*     */   }
/*     */ }

/* Location:           M:\Documents\shootinggame\stg_build_0014\
 * Qualified Name:     game.Zako1
 * JD-Core Version:    0.6.2
 */