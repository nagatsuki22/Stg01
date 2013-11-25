/*     */ package game;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.ImageIcon;
/*     */ 
/*     */ public class RoundShotEnemy extends Enemy
/*     */ {
/*     */   private int syokix;
/*     */   private int syokiy;
/*     */   private Explosion exp;
/*  13 */   private int reachedtime = 0;
/*  14 */   private int irujikan = 50;
/*  15 */   private boolean fired = false;
/*  16 */   private boolean taikyakuchu = false;
/*     */   public static Image image;
/*  18 */   Bullet[] bulletcase = new Bullet[100];
/*     */ 
/*  20 */   public RoundShotEnemy(int x, int y, Render render) { this.exp = new Explosion();
/*  21 */     this.health = 500;
/*  22 */     point = 100;
/*  23 */     this.isAlive = false;
/*  24 */     this.SPEED = 10;
/*  25 */     this.render = render;
/*  26 */     this.syokix = x;
/*  27 */     this.syokiy = y;
/*  28 */     this.width = 30;
/*  29 */     this.height = 30;
/*  30 */     this.x = -50; this.y = -50;
/*     */ 
/*  32 */     for (int k = 0; k < this.bulletcase.length; k++) {
/*  33 */       this.bulletcase[k] = new DirectionalBullet();
/*     */     }
/*  35 */     ImageIcon icon = new ImageIcon(getClass().getResource("zako1.png"));
/*  36 */     image = icon.getImage(); }
/*     */ 
/*     */   public void damage(Bullet bullet, Render render)
/*     */   {
/*  40 */     this.health -= bullet.getDamage();
/*  41 */     if (this.health <= 0) {
/*  42 */       int l = 0;
/*  43 */       int g = 0;
/*  44 */       while ((g < 5) && (l < 299)) {
/*  45 */         l++;
/*  46 */         if (!render.pointcase[l].isUsed) {
/*  47 */           render.pointcase[l].create(this.x + (int)(10.0D * Math.random()), this.y + (int)(10.0D * Math.random()));
/*  48 */           g++;
/*     */         }
/*     */       }
/*  51 */       bullet.setUnused();
/*     */ 
/*  53 */       neutralize();
/*     */     }
/*     */   }
/*     */ 
/*  57 */   public boolean corridewithjiki(Jiki jiki) { boolean b = false;
/*  58 */     for (int i = 0; i < this.bulletcase.length; i++) {
/*  59 */       Rectangle rectB = new Rectangle(this.bulletcase[i].getX(), this.bulletcase[i].getY(), this.bulletcase[i].getWidth(), this.bulletcase[i].getHeight());
/*  60 */       Rectangle rectE = new Rectangle(jiki.getX(), jiki.getY(), jiki.getWidth(), jiki.getHeight());
/*  61 */       if (rectB.intersects(rectE)) {
/*  62 */         b = true;
/*  63 */         System.out.println("shot" + i + " hitted");
/*  64 */         break;
/*     */       }
/*     */     }
/*     */ 
/*  68 */     return b; }
/*     */ 
/*     */   public boolean corridewith(Bullet bullet) {
/*  71 */     Rectangle rectB = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
/*  72 */     Rectangle rectE = new Rectangle(this.x, this.y, this.width, this.height);
/*  73 */     if (rectB.intersects(rectE)) {
/*  74 */       return true;
/*     */     }
/*  76 */     return false;
/*     */   }
/*     */ 
/*     */   public void move()
/*     */   {
/*  81 */     this.exp.animate();
/*  82 */     for (int j = 0; j < this.bulletcase.length; j++) {
/*  83 */       if (this.bulletcase[j].isUsed()) {
/*  84 */         this.bulletcase[j].move();
/*     */       }
/*     */     }
/*  87 */     if (this.isAlive) {
/*  88 */       fire();
/*  89 */       if ((this.x > this.syokix) && (!this.taikyakuchu)) {
/*  90 */         this.x -= this.SPEED;
/*     */       }
/*  92 */       if (this.x == this.syokix) {
/*  93 */         this.x -= this.SPEED;
/*  94 */         this.reachedtime = this.render.time;
/*     */       }
/*     */ 
/*  97 */       if ((this.x < this.syokix) && (this.render.time - this.reachedtime == this.irujikan)) {
/*  98 */         this.x += this.SPEED;
/*  99 */         this.taikyakuchu = true;
/*     */       }
/* 101 */       if (this.taikyakuchu) {
/* 102 */         this.x += this.SPEED;
/*     */       }
/*     */ 
/* 105 */       if (this.x > 1200) {
/* 106 */         this.isAlive = false;
/*     */       }
/*     */     }
/* 109 */     if (!this.isAlive) {
/* 110 */       this.x = -50; this.y = -50;
/*     */     }
/*     */   }
/*     */ 
/* 114 */   public void fire() { if ((this.reachedtime != 0) && (!this.fired)) {
/* 115 */       for (int i = 0; i < 100; i++) {
/* 116 */         if (!this.bulletcase[i].used)
/* 117 */           this.bulletcase[i].shot(this.x, this.y, 6.283185307179586D * (i + 1) / 100.0D);
/*     */       }
/* 119 */       this.fired = true;
/*     */     } }
/*     */ 
/*     */   public void makeThisAlive()
/*     */   {
/* 124 */     if (this.isAlive) return;
/* 125 */     this.isAlive = true;
/* 126 */     this.x = 1200;
/* 127 */     this.y = this.syokiy;
/*     */   }
/*     */ 
/*     */   public void neutralize() {
/* 131 */     this.isAlive = false;
/* 132 */     this.exp.explode(this.x, this.y);
/*     */   }
/*     */ 
/*     */   public void draw(Graphics g)
/*     */   {
/* 138 */     g.drawImage(image, this.x, this.y, null);
/* 139 */     this.exp.draw(g);
/* 140 */     for (int j = 0; j < this.bulletcase.length; j++)
/* 141 */       if (this.bulletcase[j].isUsed())
/* 142 */         this.bulletcase[j].draw(g);
/*     */   }
/*     */ }

/* Location:           M:\Documents\shootinggame\stg_build_0014\
 * Qualified Name:     game.RoundShotEnemy
 * JD-Core Version:    0.6.2
 */