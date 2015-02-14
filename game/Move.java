/* Move.java */

/**
 * Move object for the return object from the AI class.
 */

package game;

public class Move {

	// default move is moveUp if the constructor has an error
	private int move = 1;
	private int score = 0;
	
	/**
	 * Constructor for the Move object
	 * 1 - moveUp
	 * 2 - moveRight
	 * 3 - moveDown
	 * 4 - moveLeft
	 * 
	 * @param (int) move according the list above
	 */
	public Move (int move) {
		if ((move > 0) && (move < 5)) {
			this.move = move;
		} else {
			System.out.println("Error: Input value must between 1-4. Default move is chosen!");
		}
	}

	/**
	 * Constructor for the Move object with score associated with the move
	 * 
	 * @param (int) move - see above
	 * @param (int) score - score for this particular move
	 */
	public Move (int move, int score) {
		if ((move > 0) && (move < 5)) {
			this.move = move;
		} else {
			System.out.println("Error: Input value must between 1-4. Default move is chosen!");
		}
		this.score = score;
	}

	/**
	 * String representation of the move object which is stated in the class constructor
	 */
	public String toString() {
		String s;
		switch(move) {
			case 1: s = "up"; break;
			case 2: s = "right"; break;
			case 3: s = "down"; break;
			case 4: s = "left"; break;
			default: s = "nothing"; break;
		}
		return s;
	}

	public int getScore() {
		return score;
	}
}