package prajapatiHarsh_vyasShivam;

import java.awt.Color;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.Random;


public class AI {

	private Grid grid;
	private Random rand = new Random();
	private ArrayList<Integer> lblHit = new ArrayList<>();
	private int randNum = 0;
	private int totalHits = 0;
	private boolean hit;

	private boolean goDown;
	private boolean goUp;
	private boolean goRight;
	private boolean goLeft;

	int countUp = 0;
	int countDown = 0;

	public AI(Grid grid) {
		this.grid = grid;
	}

	public void placeShips() {
		Ships ship = new Ships(grid);
		for (int i = 0; i < 5; i++) {
			ship.selectShips(2, i);
		}
	}

	public void missile() throws InterruptedException {

		if (!hit && !goDown && !goUp && !goRight && !goLeft) {
			getRand();
		} else if (hit && !goDown && !goUp && !goRight && !goLeft) {
			goDown();
		} else if (goDown && hit) {
			goDown();
		} else if (goDown && !hit) {
			goUp();
		} else if (goUp && hit) {
			goUp();
		} else if (goUp && !hit) {
			while (grid.getLbl(randNum).getBackground() != Color.red) {
				randNum += 10;
			}
			goRight();
		} else if (goRight && hit) {
			goRight();
		} else if (goRight && !hit) {
			goLeft();
		} else if (goLeft && hit) {
			goLeft();
		} else if (goLeft && !hit) {
			getRand();
		} else {
			getRand();
		}

		if (randNum >= 100 || randNum < 0) {
			getRand();
		}
		lblHit.add(randNum);
		checkHit();

		System.out.println(randNum);
		if(AIWin()){
			JOptionPane.showMessageDialog(null, "Aww The Computer Won!\nPress OK to Quit");
			System.exit(0);
		}
	}
	
	private boolean AIWin() {
		if(totalHits == 16){
			return true;
		}
		return false;
	}
	private void checkHit() {
		if (randNum >= 100 || randNum < 0) {
			getRand();
		}
		if (grid.getLbl(randNum).getBackground() == Color.gray) {
			grid.getLbl(randNum).setBackground(Color.red);
			totalHits++;
			hit = true;
		} else if (grid.getLbl(randNum).getBackground() == Color.cyan) {
			grid.getLbl(randNum).setBackground(Color.blue);
			hit = false;
		}
	}

	private void getRand() {
		goLeft = false;
		goUp = false;
		goRight = false;
		goDown = false;
		while (!validNum(randNum)) {
			randNum = rand.nextInt(100);
		}
	}

	private void goDown() {
		goDown = true;
		goUp = false;
		goRight = false;
		goLeft = false;

		while (!validNum(randNum)) {

			countDown++;
			if (countDown > 100) {
				goLeft();
				break;
			}
			if (randNum >= 100) {
				randNum-=10;
				goUp();
				break;
			}
			if (randNum >= 0 && randNum < 100) {
				randNum += 10;
			}

		}

	}

	private void goUp() {
		goUp = true;
		goDown = false;
		goRight = false;
		goLeft = false;

		while (!validNum(randNum)) {
			countUp++;
			if (countUp > 100) {
				goRight();
				break;
			}
			if (randNum >= 0 && randNum < 100) {
				randNum -= 10;
			}
			if (randNum < 0) {
				randNum += 10;
				goRight();
				break;
			}
		}
	}

	private void goRight() {
		goDown = false;
		goLeft = false;
		goRight = true;
		goUp = false;

		while (!validNum(randNum)) {
			if (randNum >= 0 && randNum < 100) {
				randNum += 1;
			}
			if (randNum > 100) {
				goLeft();
			}
		}
	}

	private void goLeft() {
		goLeft = true;
		goRight = false;
		goUp = false;
		goDown = false;

		while (!validNum(randNum)) {
			if (randNum >= 0 && randNum < 100) {
				randNum -= 1;
			}
			if (randNum < 0) {
				getRand();
			}
		}

	}

	private boolean validNum(int randNum) {

		for (int i : lblHit) {
			if (i == randNum) {
				return false;
			}
		}
		if (randNum > 100 || randNum < 0) {
			return false;
		}

		return true;
	}

}