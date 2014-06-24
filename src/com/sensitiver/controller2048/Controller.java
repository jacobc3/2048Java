package com.sensitiver.controller2048;

import com.sensitiver.core2048.Board;

public interface Controller {
	public int up();
	public int left();
	public int right();
	public int down();
	public boolean newGame();
	public void exit();
	public Board getBoard();
	public void run();
	public int getNumber(int i, int j);
}
