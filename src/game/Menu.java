/*    */ package game;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.GridLayout;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class Menu extends JPanel
/*    */ {
/*    */   JLabel label0;
/*    */   JLabel label1;
/*    */   JLabel label2;
/*    */   JLabel label3;
/* 14 */   int whereis = 2;
/*    */   ImageIcon menuicon;
/* 16 */   boolean isFocused = false;
/* 17 */   String default1 = "Start Game";
/* 18 */   String default2 = "Setting";
/* 19 */   String default3 = "Exit";
/*    */ 
/* 21 */   public Menu() { this.menuicon = new ImageIcon(getClass().getResource("menu.png"));
/* 22 */     setBackground(Color.white);
/* 23 */     setPreferredSize(new Dimension(1000, 700));
/* 24 */     setLayout(new GridLayout(4, 1));
/* 25 */     this.label0 = new JLabel(this.menuicon);
/* 26 */     this.label1 = new JLabel(">Start Game");
/* 27 */     this.label2 = new JLabel(this.default2);
/* 28 */     this.label3 = new JLabel(this.default3);
/* 29 */     add(this.label0);
/* 30 */     add(this.label1);
/* 31 */     add(this.label2);
/* 32 */     add(this.label3); }
/*    */ 
/*    */   public static void main(String[] args) {
/* 35 */     JFrame frame = new JFrame("MeunDebug");
/* 36 */     frame.setDefaultCloseOperation(3);
/* 37 */     Menu p = new Menu();
/* 38 */     frame.add(p);
/* 39 */     frame.pack();
/* 40 */     frame.setVisible(true);
/*    */   }
/*    */ }

/* Location:           M:\Documents\shootinggame\stg_build_0014\
 * Qualified Name:     game.Menu
 * JD-Core Version:    0.6.2
 */