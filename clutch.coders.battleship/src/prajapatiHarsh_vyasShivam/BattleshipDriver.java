package prajapatiHarsh_vyasShivam;

import javax.swing.JFrame;

/**
 * Creates the frame and adds a panel in the frame
 * Organizes the layout and makes the frame visible
 * 
 * @author Harsh Prajapati and Shivam Vyas
 *
 */
public class BattleshipDriver {

	public static void main (String [] args){
		
		JFrame frame = new JFrame("Battleship");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new BattleshipPanel());
		frame.pack();
		frame.setVisible(true);
		
	}
	
}
