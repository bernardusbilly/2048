/* PlayAI.java */

import game.*;
import java.util.Scanner;

public class PlayAI {

	public static void main (String[] args) {

		// initialize with a chosen size of the board
		int size = 4;
		if (args.length > 0) {
			int value = Integer.parseInt(args[0]);
			if (value > 0) {
				size = value;	
			}
		}

		Board board = new Board();
		Board temp1, temp2, temp3, temp4;
		boolean moveUp = false;
		boolean moveRight = false;
		boolean moveDown = false;
		boolean moveLeft = false;
		
		Scanner in = new Scanner(System.in);
		String command = new String ("null");
		int x, y, val;

		Move move;

		// first initialization
		x = randomWithRange(0, size-1);
		y = randomWithRange(0, size-1);
		board.setNumber(2, x, y);

		do {

			command = "null";

			// find an element for a number to be placed
			do {
				x = randomWithRange(0, size-1);
				y = randomWithRange(0, size-1);
			} while(board.element(x,y) != 0);

			System.out.println("Input 2 or 4.");
			board.setNumber(random(2, 4, 90), x, y);

			
			if (board.isOver()) {

				System.out.println("Game Over");

				// find score
				if (board.bestScore() == 2048) {
					goal2048++;
				}
				else if (board.bestScore() == 1024) {
					goal1024++;
				}
				else if (board.bestScore() == 512) {
					goal512++;
				}

				endGame++;
				System.out.println("\nResult: ");
				System.out.println("Total game: " + endGame);
				System.out.println("Statistic : ");
				System.out.println("- 2048: " + goal2048);
				System.out.println("- 1024: " + goal1024);
				System.out.println("- 512 : " + goal512);

				// return;

				// repeat the game

				board = new Board();
				x = randomWithRange(0, size-1);
				y = randomWithRange(0, size-1);
				board.setNumber(2, x, y);
				
				do {
					x = randomWithRange(0, size-1);
					y = randomWithRange(0, size-1);
				} while(board.element(x,y) != 0);
				board.setNumber(random(2, 4, 90), x, y);
			}


			// test the four possibilities
			temp1 = board.copy();
			temp2 = board.copy();
			temp3 = board.copy();
			temp4 = board.copy();

			moveUp    = temp1.moveUp(); 
			moveRight = temp2.moveRight();
			moveDown  = temp3.moveDown();
			moveLeft  = temp4.moveLeft();

			move = AI.chooseMove(board);
			command = move.toString();

			while (true) {
				if ((command.equals("right")) && moveRight) {
					board.moveRight();
					break;
				}
				else if ((command.equals("up")) && moveUp) {
					board.moveUp();
					break;
				}
				else if ((command.equals("down")) && moveDown) {
					board.moveDown();
					break;
				}
				else if ((command.equals("left")) && moveLeft) {
					board.moveLeft();
					break;
				}
				else {
					move = AI.chooseMove(board);
					System.out.print("Error: Cannot choose that move.");
					command = move.toString();
				}
			}
			
		} while (true);
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