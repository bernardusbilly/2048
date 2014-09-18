/* Test.java */

import game.*;

public class Test {
	public static void main(String[] args) {
		Board board = new Board();
		boolean move;

		board.setNumber(2, 0, 0);
		board.setNumber(2, 1, 0);
		board.setNumber(2, 2, 0);
		board.setNumber(2, 3, 0);

		board.setNumber(2, 1, 1);
		board.setNumber(2, 2, 1);
		board.setNumber(2, 3, 1);

		board.setNumber(2, 0, 2);
		board.setNumber(2, 3, 2);

		board.setNumber(4, 0, 3);
		board.setNumber(4, 3, 3);
		
		board.toString();

		System.out.println("\nTry: moveRight() method");
		move = board.moveRight();
		board.toString();

		System.out.println("\nTry: moveRight() method");
		move = board.moveRight();
		board.toString();

		System.out.println("\nTry: moveLeft() method");
		move = board.moveLeft();
		board.toString();

		System.out.println("\nTry: moveRight() method");
		move = board.moveRight();
		board.toString();

		System.out.println("\nTry: moveUp() method");
		move = board.moveUp();
		board.toString();
	
		System.out.println("\nTry: moveUp() method");
		move = board.moveUp();
		board.toString();

		System.out.println("\nTry: moveDown() method");
		move = board.moveDown();
		board.toString();

		Board board2 = new Board();
		board2.setNumber(2, 3, 0);
		board2.setNumber(2, 0, 3);
		board.toString();
		System.out.println("\nTry: moveRight() method");
		board2.moveRight();
		board2.toString();
	}	
}
