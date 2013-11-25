/*    */ package game;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Image;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.io.PrintStream;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class Unit extends JPanel
/*    */ {
/* 10 */   public int x = -50;
/* 11 */   public int y = -50;
/*    */   public int part;
/* 13 */   public int type = 0;
/*    */   Units u;
/*    */   private static Image unit1;
/*    */ 
/*    */   Unit(Render render, Jiki jiki, int part, int type)
/*    */   {
/* 18 */     this.part = part;
/* 19 */     this.type = type;
/* 20 */     switch (type) {
/*    */     case 0:
/* 22 */       ImageIcon icon = new ImageIcon(getClass().getResource("Unit.png"));
/* 23 */       unit1 = icon.getImage();
/* 24 */       this.u = new KijyuUnit(render, jiki, part);
/* 25 */       break;
/*    */     case 1:
/* 27 */       ImageIcon icon2 = new ImageIcon(getClass().getResource("Unit.png"));
/* 28 */       unit1 = icon2.getImage();
/* 29 */       this.u = new MissileUnit(render, jiki, part);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void draw(Graphics g, Jiki jiki)
/*    */   {
/* 35 */     this.x = (jiki.getX() - 24);
/* 36 */     this.y = (jiki.getY() - 24);
/* 37 */     if ((this.part == 0) || (this.part == 4)) {
/* 38 */       this.x -= 30;
/* 39 */       this.y += 30;
/*    */     }
/* 41 */     if ((this.part == 1) || (this.part == 3)) {
/* 42 */       this.y += 60;
/*    */     }
/* 44 */     if (this.part == 2) {
/* 45 */       this.x += 60;
/*    */     }
/* 47 */     g.drawImage(unit1, this.x, this.y, null);
/* 48 */     this.u.draw(g, jiki);
/*    */   }
/*    */   public void fire(MouseEvent e, Jiki jiki, int part) {
/* 51 */     this.u.fire(e, jiki, part);
/* 52 */     System.out.println("Fired for" + e.getX() + "," + e.getY());
/*    */   }
/*    */   public void specialfire(MouseEvent e, Jiki jiki, int part) {
/* 55 */     this.u.specialfire(e, jiki, part);
/*    */   }
/*    */ }

/* Location:           M:\Documents\shootinggame\stg_build_0014\
 * Qualified Name:     game.Unit
 * JD-Core Version:    0.6.2
 */