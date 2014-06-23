package com.sensitiver.core2048;

import java.util.ArrayList;

import edu.princeton.stdlib.StdOut;

public class Board {
	/**
	 * if one location eg.bricks[3][2] is null, it means there is no brick.<br>
	 * 
	 */
	private Brick[][] bricks;

	private boolean debug = false;

	public Board() {
		if (debug) {
			StdOut.println("generating new Board");
		}
		bricks = new Brick[BasicSetting.vertical][BasicSetting.horizontal];
		this.randomNewBrick();
		this.randomNewBrick();
	}
        public String getNumber(int i, int j){
            if(bricks[i][j] != null){
                return ""+bricks[i][j].getNumber();
            }
            return "";
        }
	/**
	 * 
	 * @return 0: Game is not finished.<br>
	 *         1: Game is finished. User win.<br>
	 *         -1: Game is finished. User lose.<br>
	 */
	public int isGameFinished() {
		return BasicSetting.gameFinished;
	}

	/**
	 * check if full, if is stable? if full & stable, return result
	 * 
	 * @return 	-2:No empty space.Not stable. Game continues.<br>
	 * 			-1:Is empty space. Game continues.<br>
	 *         	0:No empty space. Is stable. Game finishes. User lose.<br>         
	 *         	1:No empty space. Is stable. Game finishes. User win.<br>
	 */
	public int checkBoardStatus() {
		int result = 0;
		if(isAvailableLocation()){
			result = -1;
		} else {
			if (isStable()) {
				if(BasicSetting.maxOnBoard >= BasicSetting.goal){
					result = 1;
					setUserWin();
				} else {
					result = 0;
					setUserLose();
				}
			} else {
				result = -2;
			}
		}
		return result;
	}

