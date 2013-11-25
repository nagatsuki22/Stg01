/*    */ package game;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.URL;
/*    */ import javax.sound.sampled.AudioFormat;
/*    */ import javax.sound.sampled.AudioInputStream;
/*    */ import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
/*    */ import javax.sound.sampled.DataLine.Info;
/*    */ import javax.sound.sampled.LineUnavailableException;
/*    */ import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
/*    */ 
/*    */ public class WavePlayer
/*    */ {
/* 15 */   private static SourceDataLine[] lines = new SourceDataLine[100];
/* 16 */   private static int count = 0;
/*    */   static long last;
/*    */   static boolean playing;
/*    */   static AudioFormat format;
/*    */   static DataLine.Info info;
/* 21 */   static int num = 0;
/*    */   static MusicNagasu Mus;
/*    */   static Thread thread;
/*    */   static AudioInputStream stream;
/* 25 */   static byte[] buffer = new byte[180634];
/*    */ 
/* 27 */   public static void load(URL url) throws UnsupportedAudioFileException, IOException, LineUnavailableException { stream = AudioSystem.getAudioInputStream(url);
/* 28 */     format = stream.getFormat();
/* 29 */     info = new DataLine.Info(SourceDataLine.class, format, -1);
/* 30 */     lines[count] = ((SourceDataLine)AudioSystem.getLine(info));
/* 31 */     lines[count].open(format, buffer.length);
/* 32 */     count += 1; }
/*    */ 
/*    */   public static void load(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
/* 35 */     URL url = WavePlayer.class.getResource(filename);
/* 36 */     load(url);
/*    */   }
/*    */   public static void play(int number) throws LineUnavailableException, IOException {
/* 39 */     num = number;
/* 40 */     lines[num].start();
/* 41 */     playing = true;
/* 42 */     Mus = new MusicNagasu();
/* 43 */     Mus.setWavePlayerData(num, lines, playing, stream, buffer);
/* 44 */     Mus.start();
/*    */   }
/*    */ 
/*    */   public static void stop(int number) {
/* 48 */     Mus.playing = false;
/*    */   }
/*    */ 
/*    */   public static void restart(int num) {
/* 52 */     Mus = new MusicNagasu();
/* 53 */     Mus.setWavePlayerData(num, lines, playing, stream, buffer);
/* 54 */     Mus.start();
/*    */   }
/*    */ }