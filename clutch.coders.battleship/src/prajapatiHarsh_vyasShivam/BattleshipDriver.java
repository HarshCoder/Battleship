package prajapatiHarsh_vyasShivam;

import javax.swing.JFrame;

public class BattleshipDriver {

	public static void main (String [] args){
		
		JFrame frame = new JFrame("Battleship");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new BattleshipPanel());
		frame.pack();
		frame.setVisible(true);
	}
}