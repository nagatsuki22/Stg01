/*    */ package game;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.IOException;
/*    */ import javax.sound.sampled.AudioFormat;
/*    */ import javax.sound.sampled.AudioInputStream;
/*    */ 
/*    */ public class WaveData
/*    */ {
/*    */   public byte[] data;
/*    */   public int position;
/*    */   public AudioFormat format;
/* 13 */   public boolean playing = false;
/*    */   public int Rateperframe;
/*    */ 
/*    */   public WaveData(byte[] data, AudioFormat format)
/*    */   {
/* 17 */     this.data = data;
/* 18 */     this.position = 0;
/* 19 */     this.format = format;
/*    */   }
/*    */   public WaveData(AudioInputStream audioStream) throws IOException {
/* 22 */     this.position = 0;
/* 23 */     this.format = audioStream.getFormat();
/*    */ 
/* 25 */     int length = (int)(audioStream.getFrameLength() * this.format.getFrameSize());
/* 26 */     this.data = new byte[length];
/* 27 */     DataInputStream is = new DataInputStream(audioStream);
/* 28 */     is.readFully(this.data);
/*    */   }
/*    */ 
/*    */   public void getSampleRate(int frame) {
/* 32 */     this.Rateperframe = ((int)(frame * (this.format.getChannels() * this.format.getSampleRate() * this.format.getSampleSizeInBits() / 8.0F) / 1000.0F));
/*    */   }
/*    */ }

/* Location:           M:\Documents\shootinggame\stg_build_0014\
 * Qualified Name:     game.WaveData
 * JD-Core Version:    0.6.2
 */