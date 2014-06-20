package JTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.sensitiver.core2048.BasicSetting;
import com.sensitiver.core2048.Board;
import com.sensitiver.core2048.Brick;
import com.sensitiver.core2048.Location;

import edu.princeton.stdlib.StdOut;

public class BroadTest {

	public void test() {
		Brick[][] bricks = new Brick[BasicSetting.vertical][BasicSetting.horizontal];
		if(bricks[1][1] == null){
			StdOut.println("is null");
		}
		bricks[1][1] = new Brick(new Location(1,1));
		if(bricks[1][1] != null){
			StdOut.println("is not null");
		}
		bricks[1][1] = null;
		if(bricks[1][1] == null){
			StdOut.println("is null");
		}
		
		
	}
	
	public void randomLocationTest(){
		//ArrayList<Location> emptyLocations = this.emptyLocations();
		int random = (int) (Math.random()*2.0);
		StdOut.println(random);
	}
	
	@Test
	public void newBoardTest(){
		Board b = new Board();
		StdOut.println(b.toString());
		b.up();
		StdOut.println(b.toString());
	}
}
