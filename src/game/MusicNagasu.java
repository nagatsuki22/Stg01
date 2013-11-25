/*    */ package game;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import javax.sound.sampled.AudioInputStream;
/*    */ import javax.sound.sampled.SourceDataLine;
/*    */ 
/*    */ public class MusicNagasu extends Thread
/*    */ {
/*    */   long lasttime;
/*    */   int num;
/* 11 */   SourceDataLine[] lines = new SourceDataLine[100];
/* 12 */   boolean playing = false;
/*    */   boolean halt;
/*    */   AudioInputStream stream;
/* 15 */   byte[] buffer = new byte[180634];
/*    */ 
/* 17 */   public void setWavePlayerData(int num, SourceDataLine[] lines, boolean playing, AudioInputStream stream, byte[] buffer) { this.num = num;
/* 18 */     this.lines = lines;
/* 19 */     this.playing = playing;
/* 20 */     this.stream = stream;
/* 21 */     this.buffer = buffer; }
/*    */ 
/*    */   public MusicNagasu()
/*    */   {
/* 25 */     this.halt = false;
/*    */   }
/*    */ 
/*    */   public void run() {
/* 29 */     int numBytesRead = 0;
/* 30 */     while (numBytesRead != -1)
/* 31 */       if (this.playing) {
/* 32 */         System.out.println(this.playing);
/*    */         try {
/* 34 */           numBytesRead = this.stream.read(this.buffer, 0, this.buffer.length);
/*    */         } catch (IOException e) {
/* 36 */           e.printStackTrace();
/*    */         }
/* 38 */         if (numBytesRead != -1)
/* 39 */           this.lines[this.num].write(this.buffer, 0, numBytesRead);
/*    */       }
/*    */   }
/*    */ 
/*    */   public void halt_() {
/* 44 */     this.halt = true;
/* 45 */     interrupt();
/*    */   }
/*    */ }

/* Location:           M:\Documents\shootinggame\stg_build_0014\
 * Qualified Name:     game.MusicNagasu
 * JD-Core Version:    0.6.2
 */