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
	public static Move chooseMove (Board board, int depth=1) {
        int maxScore = 0;
        
        Board temp1 = board.copy()
        Board temp2 = board.copy()
        Board temp3 = board.copy()
        Board temp4 = board.copy()

        boolean moveUp = temp1.moveUp()
        boolean moveRight = temp2.moveRight()
        boolean moveDown = temp3.moveDown()
        boolean moveLeft = temp4.moveLeft()

        int score1 = 0;
        int score2 = 0;
        int score3 = 0;
        int score4 = 0;

        if (moveUp) {
            score1 = temp1.squareScore()
        } else if (moveRight) {
            score2 = temp2.squareScore()
        } else if (moveDown) {
            score3 = temp3.squareScore()
        } else if (moveLeft) {
            score4 = temp4.squareScore()
        }

        if ((score1 >= score2) && (score1 >= score3) && (score1 >= score4)) {
            return new Move(1)
        } else if ((score2 >= score1) && (score2 >= score3) && (score2 >= score4)) {
            return new Move(2)
        } else if ((score3 >= score1) && (score3 >= score2) && (score3 >= score4)) {
            return new Move(3)
        } else if ((score4 >= score1) && (score4 >= score2) && (score4 >= score3)) {
            return new Move(4)
        }
    }
}