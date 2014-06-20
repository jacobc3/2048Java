package JTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sensitiver.core2048.BasicSetting;

import edu.princeton.stdlib.StdOut;

public class Mathtest {

	@Test
	public void test() {
		int a = (int) (Math.log(2)/Math.log(2));
		StdOut.println("max index is "+a);
		int randomIndex = (int)(Math.random()*a)+1; 
		// actually, random=1~0, final 1.0~4.9999, int erase everything behind dot
		StdOut.println("random index is "+randomIndex);
		
		int number = (int) Math.pow(2, randomIndex);
		StdOut.println("2^randomIndex is "+number);
	}

}
