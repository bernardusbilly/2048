/* Board.java */

/**
 * This is the board state of the 2048 game which is saved in the 2-dimensional array
 */

package game;

public class Board {
	
	// Constant
	public static final int SIZE = 4;
	public static final int ON 	 = 1;
	public static final int OFF  = 0;
	
	// Class variable
	protected int[][] board;
	protected int indicator;
	protected int size;

	private int counter;
	private int i;
	private int j;

	/** 
	 * Default constructor that makes a 4x4  grid
	 */
	public Board () {
		this.size = SIZE;
		board = new int[size][size];
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				board[i][j] = 0;
			}
		}
	}

	/**
	 * Constuctor for a gameboard with a given size
	 */
	public Board (int size) {
		this.size = size;
		board = new int[size][size];
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				board[i][j] = 0;
			}
		}
	}

	public Board copy() {
	 	Board temp = new Board();
	 	for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				temp.setNumber(board[i][j],i,j);
			}
		}
		return temp;
	}

	public void setNumber (int val, int x, int y) {
		board[x][y] = val;
	}

	public int element (int x, int y) {
		return board[x][y];
	}

	public boolean isFull() {
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				if (board[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isOver() {
		Board temp1, temp2, temp3, temp4;
		temp1 = copy();
		temp2 = copy();
		temp3 = copy();
		temp4 = copy();

		if (temp1.moveUp() == false) {
			if (temp2.moveRight() == false) {
				if (temp3.moveDown() == false) {
					if (temp4.moveLeft() == false) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public int bestScore() {
		int bestScore = 0;
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				if (board[i][j] > bestScore) {
					bestScore = board[i][j];
				}
			}
		}
		return bestScore;
	}

	public int totalScore() {
		int totalScore = 0;
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				totalScore += board[i][j];
			}
		}
		return totalScore;
	}

	public String toString() {
		for (j = 0; j < size; j++) {
			for (i = 0; i < size; i++) {
				System.out.print("|");
				if (board[i][j] == 0) {
					System.out.print("\t\t");
				}
				else {
					System.out.print("\t" + board[i][j] + "\t");
				}
			}
			System.out.print("|\n");
		}
		System.out.println("\n");
		return "";
	}

	public boolean moveRight() {
		indicator = OFF;
		counter = 0;
		i = 0;
		j = 0;

		// sum up the same number
		for (j = 0; j < size; j++) {
			i = size-1;
			while (i > 0) {
				counter = i-1;
	
				while ((board[i][j] != 0) && (counter > 0) && (board[counter][j] == 0)) {
					counter--;
				}

				if (board[i][j] == board[counter][j]) {
					if (board[i][j] != 0) {
						indicator = ON;
					}
					board[i][j] *= 2;
					board[counter][j] = 0;
					i = counter;
				}
				
				i--;

			}
		}

		// shift the number
		for (j = 0; j < size; j++) {
			i = size-1;
			counter = size-1;
			while (i >= 0) {
				if (board[i][j] != 0) {
					board[counter][j] = board[i][j];
					if (counter != i) {
						indicator = ON;
						board[i][j] = 0;	
					}
					counter--;
				}
				i--;
			}
		}

		if (indicator == OFF) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean moveLeft() {
		indicator = OFF;
		counter = 0;
		i = 0;
		j = 0;

		// sum up the same number
		for (j = 0; j < size; j++) {
			i = 0;
			while (i < size-1) {
				counter = i+1;
	
				while ((board[i][j] != 0) && (counter < size-1) && (board[counter][j] == 0)) {
					counter++;
				}

				if (board[i][j] == board[counter][j]) {
					if (board[i][j] != 0) {
						indicator = ON;
					}
					board[i][j] *= 2;
					board[counter][j] = 0;
					i = counter;
				}
				
				i++;

			}
		}

		// shift the number
		for (j = 0; j < size; j++) {
			i = 0;
			counter = 0;
			while (i < size) {
				if (board[i][j] != 0) {
					board[counter][j] = board[i][j];
					if (counter != i) {
						indicator = ON;
						board[i][j] = 0;	
					}
					counter++;
				}
				i++;
			}
		}

		if (indicator == OFF) {
			return false;
		}
		else {
			return true;
		}

	}

	public boolean moveUp() {
		indicator = OFF;
		counter = 0;
		i = 0;
		j = 0;

		// sum up the same number
		for (j = 0; j < size; j++) {
			i = 0;
			while (i < size-1) {
				counter = i+1;
	
				while ((board[j][i] != 0) && (counter < size-1) && (board[j][counter] == 0)) {
					counter++;
				}

				if (board[j][i] == board[j][counter]) {
					if (board[j][i] != 0) {
						indicator = ON;
					}
					board[j][i] *= 2;
					board[j][counter] = 0;
					i = counter;
				}
				
				i++;

			}
		}

		// shift the number
		for (j = 0; j < size; j++) {
			i = 0;
			counter = 0;
			while (i < size) {
				if (board[j][i] != 0) {
					board[j][counter] = board[j][i];
					if (counter != i) {
						indicator = ON;
						board[j][i] = 0;	
					}
					counter++;
				}
				i++;
			}
		}

		if (indicator == OFF) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean moveDown() {
		indicator = OFF;
		counter = 0;
		i = 0;
		j = 0;

		// sum up the same number
		for (j = 0; j < size; j++) {
			i = size-1;
			while (i > 0) {
				counter = i-1;
	
				while ((board[j][i] != 0) && (counter > 0) && (board[j][counter] == 0)) {
					counter--;
				}

				if (board[j][i] == board[j][counter]) {
					if (board[j][i] != 0) {
						indicator = ON;
					}
					board[j][i] *= 2;
					board[j][counter] = 0;
					i = counter;
				}
				
				i--;

			}
		}

		// shift the number
		for (j = 0; j < size; j++) {
			i = size-1;
			counter = size-1;
			while (i >= 0) {
				if (board[j][i] != 0) {
					board[j][counter] = board[j][i];
					if (counter != i) {
						indicator = ON;
						board[j][i] = 0;	
					}
					counter--;
				}
				i--;
			}
		}

		if (indicator == OFF) {
			return false;
		}
		else {
			return true;
		}
	}
	
}