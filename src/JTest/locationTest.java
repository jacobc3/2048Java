package JTest;

import static org.junit.Assert.*;

import org.junit.Test;
import com.sensitiver.core2048.*;

import edu.princeton.stdlib.StdOut;


public class locationTest {

	@Test
	public void test() {
		Location loc = new Location();
		StdOut.print(loc);
	}

}
