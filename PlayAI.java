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
		Scanner in = new Scanner(System.in);
		String command = new String ("null");
		int x, y, val;

		// for statistic result purposes
		Move move;
		int goal512 = 0;
		int goal1024 = 0;
		int goal2048 = 0;
		int goal4096 = 0;
		int endGame = 0;

		board.generateRandom();

		do {

			command = "null";

			board.generateRandom();
			
			if (board.isOver()) {

				System.out.println("Game Over");
				board.toString();

				// find score
				if (board.bestScore() == 4096) {
					goal4096++;
				}
				else if (board.bestScore() == 2048) {
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
				System.out.println("- 4096: " + goal4096);
				System.out.println("- 2048: " + goal2048);
				System.out.println("- 1024: " + goal1024);
				System.out.println("- 512 : " + goal512);

				//return;

				// repeat the game
				board = new Board();
				board.generateRandom();
			}

			move = AI.chooseMove(board);
			command = move.toString();
			//board.toString();

			while (true) {
				if ((command.equals("up")) && board.tryMoveUp()) {
					board.moveUp();
					break;
				} else if ((command.equals("right")) && board.tryMoveRight()) {
					board.moveRight();
					break;
				} else if ((command.equals("down")) && board.tryMoveDown()) {
					board.moveDown();
					break;
				} else if ((command.equals("left")) && board.tryMoveLeft()) {
					board.moveLeft();
					break;
				} else {
					move = AI.chooseMove(board);
					System.out.print("Error: Cannot choose that move.");
					command = move.toString();
				}
			}
			
		} while (true);
	} 
}