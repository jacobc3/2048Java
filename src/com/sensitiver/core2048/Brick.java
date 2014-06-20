package com.sensitiver.core2048;

public class Brick {

	private int number;
	private Location location;
	
	public Brick(int num, Location loc){
		this.number = num;
		this.location = loc;
	}
	
	public Brick(Location loc){
		int indexRange = (int) (Math.log(BasicSetting.maxOnBoard)/Math.log(BasicSetting.startNum));
		int randomIndex = (int)(Math.random()*indexRange)+1;
		this.number = (int) Math.pow(BasicSetting.startNum, randomIndex);
		//TODO should be empty location
		this.location = loc;
	}
	
	
	
	public int getNumber(){
		return this.number;
	}
	
	public boolean isEqual(Brick b){
		if(b.getNumber() == this.getNumber()){
			return true;
		}
		return false;
	}
	
	public boolean add(Brick b){
		if(b!=null){
			this.number = b.getNumber()+this.getNumber();
			return true;
		} 
		return false;
	}
	
	public Location getLocation(){
		return this.location;
	}
	
	public int getLocationVertical(){
		return this.getLocation().getLocationVertical();
	}
	
	public int getLocationHorizontal(){
		return this.getLocation().getLocationHorizontal();
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
	
	public String toString(){
		return this.getLocationVertical()+","+this.getLocationHorizontal()+"|"+this.getNumber();
	}
}
