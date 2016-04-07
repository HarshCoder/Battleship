package prajapatiHarsh_vyasShivam;

import javax.swing.JLabel;

public class Update {

	private Grid grid;
	
	public Update(Grid grid){
		this.grid = grid;
	}
	
	public void createBoard(JLabel lblP1, JLabel lblAI) {
		grid.createBoard(lblP1, lblAI);
	}
	
	public boolean updateBoard(int position, String rotation, int length, int player) {
		// Setting the Ships According to the Position given by the User.
		if (position > 0 && rotation != null) {

			// Removing the Ship type from the list of Ships

			if (rotation.equals("RIGHT")) {
				for (int i = 0; i < length; i++) {
					if (player == 1) {
						grid.setLblColor(position - 1 + i);
						grid.addP1Location(position - 1 + i);
					} else{
						//grid.setBtnColor(position - 1 + i);
						grid.addAILocation(position - 1 + i);
					}
				}
				return true;
			}
			if (rotation.equals("DOWN")) {
				for (int i = 0; i < length * 10; i += 10) {
					if (player == 1) {
						grid.setLblColor(position - 1 + i);
						grid.addP1Location(position - 1 + i);
					} else {
						//grid.setBtnColor(position  + i);
						grid.addAILocation(position - 1 + i);
					}
				}
				return true;
			}
			if (rotation.equals("LEFT")) {
				for (int i = 0; i < length; i++) {
					if (player == 1) {
						grid.setLblColor(position - 1 - i);
						grid.addP1Location(position - 1 - i);
					} else {
						//grid.setBtnColor(position - 1  - i);
						grid.addAILocation(position - 1 - i);
					}
				}
				return true;
			}
			if (rotation.equals("UP")) {
				for (int i = 0; i > (length * 10) * -1; i -= 10) {
					if (player == 1) {
						grid.setLblColor(position - 1 + i);
						grid.addP1Location(position - 1 + i);
					} else {
					//	grid.setBtnColor(position - 1 + i);
						grid.addAILocation(position - 1 + i);
					}
				}
				return true;
			}
		}
		return false;
	}
}