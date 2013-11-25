/*    */ package game;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Point;
/*    */ import java.awt.event.MouseEvent;
/*    */ 
/*    */ public class KijyuUnit extends Units
/*    */ {
/*    */   private int part;
/* 10 */   private int interval = 400;
/* 11 */   private long lastfiredtime = 0L;
/*    */   Jiki jiki;
/*    */   Render render;
/*    */ 
/*    */   public KijyuUnit(Render render, Jiki jiki, int part)
/*    */   {
/* 15 */     this.render = render;
/* 16 */     this.part = part;
/* 17 */     this.interval = 80;
/* 18 */     int i = 0;
/*    */     do { render.jikibullet[i][part] = new KijyuBullet();
/*    */ 
/* 18 */       i++; render.getClass(); } while (i < 20);
/*    */   }
/*    */ 
/*    */   public void draw(Graphics g, Jiki jiki)
/*    */   {
/* 24 */     int i = 0;
/*    */     do { if (this.render.jikibullet[i][this.part].isUsed())
/* 26 */         this.render.jikibullet[i][this.part].draw(g);
/* 24 */       i++; this.render.getClass(); } while (i < 20);
/*    */   }
/*    */ 
/*    */   public void fire(MouseEvent e, Jiki jiki, int part)
/*    */   {
/* 32 */     if (System.currentTimeMillis() - this.lastfiredtime < this.interval) {
/* 33 */       return;
/*    */     }
/*    */ 
/* 36 */     this.lastfiredtime = System.currentTimeMillis();
/* 37 */     int startx = jiki.getX() - 24;
/* 38 */     int starty = jiki.getY() - 24;
/* 39 */     if ((part == 0) || (part == 4)) {
/* 40 */       startx -= 30;
/* 41 */       starty += 30;
/*    */     }
/* 43 */     if ((part == 1) || (part == 3)) {
/* 44 */       starty += 60;
/*    */     }
/* 46 */     if (part == 2) {
/* 47 */       startx += 60;
/*    */     }
/* 49 */     Point start = new Point(startx, starty);
/* 50 */     Point target = new Point(e.getX(), e.getY());
/* 51 */     int i = 0;
/*    */     do { if (!this.render.jikibullet[i][part].isUsed()) {
/* 53 */         this.render.jikibullet[i][part].shot(start, target);
/* 54 */         break;
/*    */       }
/* 51 */       i++; this.render.getClass(); } while (i < 20);
/*    */   }
/*    */ 
/*    */   public void specialfire(MouseEvent e, Jiki jiki, int part)
/*    */   {
/* 60 */     if (System.currentTimeMillis() - this.lastfiredtime < this.interval) {
/* 61 */       return;
/*    */     }
/*    */ 
/* 64 */     this.lastfiredtime = System.currentTimeMillis();
/* 65 */     Point start = new Point(jiki.getX() + 50, jiki.getY() + 16 + (part - 2) * 20);
/* 66 */     Point target = new Point(e.getX(), e.getY());
/* 67 */     int i = 0;
/*    */     do { for (int j = 0; j < 20; j++)
/* 69 */         if (!this.render.jikibullet[i][part].isUsed()) {
/* 70 */           target = new Point((int)(1000.0D * Math.random()), (int)(700.0D * Math.random()));
/* 71 */           this.render.jikibullet[i][part].shot(start, target);
/*    */         }
/* 67 */       i++; this.render.getClass(); } while (i < 20);
/*    */   }
/*    */ }

/* Location:           M:\Documents\shootinggame\stg_build_0014\
 * Qualified Name:     game.KijyuUnit
 * JD-Core Version:    0.6.2
 */