	/**
	 * check if this board is stable
	 * 
	 * @return true: broad can has next step. <br>
	 *         false: doesn't have next step, may have empty location or
	 *         avaiblae move
	 */
	private boolean isStable() {
		if (!isAvailableLocation()) {
			for (int i = 0; i < BasicSetting.vertical - 1; i++) {
				for (int j = 0; j < BasicSetting.horizontal - 1; j++) {
					if (isEqual(i, j, i + 1, j) || isEqual(i, j, i, j + 1)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Up: <br>
	 * (unless broad is already in up-stable, which means this action won't make
	 * any change to board.)<br>
	 * from top to bottom<br>
	 * slide every brick into most top column it can reach<br>
	 * if there's any same number in vertical direction, add them up<br>
	 * generate a random number between min-max in board(current between
	 * start-max)<br>
	 * 
	 * @return -2:this action does not change the board. Game finishes.<br>
	 *         -1:this action does not change the board. Game continues.<br>
	 *         0:this action is ok. Game not finished. Game continues.<br>
	 *         1:this action is ok. Game is finished. User lose.<br>
	 *         2:this action is ok. Game is finished. User win.<br>
	 */
	public int up() {
		boolean changed = false;
		// MOVE UP
		for (int i = 1; i < BasicSetting.vertical; i++) {
			// ignoreing 1st row, i=0
			for (int j = 0; j < BasicSetting.horizontal; j++) {
				if (!isEmpty(i, j)) {
					for (int k = 0; k < i; k++) {
						if (isEmpty(k, j)) {
							move(i, j, k, j);
							changed = true;
						}// END OF IF
					}// END OF FOR
				}// END OF IF
			}
		}// END OF FOR

		// VERTICAL ADD UP
		for (int i = 0; i < BasicSetting.vertical - 1; i++) {
			for (int j = 0; j < BasicSetting.horizontal; j++) {
				if (!isEmpty(i, j) && !isEmpty(i + 1, j)) {
					if (isEqual(i, j, i + 1, j)) {
						add(i, j, i + 1, j);
						changed = true;
					}// END OF IF
				}// END OF IF
			}
		}// END OF FOR

		// MOVE UP
		for (int i = 1; i < BasicSetting.vertical; i++) {
			// ignoreing 1st row, i=0
			for (int j = 0; j < BasicSetting.horizontal; j++) {
				if (!isEmpty(i, j)) {
					for (int k = 0; k < i; k++) {
						if (isEmpty(k, j)) {
							move(i, j, k, j);
							changed = true;
						}// END OF IF
					}// END OF FOR
				}// END OF IF
			}
		}// END OF FOR

		/**
		 * Return value: <br>
		 * -2:this action does not change the board. Game finishes.<br>
		 * -1:this action does not change the board. Game continues.<br>
		 * 0:this action is ok. Game not finished. Game continues.<br>
		 * 1:this action is ok. Game is finished. User lose.<br>
		 * 2:this action is ok. Game is finished. User win.<br>
		 * */
		int result = 0;
		// GENERATE NEW BRICK
		if (changed) {// not in stable, generate random number
			this.randomNewBrick();
			int status = checkBoardStatus();
			if(status == -2 || status == -1){
				result = 0;
			} else if(status == 1){
				result = 2;
			} else if (status == 0){
				result = 1;
			}
		} else {// in stable, this action didn't make any change to board
			if (isGameFinished() != 0) {
				result = -2;
			} else {
				result = -1;
			}
		}		
		return result;
	}

	/**
	 * Down: <br>
	 * (unless broad is already in up-stable, which means this action won't make
	 * any change to board.)<br>
	 * from bottom to top<br>
	 * slide every brick into most bottom column it can reach<br>
	 * if there's any same number in vertical direction, add them up<br>
	 * generate a random number between min-max in board<br>
	 * @return 
	 */
	public int down() {
		boolean changed = false;
		// MOVE DOWN
		for (int i = BasicSetting.vertical - 1 - 1; i >= 0; i--) {
			// ignoreing most bottom row, BasicSetting.vertical-1
			for (int j = 0; j < BasicSetting.horizontal; j++) {
				if (!isEmpty(i, j)) {
					for (int k = BasicSetting.vertical - 1; k > i; k--) {
						if (isEmpty(k, j)) {
							move(i, j, k, j);
							changed = true;
						}// END OF IF
					}// END OF FOR
				}// END OF IF
			}
		}// END OF FOR

		// VERTICAL ADD DOWN
		for (int i = BasicSetting.vertical - 1; i > 0; i--) {
			for (int j = 0; j < BasicSetting.horizontal; j++) {
				if (!isEmpty(i, j) && !isEmpty(i - 1, j)) {
					if (isEqual(i, j, i - 1, j)) {
						add(i, j, i - 1, j);
						changed = true;
					}// END OF IF
				}// END OF IF
			}
		}// END OF FOR

		// MOVE DOWN
		for (int i = BasicSetting.vertical - 1 - 1; i >= 0; i--) {
			// ignoreing most bottom row, BasicSetting.vertical-1
			for (int j = 0; j < BasicSetting.horizontal; j++) {
				if (!isEmpty(i, j)) {
					for (int k = BasicSetting.vertical - 1; k > i; k--) {
						if (isEmpty(k, j)) {
							move(i, j, k, j);
							changed = true;
						}// END OF IF
					}// END OF FOR
				}// END OF IF
			}
		}// END OF FOR

		/**
		 * Return value: <br>
		 * -2:this action does not change the board. Game finishes.<br>
		 * -1:this action does not change the board. Game continues.<br>
		 * 0:this action is ok. Game not finished. Game continues.<br>
		 * 1:this action is ok. Game is finished. User lose.<br>
		 * 2:this action is ok. Game is finished. User win.<br>
		 * */
		int result = 0;
		// GENERATE NEW BRICK
		if (changed) {// not in stable, generate random number
			this.randomNewBrick();
			int status = checkBoardStatus();
			if(status == -2 || status == -1){
				result = 0;
			} else if(status == 1){
				result = 2;
			} else if (status == 0){
				result = 1;
			}
		} else {// in stable, this action didn't make any change to board
			if (isGameFinished() != 0) {
				result = -2;
			} else {
				result = -1;
			}
		}		
		return result;
	}

	/**
	 * Left: <br>
	 * (unless broad is already in up-stable, which means this action won't make
	 * any change to board.)<br>
	 * from left to right<br>
	 * slide every brick into most left column it can reach<br>
	 * if there's any same number in horizontal direction, add them up to left<br>
	 * generate a random number between min-max in board<br>
	 * @return 
	 */
	public int left() {
		boolean changed = false;
		// MOVE LEFT
		for (int j = 1; j < BasicSetting.horizontal; j++) {
			// ignoreing 1st column, j=0
			for (int i = 0; i < BasicSetting.vertical; i++) {
				if (!isEmpty(i, j)) {
					for (int k = 0; k < j; k++) {
						if (isEmpty(i, k)) {
							move(i, j, i, k);
							changed = true;
						}// END OF IF
					}// END OF FOR
				}// END OF IF
			}
		}// END OF FOR

		// HORIZONTAL ADD LEFT
		for (int i = 0; i < BasicSetting.vertical; i++) {
			for (int j = 0; j < BasicSetting.horizontal - 1; j++) {
				if (!isEmpty(i, j) && !isEmpty(i, j + 1)) {
					if (isEqual(i, j, i, j + 1)) {
						add(i, j, i, j + 1);
						changed = true;
					}// END OF IF
				}// END OF IF
			}
		}// END OF FOR

		// MOVE LEFT
		for (int j = 1; j < BasicSetting.horizontal; j++) {
			// ignoreing 1st column, j=0
			for (int i = 0; i < BasicSetting.vertical; i++) {
				if (!isEmpty(i, j)) {
					for (int k = 0; k < j; k++) {
						if (isEmpty(i, k)) {
							move(i, j, i, k);
							changed = true;
						}// END OF IF
					}// END OF FOR
				}// END OF IF
			}
		}// END OF FOR

		/**
		 * Return value: <br>
		 * -2:this action does not change the board. Game finishes.<br>
		 * -1:this action does not change the board. Game continues.<br>
		 * 0:this action is ok. Game not finished. Game continues.<br>
		 * 1:this action is ok. Game is finished. User lose.<br>
		 * 2:this action is ok. Game is finished. User win.<br>
		 * */
		int result = 0;
		// GENERATE NEW BRICK
		if (changed) {// not in stable, generate random number
			this.randomNewBrick();
			int status = checkBoardStatus();
			if(status == -2 || status == -1){
				result = 0;
			} else if(status == 1){
				result = 2;
			} else if (status == 0){
				result = 1;
			}
		} else {// in stable, this action didn't make any change to board
			if (isGameFinished() != 0) {
				result = -2;
			} else {
				result = -1;
			}
		}		
		return result;
	}

	/**
	 * Right: <br>
	 * (unless broad is already in up-stable, which means this action won't make
	 * any change to board.)<br>
	 * from right to left<br>
	 * slide every brick into most right column it can reach<br>
	 * if there's any same number in horizontal direction, add them up to right<br>
	 * generate a random number between min-max in board<br>
	 * @return 
	 */
	public int right() {
		boolean changed = false;
		// MOVE RIGHT
		for (int j = BasicSetting.horizontal - 1 - 1; j >= 0; j--) {
			// ignoreing 1st column, j=BasicSetting.horizontal-1;
			for (int i = 0; i < BasicSetting.vertical; i++) {
				if (!isEmpty(i, j)) {
					for (int k = BasicSetting.horizontal - 1; k > j; k--) {
						if (isEmpty(i, k)) {
							move(i, j, i, k);
							changed = true;
						}// END OF IF
					}// END OF FOR
				}// END OF IF
			}
		}// END OF FOR

		// HORIZONTAL ADD RIGHT
		for (int i = 0; i < BasicSetting.vertical; i++) {
			for (int j = BasicSetting.horizontal - 1; j > 0; j--) {
				if (!isEmpty(i, j) && !isEmpty(i, j - 1)) {
					if (isEqual(i, j, i, j - 1)) {
						add(i, j, i, j - 1);
						changed = true;
					}// END OF IF
				}// END OF IF
			}
		}// END OF FOR

		// MOVE RIGHT
		for (int j = BasicSetting.horizontal - 1 - 1; j >= 0; j--) {
			// ignoreing 1st column, j=BasicSetting.horizontal-1;
			for (int i = 0; i < BasicSetting.vertical; i++) {
				if (!isEmpty(i, j)) {
					for (int k = BasicSetting.horizontal - 1; k > j; k--) {
						if (isEmpty(i, k)) {
							move(i, j, i, k);
							changed = true;
						}// END OF IF
					}// END OF FOR
				}// END OF IF
			}
		}// END OF FOR

		/**
		 * Return value: <br>
		 * -2:this action does not change the board. Game finishes.<br>
		 * -1:this action does not change the board. Game continues.<br>
		 * 0:this action is ok. Game not finished. Game continues.<br>
		 * 1:this action is ok. Game is finished. User lose.<br>
		 * 2:this action is ok. Game is finished. User win.<br>
		 * */
		int result = 0;
		// GENERATE NEW BRICK
		if (changed) {// not in stable, generate random number
			this.randomNewBrick();
			int status = checkBoardStatus();
			if(status == -2 || status == -1){
				result = 0;
			} else if(status == 1){
				result = 2;
			} else if (status == 0){
				result = 1;
			}
		} else {// in stable, this action didn't make any change to board
			if (isGameFinished() != 0) {
				result = -2;
			} else {
				result = -1;
			}
		}		
		return result;
	}

	private void setUserWin() {
		BasicSetting.gameFinished = 1;
	}

	private void setUserLose() {
		BasicSetting.gameFinished = -1;
	}

	/**
	 * Add up<br>
	 * Keep i0,j0, erase i1,j1
	 * 
	 * @param i0
	 * @param j0
	 * @param i1
	 * @param j1
	 * @return
	 */
	private boolean add(int i0, int j0, int i1, int j1) {
		if (isEmpty(i0, j0) || isEmpty(i1, j1)) {
			return false;
		}
		bricks[i0][j0].add(bricks[i1][j1]);
		bricks[i1][j1] = null;
		if(bricks[i0][j0].getNumber() > BasicSetting.maxOnBoard){
			BasicSetting.maxOnBoard = bricks[i0][j0].getNumber();
			if (debug) StdOut.println("Max number is:"+BasicSetting.maxOnBoard+"\t|add()");
		}
		return true;
	}

	private boolean isEqual(int i0, int j0, int i1, int j1) {
		if (isEmpty(i0, j0) || isEmpty(i1, j1)) {
			return false;
		}

		if (bricks[i0][j0].getNumber() == bricks[i1][j1].getNumber()) {
			return true;
		}
		return false;
	}

	private boolean move(int i0, int j0, int i1, int j1) {
		if (isEmpty(i1, j1) && !isEmpty(i0, j0)) {
			bricks[i1][j1] = bricks[i0][j0];
			bricks[i0][j0] = null;
			return true;
		}
		return false;
	}

	private boolean isEmpty(int i, int j) {
		if (bricks[i][j] == null) {
			return true;
		}
		return false;
	}

	private boolean randomNewBrick() {
		if (debug) {
			StdOut.println("generating new Brick at random Location");
		}
		if (this.isAvailableLocation()) { // there is empty location on board
			Location randomLocation = this.randomLocation();
			Brick newBrick = new Brick(randomLocation);
			this.addBrick(newBrick);
			if (debug) {
				StdOut.println("Location=" + newBrick.getLocation().toString()
						+ "\tnumber=" + newBrick.getNumber());
			}
			return true;
		}
		StdOut.println("NO EMPTY LOCATION ON BORARD");
		return false;
	}

	private boolean maxCompare() {
		if (BasicSetting.maxOnBoard >= BasicSetting.goal) {
			StdOut.println("You WIN! \t\t|maxCompare()");
			return true;
		}
		StdOut.println("You LOSE! \t\t|maxCompare()");
		return false;
	}

	private void addBrick(Brick brick) {
		bricks[brick.getLocationVertical()][brick.getLocationHorizontal()] = brick;
	}

	private boolean isAvailableLocation() {
		for (int i = 0; i < BasicSetting.vertical; i++) {
			for (int j = 0; j < BasicSetting.horizontal; j++) {
				if (bricks[i][j] == null) {
					if (debug) StdOut.println("location " + i + "|" + j + " is empty");
					return true;
				}
			}
		}
		StdOut.println("no empty location \t|isAvailableLocation()");
		return false;
	}

	private Location randomLocation() {
		ArrayList<Location> emptyLocations = this.emptyLocations();
		int random = (int) (Math.random() * emptyLocations.size());
		return emptyLocations.get(random);
	}

	private ArrayList<Location> emptyLocations() {
		ArrayList<Location> emptyLocations = new ArrayList<Location>();

		for (int i = 0; i < BasicSetting.vertical; i++) {
			for (int j = 0; j < BasicSetting.horizontal; j++) {
				if (isEmpty(i, j)) {
					emptyLocations.add(new Location(i, j));
				}
			}
		}
		if (false) {
			StdOut.println("Empty Locations are");
			for (Location loc : emptyLocations) {
				StdOut.println(loc.toString() + "\t");
			}
		}
		return emptyLocations;
	}

	public String toString() {
		String string = "";
		for (int i = 0; i < BasicSetting.vertical; i++) {
			for (int j = 0; j < BasicSetting.horizontal; j++) {
				if (bricks[i][j] != null) {
					string += bricks[i][j].toString() + "\t";
				} else {
					string += "(Null)\t";
				}
			}
			string += "\n";
		}

		return string;
	}
}
