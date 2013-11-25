/*    */ package game;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Point;
/*    */ import java.awt.event.MouseEvent;
/*    */ 
/*    */ public class MissileUnit extends Units
/*    */ {
/*    */   private int part;
/*  9 */   private int interval = 400;
/* 10 */   private long lastfiredtime = 0L;
/*    */   Jiki jiki;
/*    */   Render render;
/*    */ 
/*    */   public MissileUnit(Render render, Jiki jiki, int part)
/*    */   {
/* 14 */     this.render = render;
/* 15 */     this.part = part;
/* 16 */     this.interval = 1000;
/* 17 */     int i = 0;
/*    */     do { render.jikibullet[i][part] = new Missile();
/*    */ 
/* 17 */       i++; render.getClass(); } while (i < 20);
/*    */   }
/*    */ 
/*    */   public void draw(Graphics g, Jiki jiki)
/*    */   {
/* 23 */     int i = 0;
/*    */     do { if (this.render.jikibullet[i][this.part].isUsed())
/* 25 */         this.render.jikibullet[i][this.part].draw(g);
/* 23 */       i++; this.render.getClass(); } while (i < 20);
/*    */   }
/*    */ 
/*    */   public void fire(MouseEvent e, Jiki jiki, int part)
/*    */   {
/* 31 */     if (System.currentTimeMillis() - this.lastfiredtime < this.interval) {
/* 32 */       return;
/*    */     }
/*    */ 
/* 35 */     this.lastfiredtime = System.currentTimeMillis();
/* 36 */     int startx = jiki.getX() - 24;
/* 37 */     int starty = jiki.getY() - 24;
/* 38 */     if ((part == 0) || (part == 4)) {
/* 39 */       startx -= 30;
/* 40 */       starty += 30;
/*    */     }
/* 42 */     if ((part == 1) || (part == 3)) {
/* 43 */       starty += 60;
/*    */     }
/* 45 */     if (part == 2) {
/* 46 */       startx += 60;
/*    */     }
/* 48 */     Point start = new Point(startx, starty);
/* 49 */     Point target = new Point(0, 0);
/* 50 */     int i = 0;
/*    */     do { if (!this.render.jikibullet[i][part].isUsed()) {
/* 52 */         this.render.jikibullet[i][part].shot(start, target);
/* 53 */         break;
/*    */       }
/* 50 */       i++; this.render.getClass(); } while (i < 20);
/*    */   }
/*    */ 
/*    */   public void specialfire(MouseEvent e, Jiki jiki, int part)
/*    */   {
/* 59 */     if (System.currentTimeMillis() - this.lastfiredtime < this.interval) {
/* 60 */       return;
/*    */     }
/*    */ 
/* 63 */     this.lastfiredtime = System.currentTimeMillis();
/* 64 */     Point start = new Point(jiki.getX() + 50, jiki.getY() + 16 + (part - 2) * 20);
/* 65 */     Point target = new Point(0, 0);
/* 66 */     int i = 0;
/*    */     do { for (int j = 0; j < 20; j++)
/* 68 */         if (!this.render.jikibullet[i][part].isUsed())
/* 69 */           this.render.jikibullet[i][part].shot(start, target);
/* 66 */       i++; this.render.getClass(); } while (i < 20);
/*    */   }
/*    */ }

/* Location:           M:\Documents\shootinggame\stg_build_0014\
 * Qualified Name:     game.MissileUnit
 * JD-Core Version:    0.6.2
 */