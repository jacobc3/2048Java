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
		if (bricks[1][1] == null) {
			StdOut.println("is null");
		}
		bricks[1][1] = new Brick(new Location(1, 1));
		if (bricks[1][1] != null) {
			StdOut.println("is not null");
		}
		bricks[1][1] = null;
		if (bricks[1][1] == null) {
			StdOut.println("is null");
		}

	}

	public void randomLocationTest() {
		// ArrayList<Location> emptyLocations = this.emptyLocations();
		int random = (int) (Math.random() * 2.0);
		StdOut.println(random);
	}

	public void newBoardTest() {
		Board b = new Board();
		StdOut.println(b.toString());
		b.up();
		StdOut.println("UP\n" + b.toString());
		b.down();
		StdOut.println("DOWN\n" + b.toString());
		b.up();
		StdOut.println("UP\n" + b.toString());
		b.down();
		StdOut.println("DOWN\n" + b.toString());
		b.up();
		StdOut.println("UP\n" + b.toString());
		b.down();
		StdOut.println("DOWN\n" + b.toString());
		b.left();
		StdOut.println("UP\n" + b.toString());
		b.down();
		StdOut.println("DOWN\n" + b.toString());
		b.left();
		StdOut.println("UP\n" + b.toString());
		b.down();
		StdOut.println("DOWN\n" + b.toString());
		b.left();
		StdOut.println("LEFT\n" + b.toString());
		b.up();
		StdOut.println("UP\n" + b.toString());
		b.down();
		StdOut.println("DOWN\n" + b.toString());
		b.right();
		StdOut.println("LEFT\n" + b.toString());
	}
	@Test
	public void randomActionTest() {
		Board b = new Board();
		StdOut.println(b.toString());

		b.up();
		StdOut.println("UP\n" + b.toString());
		int result = -3;
		for (int i = 0; i < 60 && result !=1 && result != 2; i++) {
			StdOut.println("Action " + i + "\t\t|randomActionTest()");
			int random = (int) (Math.random() * 4.0);			
			if (random == 0) {
				result = b.up();
				StdOut.println("UP\n" + b.toString());
			} else if (random == 1) {
				result = b.down();
				StdOut.println("DOWN\n" + b.toString());
			} else if (random == 2) {
				result = b.left();
				StdOut.println("LEFT\n" + b.toString());
			} else if (random == 3) {
				result = b.right();
				StdOut.println("RIGHT\n" + b.toString());
			}
		}
		if(result == 2){
			StdOut.println("RESULT: YOU WIN!\t Max Number:"+BasicSetting.maxOnBoard);
		} else if(result == 1){
			StdOut.println("RESULT: YOU LOSE!\t Max Number:"+BasicSetting.maxOnBoard);
			
		}
	}

	
	public void statusTest() {
		Board b = new Board();
		StdOut.println(b.toString());

		for(int i = 1; i < 30 ; i++){
			b.up();
			StdOut.println("UP\n" + b.toString());
		}
	}

}
