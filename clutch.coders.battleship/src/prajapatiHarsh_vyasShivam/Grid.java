package prajapatiHarsh_vyasShivam;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


public class Grid {

	private ArrayList<JButton> btns = new ArrayList<>();
	private ArrayList<JLabel> lbls = new ArrayList<>();
	private ArrayList<Integer> shipsAI = new ArrayList<>();
	private ArrayList<Integer> shipsP1 = new ArrayList<>();
	private AI comp;
	private JButton btnPlaceShips;
	private JLabel lblInfo;

	public Grid(JButton btn, JLabel lbl) {
		comp = new AI(this);
		this.btnPlaceShips = btn;
		this.lblInfo = lbl;
	}

	public void createBoard(JLabel lblP1, JLabel lblAI) {
		// Creating a grid of buttons and labels
		for (int i = 0; i < 100; i++) {
			lbls.add(new JLabel(String.valueOf(i + 1)));
			lbls.get(i).setBackground(Color.cyan);
			lbls.get(i).setOpaque(true);
			lblP1.add(lbls.get(i));

			btns.add(new JButton());
			btns.get(i).setBackground(Color.cyan);
			lblAI.add(btns.get(i));
			btns.get(i).setEnabled(false);
			btns.get(i).addActionListener(new HitListener(i));
		}

	}

	public JLabel getLbl(int i) {

		return lbls.get(i);
	}

	public void setLblColor(int i) {
		lbls.get(i).setBackground(Color.gray);
	}

	public void setBtnColor(int a) {
		btns.get(a).setBackground(Color.gray);
	}

	public void addAILocation(int a) {
		shipsAI.add(a);
	}
	
	public void addP1Location(int a) {
		shipsP1.add(a);
	}
	
	public int getP1ShipSize(){
		return shipsP1.size();
	}

	public JButton getBtn(int i) {

		return btns.get(i);
	}
	
	public void removePlaceShipsBtn(){
		btnPlaceShips.setVisible(false);
		lblInfo.remove(btnPlaceShips);
		JOptionPane.showMessageDialog(null, "The Game has begun!\nClick on the Boxes on the Right to Fire at the Computer!");
	}

	public void disableBtns(){
		for(int i = 0; i<btns.size(); i++){
			btns.get(i).setEnabled(false);
		}
	}
	
	public void enableBtns(){
		for(int i = 0; i<btns.size(); i++){
			btns.get(i).setEnabled(true);
		}
	}
	
	public boolean getShipLocation(int location){
		
		for(int a : shipsAI){
			if (a == location){
				return true;
			}
		}
		
		return false;
	}
	
	private class HitListener implements ActionListener {

		private int i;

		public HitListener(int i) {
			this.i = i;
		}
		
		private boolean ship(int location){
			
			for(int a : shipsAI){
				if (a == location){
					return true;
				}
			}
			
			return false;
		}

		public void actionPerformed(ActionEvent event) {

				if (ship(i)) {
					btns.get(i).setBackground(Color.red);
					btns.get(i).setEnabled(false);
				} else {
					btns.get(i).setBackground(Color.blue);
					btns.get(i).setEnabled(false);
				}
			
			try {
				comp.missile();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(playerWin()){
				JOptionPane.showMessageDialog(null, "Player 1 Wins!\nPress OK to Quit");
				System.exit(0);
			}
		}
	}

		private boolean playerWin() {
			int count = 0;
			for(JButton element: btns){
				if(element.getBackground() == Color.red){
					count++;
				}
			}
			if(count == 16){
				return true;
			}
			return false;
		}
}
