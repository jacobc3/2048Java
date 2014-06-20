package com.sensitiver.core2048;

import java.util.ArrayList;

import edu.princeton.stdlib.StdOut;

public class Board {
	/**
	 * if one location eg.bricks[3][2] is null, it means there is no brick.<br>
	 * 
	 */
	private Brick[][] bricks;

	private boolean debug = true;

	public Board() {
		if (debug) {
			StdOut.println("generating new Board");
		}
		bricks = new Brick[BasicSetting.vertical][BasicSetting.horizontal];
		this.randomNewBrick();
		this.randomNewBrick();
	}

	/**
	 * Up: <br>
	 * (unless broad is already in up-stable, which means this action won't make
	 * any change to board.)<br>
	 * from top to bottom<br>
	 * slide every brick into most top column it can reach<br>
	 * if there's any same number in vertical direction, add them up<br>
	 * generate a random number between min-max in board<br>
	 */
	public void up() {
		boolean changed = false;
		//MOVE UP
		for (int i = 1; i < BasicSetting.vertical; i++) {
			// ignoreing 1st row, i=0
			for (int j = 0; j < BasicSetting.horizontal; j++) {
				if (!isEmpty(i, j)) {
					for (int k = 0; k < i; k++) {
						if (isEmpty(k, j)) {
							move(i, j, k, j);
							changed = true;
						}//END OF IF
					}// END OF FOR
				}//END OF IF
			}
		}// END OF FOR
		
		// VERTICAL ADD UP
		for (int i = 0; i < BasicSetting.vertical-1; i++) {
			for (int j = 0; j < BasicSetting.horizontal; j++) {
				if (!isEmpty(i, j) && !isEmpty(i+1,j)) {
						if (isEqual(i,j,i+1,j)) {
							add(i,j,i+1,j);
							changed = true;
						}//END OF IF
				}//END OF IF
			}
		}// END OF FOR
		
		//GENERATE NEW BRICK
		if (changed) {// not in stable, generate random number
			this.randomNewBrick();
		} else {// in stable			
		}
	}
	
	/**
	 * Add up<br>Keep i0,j0, erase i1,j1
	 * @param i0
	 * @param j0
	 * @param i1
	 * @param j1
	 * @return
	 */
	private boolean add(int i0,int j0, int i1, int j1){ 
		if(isEmpty(i0,j0) || isEmpty(i1,j1)){
			return false;
		}
		bricks[i0][j0].add(bricks[i1][j1]);	
		bricks[i1][j1] = null;
		return true;
	}
	
	private boolean isEqual(int i0,int j0, int i1, int j1){
		if(isEmpty(i0,j0) || isEmpty(i1,j1)){
			return false;
		}
		
		if(bricks[i0][j0].getNumber() == bricks[i1][j1].getNumber()){
			return true;
		}
		return false;
	}

	public boolean move(int i0, int j0, int i1, int j1) {
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
		return false;
	}

	private void addBrick(Brick brick) {
		bricks[brick.getLocationVertical()][brick.getLocationHorizontal()] = brick;
	}

	private boolean isAvailableLocation() {
		for (int i = 0; i < BasicSetting.vertical; i++) {
			for (int j = 0; j < BasicSetting.horizontal; j++) {
				if (bricks[i][j] == null) {
					return true;
				}
			}
		}
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
