/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sensitiver.controller2048;

import com.sensitiver.core2048.Board;
import edu.princeton.stdlib.StdOut;

/**
 *
 * @author Shuwen Zhou
 */
public class UIController implements Controller{
    private Board board;
    
    public UIController(){
        board = new Board();
    }
    public UIController(Board b){
        this.board = b;
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

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
