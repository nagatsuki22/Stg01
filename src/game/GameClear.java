package game;
import java.awt.*;

import javax.swing.*;

public class GameClear extends JPanel{
	JLabel label,label2,label3;
	boolean isFocused;
	public GameClear(){
		setPreferredSize(new Dimension(1000,700));
		setBackground(Color.white);
		isFocused = false;
		label = new JLabel(" ");
		label2 = new JLabel(" ");
		label3 = new JLabel(" ");
		setLayout(new BorderLayout());
		add(label,"North");
		add(label2,"Center");
		add(label3,"South");
	}
	public void setResults(boolean cleared,int point){
		if(cleared){
			label.setText("Game Clear!");
			label2.setText("“¾“_"+point);
		}
		else{
			label.setText("Game Over");
			label2.setText("“¾“_"+point);
		}
	}
	

}
