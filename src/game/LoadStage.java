/*    */ package game;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStreamReader;
/*    */ 
/*    */ public class LoadStage
/*    */ {
/*    */   int enes;
/*    */   int[][] stage;
/*    */ 
/*    */   public void load(String filename)
/*    */   {
/*    */     try
/*    */     {
/* 12 */       BufferedReader br = new BufferedReader(
/* 13 */         new InputStreamReader(getClass().getResourceAsStream(filename)));
/* 14 */       String line = br.readLine();
/* 15 */       this.enes = Integer.parseInt(line);
/* 16 */       this.stage = new int[this.enes][4];
/* 17 */       for (int i = 0; i < this.enes; i++) {
/* 18 */         line = br.readLine();
/* 19 */         this.stage[i][0] = Integer.parseInt(line);
/* 20 */         line = br.readLine();
/* 21 */         this.stage[i][1] = Integer.parseInt(line);
/* 22 */         line = br.readLine();
/* 23 */         this.stage[i][2] = Integer.parseInt(line);
/* 24 */         line = br.readLine();
/* 25 */         this.stage[i][3] = Integer.parseInt(line);
/*    */       }
/*    */     }
/*    */     catch (Exception localException) {
/*    */     }
/*    */   }
/*    */ 
/*    */   public Enemy getEnemy(int x, int y, int type, Render render) {
/* 33 */     Enemy enemy = null;
/* 34 */     switch (type) {
/*    */     case 0:
/* 36 */       enemy = new Zako1(x, y, render);
/* 37 */       break;
/*    */     case 1:
/* 39 */       enemy = new RoundShotEnemy(x, y, render);
/* 40 */       break;
/*    */     case 2:
/* 42 */       enemy = new HorizontalMoveEnemy(x, y, render);
/* 43 */       break;
/*    */     case 99:
/* 45 */       enemy = new Boss(x, y, render);
/*    */     }
/*    */ 
/* 48 */     return enemy;
/*    */   }
/*    */ }