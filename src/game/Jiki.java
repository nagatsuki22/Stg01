/*    */ package game;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Image;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class Jiki extends JPanel
/*    */ {
/*    */   private int x;
/*    */   private int y;
/* 12 */   private int SPEED = 10;
/*    */   private static final int UP = 0;
/*    */   private static final int DOWN = 1;
/*    */   private static final int RIGHT = 2;
/*    */   private static final int LEFT = 3;
/* 17 */   private int height = 4;
/* 18 */   private int width = 4;
/* 19 */   private boolean isAlive = true;
/*    */   private Image image1;
/*    */   private Render render;
/* 22 */   private long lastdeadtime = 0L;
/*    */ 
/*    */   public boolean isAlive() {
/* 25 */     return this.isAlive;
/*    */   }
/*    */ 
/*    */   public void neutralize() {
/* 29 */     if (this.isAlive) { this.x = -100;
/* 30 */       this.isAlive = false;
/* 31 */       this.lastdeadtime = this.render.time; } 
/*    */   }
/*    */ 
/* 34 */   public int getWidth() { return this.width; }
/*    */ 
/*    */   public int getHeight() {
/* 37 */     return this.height;
/*    */   }
/*    */ 
/*    */   private void loadImage() {
/* 41 */     ImageIcon icon = new ImageIcon(getClass().getResource("jiki.png"));
/* 42 */     this.image1 = icon.getImage();
/*    */   }
/*    */ 
/*    */   public void hukkatu()
/*    */   {
/* 47 */     if (this.x <= 100) this.x += 1;
/* 48 */     if (this.render.time - this.lastdeadtime > 250L)
/* 49 */       this.isAlive = true;
/*    */   }
/*    */ 
/*    */   public void move(int dir)
/*    */   {
/* 55 */     if (dir == 0)
/* 56 */       this.y -= this.SPEED;
/* 57 */     else if (dir == 1) {
/* 58 */       this.y += this.SPEED;
/*    */     }
/* 60 */     if (dir == 2) {
/* 61 */       this.x += this.SPEED;
/*    */     }
/* 63 */     if (dir == 3) {
/* 64 */       this.x -= this.SPEED;
/*    */     }
/* 66 */     if (this.y < 0) {
/* 67 */       this.y = 0;
/*    */     }
/* 69 */     if (this.x < 0) {
/* 70 */       this.x = 0;
/*    */     }
/* 72 */     if (this.y > this.render.getHeight() - 32) {
/* 73 */       this.y = (this.render.getHeight() - 32);
/*    */     }
/* 75 */     if (this.x > this.render.getWidth() - 32)
/* 76 */       this.x = (this.render.getWidth() - 32);
/*    */   }
/*    */ 
/*    */   public Jiki(int x, int y, Render render) {
/* 80 */     loadImage();
/* 81 */     this.x = x;
/* 82 */     this.y = y;
/* 83 */     this.render = render;
/*    */   }
/*    */ 
/*    */   public int getX()
/*    */   {
/* 88 */     return this.x;
/*    */   }
/*    */   public int getY() {
/* 91 */     return this.y;
/*    */   }
/*    */   public void draw(Graphics g) {
/* 94 */     g.drawImage(this.image1, this.x - 24, this.y - 24, null);
/*    */   }
/*    */ }

/* Location:           M:\Documents\shootinggame\stg_build_0014\
 * Qualified Name:     game.Jiki
 * JD-Core Version:    0.6.2
 */