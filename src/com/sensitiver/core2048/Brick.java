package com.sensitiver.core2048;

public class Brick {

	private int number;
	private Location location;
	
	
	public Brick(int num, Location loc){
		this.number = num;
		this.location = loc;
	}
	
	public int getNumber(){
		return this.number;
	}
	
	public Location getLocation(){
		return this.location;
	}
	
	public boolean moveUp(){
		return this.location.moveUp();
	}
	public boolean moveDown(){
		return this.location.moveDown();
	}
	public boolean moveLeft(){
		return this.location.moveLeft();
	}
	public boolean moveRight(){
		return this.location.moveRight();
	}
	
}
