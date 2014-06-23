package com.sensitiver.core2048;

public class BasicSetting {

	/**
	 * Indicate this board's column
	 */
	final public static int horizontal = 4;
	
	/**
	 * This board's column
	 */
	final public static int vertical = 4;
	
	/**
	 * If <code>maxOnBoard==goal</code>, then user win
	 */
	final public static int goal = 128;
	
	/**
	 * The start number appear during game
	 */
	final public static int startNum = 2;
	
	/**
	 * Top,left corner represented by coordinate 0,0.<br>
	 * i represent row. j represent column.
	 */
	final public static Location startLoc = new Location(0,0);
	
	/**
	 * Indicate current game's min number on board.<br>
	 * Should be changed during gameplay.
	 */
	public static int minOnBoard = 2;
	
	/**
	 * Indicate current game's max number on board.<br>
	 * Should be changed during gameplay.
	 */
	public static int maxOnBoard = 2;
	
	/**
	 * 0: Game is not finished.<br>
	 * 1: Game is finished. User win.<br>
	 * -1: Game is finished. User lose.<br>
	 */
	public static int gameFinished = 0;
}
