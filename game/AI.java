/* AI.java */

package game;

public class AI {

	Board board;

    /**
     * Given the board condition and a search depth, return a Move object
     * which determine which move should it choose for the best possible move
     * based on expectiminimax algorithm
     *
     * @param (Board) the current condition of the board
     * @return (Move) a move object where it determine its next move
     */
	public static Move chooseMove (Board board) {
        return new Move(1);
    }
}