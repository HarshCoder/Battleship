package prajapatiHarsh_vyasShivam;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BattleshipPanel extends JPanel {

	
	// Image img;
	JButton btnPlay;
	JButton btnPlaceShips = new JButton("Place Ships");
	JLabel lblP1;
	JLabel lblInfo = new JLabel();
	JLabel lblAI;
	JLabel lblTitle;
	JLabel lblPlayer1;
	JLabel lblComp;
	Grid grid;
	Ships ship;
	AI comp;
	Update update;

	public BattleshipPanel() {

		// Setting Layout,color,font, and size of the Grid
		setLayout(new GridLayout(1, 3, 5, 5));
		setBackground(new Color(20, 50, 60));
		setPreferredSize(new Dimension(2000, 500));
		setFont(new Font("Arial", Font.BOLD, 12));

		// Creating Buttons
		btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ButtonListener());

		
		btnPlaceShips.addActionListener(new ButtonListener());

		// Creating Labels
		lblTitle = new JLabel("Battleship");
		lblTitle.setFont(new Font("Magneto", Font.BOLD, 80));
		lblTitle.setBackground(Color.darkGray);
		lblTitle.setForeground(Color.green);
		lblTitle.setOpaque(true);
		
		lblPlayer1 = new JLabel();
		lblPlayer1.setFont(new Font("Magneto", Font.BOLD, 60));
		lblPlayer1.setForeground(Color.white);

		lblComp = new JLabel();
		lblComp.setFont(new Font("Magneto", Font.BOLD, 60));
		lblComp.setForeground(Color.white);
		
		lblP1 = new JLabel();
		lblP1.setLayout(new GridLayout(10, 10, 1, 1));
		lblP1.setVisible(false);
		
		lblInfo.setLayout(new GridLayout(4, 1, 5, 5));
		lblInfo.add(lblTitle);
		lblInfo.add(lblPlayer1);
		lblInfo.add(lblComp);
		lblInfo.add(btnPlay);
		
		lblAI = new JLabel();
		lblAI.setLayout(new GridLayout(10, 10, 1, 1));
		lblAI.setVisible(false);

		// Adding the components to the Panel
		add(lblP1);
		add(lblInfo);
		add(lblAI);
		
		grid = new Grid(btnPlaceShips, lblInfo);
		update = new Update(grid);
		ship = new Ships(grid);
		comp = new AI(grid);
		
	}

	/*
	 * public void paint(Graphics g){ g.drawImage(img, 0, 0, null); }
	 */
	
	
	public void removeShipBtn(){
		lblInfo.remove(btnPlaceShips);
	}
	

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == btnPlay) {
				lblPlayer1.setText("<--You");
				lblComp.setText("Computer -->");
				
				update.createBoard(lblP1, lblAI);
				
				lblP1.setVisible(true);
				lblAI.setVisible(true);

				lblInfo.remove(btnPlay);
				lblInfo.add(btnPlaceShips);
				
				comp.placeShips();
			}

			if (event.getSource() == btnPlaceShips) {
				ship.selectShips(1, 0);
			}

		}

	}
	

}