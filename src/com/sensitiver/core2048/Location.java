package com.sensitiver.core2048;

public class Location {
	// left, up corner : i=0, j=0
	int i;//0~3 vertical
	int j;//0~3 horizontal
	
	public Location(int a, int b){
		this.i = a;
		this.j = b;
	}
	
	/**
	 * generate a random location
	 */
	public Location(){
		this.i = (int)(Math.random() * BasicSetting.vertical);
		this.j = (int)(Math.random() * BasicSetting.horizontal);
	}
	
	public boolean moveLeft(){
		if(j-1>=0){
			j--;
			return true;
		}
		return false;
	}
	
	public boolean moveRight(){
		if(j+1 <= BasicSetting.horizontal-1){
			j++;
			return true;
		}
		return false;
	}
	
	public boolean moveUp(){
		if(i-1>=0){
			i--;
			return true;
		}
		return false;
	}
	
	public boolean moveDown(){
		if(i+1 <= BasicSetting.vertical-1){
			i--;
			return true;
		}
		return false;
	}
	
	public int getLocationHorizontal(){
		return this.i;
	}
	
	public int getLocationVertical(){
		return this.j;
	}
	
	public String toString(){
		return this.i+" "+this.j;
		
	}
}
