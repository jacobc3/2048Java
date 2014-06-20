package com.sensitiver.core2048;

public class BasicSetting {

	/**
	 * indicate this board's column
	 */
	final public static int horizontal = 4;
	
	/**
	 * this board's column
	 */
	final public static int vertical = 4;
	
	/**
	 * if <code>maxOnBoard==goal</code>, then user win
	 */
	final public static int goal = 2048;
	
	/**
	 * the start number appear during game
	 */
	final public static int startNum = 2;
	
	/**
	 * top,left corner represented by coordinate 0,0.<br>
	 * i represent row. j represent column.
	 */
	final public static Location startLoc = new Location(0,0);
	
	/**
	 * indicate current game's min number on board.<br>
	 * should be changed during gameplay.
	 */
	public static int minOnBoard = 2;
	
	/**
	 * indicate current game's max number on board.<br>
	 * should be changed during gameplay.
	 */
	public static int maxOnBoard = 2;
}
