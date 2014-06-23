package com.sensitiver.controller2048;

import com.sensitiver.core2048.Board;

import edu.princeton.stdlib.StdIn;
import edu.princeton.stdlib.StdOut;

public class CommandLineController implements Controller {
	private Board board;

	public CommandLineController(Board b) {
		this.board = b;
	}
	
	public void run(){
		String input;
		StdOut.println("Please use key WASD to play, Q to quit");
		while(board.isGameFinished()== 0 &&(input = StdIn.readLine() )!= null 
				&& !input.matches(".*Q.*")){
			if(input.matches(".*(?i)W.*")){
				up();
			} else if(input.matches(".*(?i)S.*")){
				down();
			} else if(input.matches(".*(?i)A.*")){
				left();
			} else if(input.matches(".*(?i)D.*")){
				right();
			} else {
				StdOut.println("Wrong Key");
			}
		}
		if(board.isGameFinished() == 1){
			StdOut.println("You win");
		} else if (board.isGameFinished() == -1){
			StdOut.println("You lose");
		}
	}

	/**
	 * @return -1:Lose<br>
	 *         0:continu<br>
	 *         1:Win<br>
	 */
	public int up() {
		StdOut.println("UP");
		return action(board.up());
	}

	/**
	 * @return -1:Lose<br>
	 *         0:continu<br>
	 *         1:Win<br>
	 */
	public int left() {
		StdOut.println("LEFT");
		return action(board.left());
	}

	/**
	 * @return -1:Lose<br>
	 *         0:continu<br>
	 *         1:Win<br>
	 */
	public int right() {
		StdOut.println("RIGHT");
		return action(board.right());
	}

	/**
	 * @return -1:Lose<br>
	 *         0:continu<br>
	 *         1:Win<br>
	 */
	public int down() {		
		StdOut.println("DOWN");
		return action(board.down());
	}

	private int action(int res){
		/**
		 * -2:this action does not change the board. Game finishes. <br>
		 * -1:this action does not change the board. Game continues. <br>
		 * 0:this action is ok. Game not finished. Game continues. <br>
		 * 1:this action is ok. Game is finished. User lose. <br>
		 * 2:this action is ok. Game is finished. User win.<br>
		 */
		StdOut.println(board.toString());
		if (res == 1) return -1;
		if (res == 2) return 1;
		return 0;
	}
	public boolean newGame() {
		Board b = new Board();
		this.board = b;
		return true;
	}

	public void exit() {

	}

	public Board getBoard() {
		return board;
	}

}
