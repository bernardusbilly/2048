/* AI.java */

package game;

public class AI {

	private Board board;
    private static final int depth = 1;

    /**
     * Given the board condition and a search depth, return a Move object
     * which determine which move should it choose for the best possible move
     * based on expectiminimax algorithm
     *
     * @param (Board) the current condition of the board
     * @return (Move) a move object where it determine its next move
     */
	public static Move chooseMove (Board board) {
        int maxScore = 0;
        int move = 0;
        
        Board temp1 = board.copy();
        Board temp2 = board.copy();
        Board temp3 = board.copy();
        Board temp4 = board.copy();

        boolean moveUp = false;
        boolean moveRight = false;
        boolean moveDown = false;
        boolean moveLeft = false;

        int score1 = 0;
        int score2 = 0;
        int score3 = 0;
        int score4 = 0;

        if (board.tryMoveUp()) {
            moveUp = true;
            temp1.moveUp();
            score1 = measureScore(temp1, depth);
        }
        if (board.tryMoveRight()) {
            moveRight = true;
            temp2.moveRight();
            score2 = measureScore(temp2, depth);
        }
        if (board.tryMoveDown()) {
            moveDown = true;
            temp3.moveDown();
            score3 = measureScore(temp3, depth);
        }
        if (board.tryMoveLeft()) {
            moveLeft = true;
            temp4.moveLeft();
            score4 = measureScore(temp4, depth);
        }

        if (!moveUp) {
            score1 = -10000;
        }
        if (!moveRight) {
            score2 = -10000;
        }
        if (!moveDown) {
            score3 = -10000;
        }
        if (!moveLeft) {
            score4 = -10000;
        }

        // board.toString();
        // System.out.println(score1 + " " + score2 + " " + score3 + " " + score4);


        // score1 might be 0 but not a legal move!
        if ((score1 >= score2) && (score1 >= score3) && (score1 >= score4)) {
            // System.out.println("Choose move 1");
            return new Move(1);
        } else if ((score2 >= score1) && (score2 >= score3) && (score2 >= score4)) {
            // System.out.println("Choose move 2");
            return new Move(2);
        } else if ((score3 >= score1) && (score3 >= score2) && (score3 >= score4)) {
            // System.out.println("Choose move 3");
            return new Move(3);
        } else {
            // System.out.println("Choose move 4");
            return new Move(4);
        }
    }

    /**
     * Helper function to help measure the average score of a current board through recursive call
     *
     * @return (int) score of the current board
    */
    private static int measureScore(Board board, int depth) {
        Board tempBoard;
        int totalScore = 0;
        int tryMove = 0;
        Move move;

        if (depth == 0) {
            return board.squareScore();
        } 

        // for each possibleMove
        for (int i = 1; i <= 4; i++) {
            // System.out.println(board.tryMove(i));
            if (board.tryMove(i)) {
                tryMove++;
                tempBoard = board.copy();
                tempBoard.move(i);
                totalScore += measureScore(tempBoard, depth-1);
                //tempBoard.squareScore();
            }
        }
        if (tryMove == 0) {
            return 0;
        } else {
            return totalScore/tryMove;
        }
    }
}