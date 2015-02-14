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

	public void generateRandom() {
		int x, y;
		// Find an element for a number to be placed
		do {
			x = randomWithRange(0, size-1);
			y = randomWithRange(0, size-1);
		} while(board[x][y] != 0);

		board[x][y] = random(2, 4, 90);
	}


	/**
	 * This function does not change the original board
	 * @return (boolean) whether it can move to the directed move or not
	*/
	public boolean tryMoveUp() {
		Board tempBoard = copy();
		return tempBoard.moveUp();
	}
	public boolean tryMoveRight() {
		Board tempBoard = copy();
		return tempBoard.moveRight();
	}
	public boolean tryMoveDown() {
		Board tempBoard = copy();
		return tempBoard.moveDown();
	}
	public boolean tryMoveLeft() {
		Board tempBoard = copy();
		return tempBoard.moveLeft();
	}

	public int getEmpty () {
		int counter = 0;
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				if (board[i][j] == 0) {
					counter++;
				}
			}
		}
		return counter;
	}

	public void move(int direction) {
		if (direction == 1) {
			moveUp();
		} else if (direction == 2) {
			moveRight();
		} else if (direction == 3) {
			moveDown();
		} else if (direction == 4) {
			moveLeft();
		} else {
			System.out.println("Invalid move order.");
		}
	}

	public boolean tryMove(int direction) {
		if (direction == 1) {
			return tryMoveUp();
		} else if (direction == 2) {
			return tryMoveRight();
		} else if (direction == 3) {
			return tryMoveDown();
		} else if (direction == 4) {
			return tryMoveLeft();
		} else {
			System.out.println("Invalid tryMove order.");
		}
		return false;
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

	public int squareScore() {
		int squareScore = 0;
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				squareScore += board[i][j]*board[i][j];
			}
		}
		return squareScore;
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

	/**
   	* Helper function to make a random integer between the two given value, 
   	* with one more input value as the percentage of occurance for the first number
   	*
   	* @param (int) i1 the first value that want to be chosen
   	* @param (int) i2 the second value that want to be chosen
   	* @param (int) p the percentage for the first item to appear (1-99)
   	* @return (int) a random integer number between the two number
   	*/
  	private static int random(int i1, int i2, int p) {
    	int temp = (int)(Math.random() * 100);
    	if ((p > 0) && (p < 100)) {
    		// value of p is good
    	} else {
    		System.out.println("Error: percentage value must be an integer between 1-99. Default percentage is chosen!");
    		p = 10;
    	}

    	if (temp < p) {
    		return i1;
    	} else {
    		return i2;
    	}
  	}

  	/**
   	* Helper function to make a random integer value in a given range
   	*
   	* @return (int) a random integer number between the given range
   	*/
  	private static int randomWithRange(int min, int max) {
    	int range = Math.abs(max - min) + 1;     
    	return (int)(Math.random() * range) + (min <= max ? min : max);
  	}
}