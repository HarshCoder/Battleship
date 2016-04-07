package prajapatiHarsh_vyasShivam;

import java.awt.Color;
import java.util.Random;
import javax.swing.JOptionPane;

public class Ships {

	private String[] shipList = { "(5) Aircraft Carrier", "(4) Battle Ship", "(3) Cruiser", "(3) Submarine", "(1) Destroyer" };
	private int player;
	private String shipType;
	private String[] shipRotations = { "UP", "DOWN", "LEFT", "RIGHT" };
	private Random rand = new Random();
	private Grid grid;
	Update update;

	public Ships(Grid grid) {
		this.grid = grid;
		update = new Update(grid);
	}

	private void removeShip(String[] shipList, String shipType) {
		for (int i = 0; i < shipList.length; i++) {
			if (shipList[i].equals(shipType)) {
				shipList[i] = shipType + " is Used!";
			}
		}
	}

	public void selectShips(int a, int ship) {
		this.player = a;
		if (player == 1) {
			shipType = (String) JOptionPane.showInputDialog(null, "Which Ship would you like to place:", "Select Ship",
					JOptionPane.PLAIN_MESSAGE, null, shipList, null);
		} else {
			shipType = shipList[ship];
		}
		if (shipType != null) {
			if (shipType.equals("(5) Aircraft Carrier")) {
				arrangeShips(5, shipList, shipType);
			} else if (shipType.equals("(4) Battle Ship")) {
				arrangeShips(4, shipList, shipType);
			} else if (shipType.equals("(3) Cruiser")) {
				arrangeShips(3, shipList, shipType);
			} else if (shipType.equals("(3) Submarine")) {
				arrangeShips(3, shipList, shipType);
			} else if (shipType.equals("(1) Destroyer")) {
				arrangeShips(1, shipList, shipType);
			} else {
				JOptionPane.showMessageDialog(null, "That's already Used!");
			}
		}
	}

	private void arrangeShips(int length, String[] shipList, String shipType) {
		String rotation = "";
		int position = 0;
		boolean repeat = true;

		while (repeat) {
			if (player == 1) {
				position = getPosition();
				if (position != -1) {

					rotation = (String) JOptionPane.showInputDialog(null, "Which direction do you want the ship?",
							"Direction", JOptionPane.PLAIN_MESSAGE, null, shipRotations, null);
				} else {
					repeat = false;
				}

			} else {
				rotation = shipRotations[rand.nextInt(shipRotations.length)];
				while(position == 0){
					position = rand.nextInt(101);
				}
			}

			if (position > 0 || rotation != null) {
				if (isValid(position, length, rotation)) {
					repeat = false;
					if(update.updateBoard(position, rotation, length, player)){
						removeShip(shipList, shipType);
					}
				}

				if (repeat && player == 1) {
					JOptionPane.showMessageDialog(null,
							"That Location is INVALID, since it is overlapping a ship or going out of the grid!");

				}

			} else {
				repeat = false;

			}

		}

		
		if(grid.getP1ShipSize() == 16){
			System.out.println(grid.getP1ShipSize());
			grid.removePlaceShipsBtn();
			grid.enableBtns();
		}
	}

	private boolean isValid(int position, int length, String rotation) {

		int directionEquation = 0;
		if (rotation != null) {
			if (rotation.equals("RIGHT")) {
				for (int i = 0; i < length; i++) {
					directionEquation = position - 1 + i;
					if (isOutOfBounce(directionEquation, length, position, "Right")) {
						return false;
					}
					
					if(player == 1){
						if (grid.getLbl(directionEquation).getBackground() == Color.gray) {
							return false;
						}
					}else{
						if (grid.getShipLocation(directionEquation)) {
							return false;
						}
					}
				}
			}
			if (rotation.equals("DOWN")) {
				for (int i = 0; i < length * 10; i += 10) {
					directionEquation = position - 1 + i;
					if (isOutOfBounce(directionEquation, length, position, "Down")) {
						return false;
					}

					if(player == 1){
						if (grid.getLbl(directionEquation).getBackground() == Color.gray) {
							return false;
						}
					}else{
						if (grid.getShipLocation(directionEquation)) {
							return false;
						}
					}
				}
			}
			if (rotation.equals("LEFT")) {
				for (int i = 0; i < length; i++) {
					directionEquation = position - 1 - i;
					if (isOutOfBounce(directionEquation, length, position, "Left")) {
						return false;
					}

					if(player == 1){
						if (grid.getLbl(directionEquation).getBackground() == Color.gray) {
							return false;
						}
					}else{
						if (grid.getShipLocation(directionEquation)) {
							return false;
						}
					}
				}
			}
			if (rotation.equals("UP")) {
				for (int i = 0; i > (length * 10) * -1; i -= 10) {
					directionEquation = position - 1 + i;
					if (isOutOfBounce(directionEquation, length, position, "Up")) {
						return false;
					}

					if(player == 1){
						if (grid.getLbl(directionEquation).getBackground() == Color.gray) {
							return false;
						}
					}else{
						if (grid.getShipLocation(directionEquation)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private boolean isOutOfBounce(int directionEquation, int length, int position, String direction) {
		//For Up or Down Check
		if (directionEquation < 0 || directionEquation > 99) {
 			return true;
 		}
		
		int finalPosition = 0;
		if (direction.equals("Left")) {
 			finalPosition = position - length + 1;
 		}else if (direction.equals("Right")) {
 			finalPosition = position + length - 1;
 		}
 
		if (direction.equals("Right") || direction.equals("Left")) {
			if ((position <= 10 && finalPosition > 10) || (position >= 11 && finalPosition < 11)) {
				return true;
			}
			if ((position <= 20 && finalPosition > 20) || (position >= 21 && finalPosition < 21)) {
				return true;
			}
			if ((position <= 30 && finalPosition > 30) || (position >= 31 && finalPosition < 31)) {
				return true;
			}
			if ((position <= 40 && finalPosition > 40) || (position >= 41 && finalPosition < 41)) {
				return true;
			}
			if ((position <= 50 && finalPosition > 50) || (position >= 51 && finalPosition < 51)) {
				return true;
			}
			if ((position <= 60 && finalPosition > 60) || (position >= 61 && finalPosition < 61)) {
				return true;
			}
			if ((position <= 70 && finalPosition > 70) || (position >= 71 && finalPosition < 71)) {
				return true;
			}
			if ((position <= 80 && finalPosition > 80) || (position >= 81 && finalPosition < 81)) {
				return true;
			}
			if ((position <= 90 && finalPosition > 90) || (position >= 91 && finalPosition < 91)) {
				return true;
			}
		}
 		return false;
	}

	private int getPosition() {
		int position = 0;
		boolean valid = false;
		while (!valid) {
			String place = JOptionPane.showInputDialog(null, "Where would you like to place the ship?");
			if (place != null) {
				try {
					position = Integer.valueOf(place);
					if (position > 0 && position <= 100) {
						valid = true;
					} else {
						JOptionPane.showMessageDialog(null,
								"Please Enter a positive and valid location ranging from 0 - 100!");
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Please Enter a Valid Loction!");
					valid = false;
				}
			} else {
				position = -1; // When user cancels the JOptionPane resulting in
								// getting no input for position
				valid = true;
			}
		}
		return position;
	}

}