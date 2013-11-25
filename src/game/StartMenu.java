package game;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class StartMenu extends JPanel{
	JLabel label1,label2,label3,label4,label5,label6,label7,label8,label9,label10,label11;
	int whereis = 0;
	int wherewas = 0;
	boolean ready = false;
	int soubiis = -1;
	int soubi1is = -1;
	int soubi2is = -1;
	int soubi3is = -1;
	int soubimotteru = Math.min(Math.min(soubi1is,soubi2is),soubi3is);
	String default1 = "Choose Character";
	String default2 = "Back";
	String default3 = "Marisa Kirisame";
	String default4 = "Choose Equipment";
	String default5 = "Center Unit";
	String default6  = "Middle Unit";
	String default7 = "Outer Unit";
	String default8 = "Choose";
	String default9 = "Gun Unit";
	String default10 = "Missile Unit";
	String default11 = "Sally!";
	String nullstring = "";
	JPanel panel1;
	JPanel panel2;
	boolean isFocused = false;
	boolean isSoubi = false;
	
	public StartMenu(){
		label1 = new JLabel(default1);
		label2 = new JLabel(">Marisa Kirisame");
		label3 = new JLabel(default2);
		label4 = new JLabel(nullstring);
		label5 = new JLabel(nullstring);
		label6 = new JLabel(nullstring);
		label7 = new JLabel(nullstring);
		label8 = new JLabel(nullstring);
		label9 = new JLabel(nullstring);
		label10 = new JLabel(nullstring);
		label11 = new JLabel(nullstring);
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel2.setLayout(new BorderLayout());
		JPanel subpanel1 = new JPanel();
		JPanel subpanel2 = new JPanel();
		subpanel2.setPreferredSize(new Dimension(200,300));
		subpanel1.setPreferredSize(new Dimension(200,300));
		subpanel1.setLayout(new BorderLayout());
		subpanel2.setLayout(new BorderLayout());
		subpanel1.add(label5,BorderLayout.NORTH);
		subpanel1.add(label6,BorderLayout.CENTER);
		subpanel1.add(label7,BorderLayout.SOUTH);
		subpanel2.add(label8,BorderLayout.NORTH);
		subpanel2.add(label9,BorderLayout.CENTER);
		subpanel2.add(label10,BorderLayout.SOUTH);
		panel2.setPreferredSize(new Dimension(500,300));
		panel1.setPreferredSize(new Dimension(150,300));
		panel2.add(label4,BorderLayout.NORTH);
		panel2.add(subpanel2,BorderLayout.EAST);
		panel2.add(subpanel1,BorderLayout.WEST);
		panel2.add(label11,BorderLayout.SOUTH);
		panel1.add(label1,BorderLayout.NORTH);
		panel1.add(label2,BorderLayout.CENTER);
		panel1.add(label3,BorderLayout.SOUTH);
		add(panel1);
		add(panel2);
	}
	public void soubisentaku(int character){
		switch(character){
		case 0:
			label4.setText(default4);
			label5.setText(default5);
			label6.setText(default6);
			label7.setText(default7);
			label8.setText(default8);
			label9.setText(default8);
			label10.setText(default8);
			label11.setText(default11);
			isSoubi = true;
			wherewas = whereis;
			whereis = 0;
			break;
		}
	}


}
