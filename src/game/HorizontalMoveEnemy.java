/*     */ package game;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import javax.swing.ImageIcon;
/*     */ 
/*     */ public class HorizontalMoveEnemy extends Enemy
/*     */ {
/*     */   private int syokix;
/*     */   private int syokiy;
/*     */   private Image image;
/*     */   private Explosion exp;
/*     */   private long lastfiredtime;
/*     */   private int firedtime;
/*  17 */   private int interval = 100;
/*  18 */   private Bullet[] bulletcase = new Bullet[100];
/*     */ 
/*  20 */   public boolean corridewithjiki(Jiki jiki) { boolean b = false;
/*  21 */     for (int i = 0; i < this.bulletcase.length; i++) {
/*  22 */       Rectangle rectB = new Rectangle(this.bulletcase[i].getX(), this.bulletcase[i].getY(), this.bulletcase[i].getWidth(), this.bulletcase[i].getHeight());
/*  23 */       Rectangle rectE = new Rectangle(jiki.getX(), jiki.getY(), jiki.getWidth(), jiki.getHeight());
/*  24 */       if (rectB.intersects(rectE)) {
/*  25 */         b = true;
/*  26 */         break;
/*     */       }
/*     */     }
/*  29 */     return b; }
/*     */ 
/*     */   public boolean corridewith(Bullet bullet) {
/*  32 */     Rectangle rectB = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
/*  33 */     Rectangle rectE = new Rectangle(this.x, this.y, this.width, this.height);
/*  34 */     if (rectB.intersects(rectE)) {
/*  35 */       return true;
/*     */     }
/*  37 */     return false;
/*     */   }
/*     */ 
/*     */   public void damage(Bullet bullet, Render render)
/*     */   {
/*  42 */     this.health -= bullet.getDamage();
/*  43 */     if (this.health <= 0) {
/*  44 */       int l = 0;
/*  45 */       int g = 0;
/*  46 */       while ((g < 5) && (l < 299)) {
/*  47 */         l++;
/*  48 */         if (!render.pointcase[l].isUsed) {
/*  49 */           render.pointcase[l].create(this.x + (int)(10.0D * Math.random()), this.y + (int)(10.0D * Math.random()));
/*  50 */           g++;
/*     */         }
/*     */       }
/*  53 */       bullet.setUnused();
/*     */ 
/*  55 */       neutralize();
/*     */     }
/*     */   }
/*     */ 
/*  59 */   public HorizontalMoveEnemy(int x, int y, Render render) { this.health = 100;
/*  60 */     this.SPEED = 1;
/*  61 */     this.isBoss = false;
/*  62 */     this.x = -50;
/*  63 */     this.y = -50;
/*  64 */     this.syokix = x;
/*  65 */     this.syokiy = y;
/*  66 */     this.width = 32;
/*  67 */     this.height = 32;
/*  68 */     this.render = render;
/*  69 */     point = 100;
/*  70 */     this.exp = new Explosion();
/*  71 */     for (int k = 0; k < this.bulletcase.length; k++) {
/*  72 */       this.bulletcase[k] = new VerticalBullet();
/*     */     }
/*  74 */     if (this.syokix > 1000) {
/*  75 */       this.SPEED *= -1;
/*     */     }
/*  77 */     ImageIcon icon = new ImageIcon(getClass().getResource("zako1.png"));
/*  78 */     this.image = icon.getImage(); }
/*     */ 
/*     */   public void makeThisAlive() {
/*  81 */     if (this.isAlive) return;
/*  82 */     this.isAlive = true;
/*  83 */     this.x = this.syokix;
/*  84 */     this.y = this.syokiy;
/*     */   }
/*     */   public void move() {
/*  87 */     this.exp.animate();
/*  88 */     if (this.isAlive) {
/*  89 */       fire();
/*  90 */       this.x += this.SPEED;
/*     */     }
/*  92 */     else if (this.x > 0) {
/*  93 */       this.x += -10; this.y += 10; } else {
/*  94 */       this.x = -50; this.y = -50;
/*     */     }
/*     */ 
/*  97 */     for (int j = 0; j < this.bulletcase.length; j++) {
/*  98 */       if (this.bulletcase[j].isUsed()) {
/*  99 */         this.bulletcase[j].move();
/*     */       }
/*     */     }
/* 102 */     if ((this.x < -100) || (this.x > 1100))
/* 103 */       this.isAlive = false;
/*     */   }
/*     */ 
/*     */   public void fire() {
/* 107 */     if (System.currentTimeMillis() - this.lastfiredtime < this.interval) {
/* 108 */       return;
/*     */     }
/* 110 */     if ((this.firedtime == 9) && (System.currentTimeMillis() - this.lastfiredtime < this.interval * 10)) {
/* 111 */       return;
/*     */     }
/*     */ 
/* 114 */     this.firedtime = ((this.firedtime + 1) % 10);
/* 115 */     this.lastfiredtime = System.currentTimeMillis();
/* 116 */     java.awt.Point start = new java.awt.Point(this.x, this.y);
/* 117 */     java.awt.Point target = new java.awt.Point(0, 0);
/* 118 */     for (int i = 0; i < this.bulletcase.length; i++)
/* 119 */       if (!this.bulletcase[i].isUsed()) {
/* 120 */         this.bulletcase[i].shot(start, target);
/* 121 */         break;
/*     */       }
/*     */   }
/*     */ 
/*     */   public int getPoint() {
/* 126 */     return point;
/*     */   }
/*     */   public void neutralize() {
/* 129 */     this.isAlive = false;
/* 130 */     this.exp.explode(this.x, this.y);
/*     */   }
/*     */ 
/*     */   public void draw(Graphics g) {
/* 134 */     this.exp.draw(g);
/* 135 */     g.drawImage(this.image, this.x, this.y, null);
/* 136 */     for (int j = 0; j < this.bulletcase.length; j++)
/* 137 */       if (this.bulletcase[j].isUsed())
/* 138 */         this.bulletcase[j].draw(g);
/*     */   }
/*     */ }

/* Location:           M:\Documents\shootinggame\stg_build_0014\
 * Qualified Name:     game.HorizontalMoveEnemy
 * JD-Core Version:    0.6.2
 */