package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import curling.CurlingMatch;
import curling.Stone;

public class curling {

	@Before
	public void setUp() {
		CurlingMatch match = new CurlingMatch();
	}

	@Test
	public void testAdvanceTurnChangePlayer() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdvanceTurnChangeSet() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdvanceTurnNewGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdvanceTurnGameOver() {
		fail("Not yet implemented");
	}


	@Test
	public void testFormTeamsNumber() {
		fail("Not yet implemented");
	}


	@Test
	public void testFormTeamsComposition() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendPlayerStoneCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendEffect() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompareEqual() {
		Stone stone1 = new Stone(1, 2);
		Stone stone2 = new Stone(1, 3);
		Stone stone3 = new Stone(0, 3);
		Stone stone4 = new Stone(0, 2);
		Stone stone5 = new Stone(-1, 9);
		Stone stone6 = new Stone(-1, 0);
		Stone stone7 = new Stone(5, 1);
		Stone stone8 = new Stone(5, 1);

		assertEquals(Stone.Compare.EQUAL, stone1.compareTo(stone2));
		assertEquals(Stone.Compare.EQUAL, stone3.compareTo(stone4));
		assertEquals(Stone.Compare.EQUAL, stone5.compareTo(stone6));
		assertEquals(Stone.Compare.EQUAL, stone7.compareTo(stone8));
	}

	@Test
	public void testCompareFarther() {
		Stone stone1 = new Stone(2, 2);
		Stone stone2 = new Stone(1, 3);
		Stone stone3 = new Stone(9, 3);
		Stone stone4 = new Stone(0, 2);
		Stone stone5 = new Stone(-2, 9);
		Stone stone6 = new Stone(-1, 0);
		Stone stone7 = new Stone(6, 1);
		Stone stone8 = new Stone(5, 1);

		assertEquals(Stone.Compare.FARTHER, stone1.compareTo(stone2));
		assertEquals(Stone.Compare.FARTHER, stone3.compareTo(stone4));
		assertEquals(Stone.Compare.FARTHER, stone5.compareTo(stone6));
		assertEquals(Stone.Compare.FARTHER, stone7.compareTo(stone8));
	}

	@Test
	public void testCompareCloser() {
		Stone stone1 = new Stone(1, 2);
		Stone stone2 = new Stone(2, 3);
		Stone stone3 = new Stone(0, 3);
		Stone stone4 = new Stone(9, 2);
		Stone stone5 = new Stone(-1, 9);
		Stone stone6 = new Stone(-2, 0);
		Stone stone7 = new Stone(5, 1);
		Stone stone8 = new Stone(6, 1);

		assertEquals(Stone.Compare.CLOSER, stone1.compareTo(stone2));
		assertEquals(Stone.Compare.CLOSER, stone3.compareTo(stone4));
		assertEquals(Stone.Compare.CLOSER, stone5.compareTo(stone6));
		assertEquals(Stone.Compare.CLOSER, stone7.compareTo(stone8));
	}

	@Test
	public void testCalcScorePoints() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalcScoreTeamScore() {
		fail("Not yet implemented");
	}
}
