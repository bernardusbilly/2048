/* Play.java */

import game.*;
import java.util.Scanner;

public class Play {

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

		board.generateRandom();

		do {

			command = "null";
			board.generateRandom();
			board.toString();
			
			if (board.isOver()) {
				System.out.println("Game Over");
				try {
					Thread.sleep(5000);	
				}
				catch (Exception e){
					System.err.println(e);
				}
				return;
			}
			
			// our chooseMove by inputing a text from keyboard
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
					System.out.print("Enter a command: ");
					command = in.nextLine();
					command = command.toLowerCase();	
				}
			}

			board.toString();
		} while (true);
	} 
}