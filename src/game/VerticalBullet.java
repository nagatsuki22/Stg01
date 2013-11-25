/*   */ package game;
/*   */ 
/*   */ public class VerticalBullet extends StraightBullet
/*   */ {
/*   */   public void move()
/*   */   {
/* 5 */     this.y -= this.speed / 3.0D;
/* 6 */     if ((this.x < 0.0D) || (this.x > 1000.0D) || (this.y < 0.0D) || (this.y > 700.0D))
/* 7 */       this.used = false;
/*   */   }
/*   */ }