/* Move.java */

/**
 * Move object for the return object from the AI class.
 */

package game;

public class Move {

	// default move is moveUp if the constructor has an error
	private int move = 1;
	
	/**
	 * Constructor for the Move object
	 * 1 - moveUp
	 * 2 - moveRight
	 * 3 - moveDown
	 * 4 - moveLeft
	 */
	public Move (int move) {
		if ((move > 0) && (move < 5)) {
			this.move = move;
		} else {
			System.out.println("Error: Input value must between 1-4. Default move is chosen!");
		}
	}

	/**
	 * String representation of the move object which is stated in the class constructor
	 */
	public String toString() {
		String s;
		switch(move) {
			case 1: s = "up";
			case 2: s = "right";
			case 3: s = "down";
			case 4: s = "left";
			default: s = "nothing";
		}
		return s;
	}
}