package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import curling.CurlingMatch;
import curling.Player;
import curling.Stone;
import curling.Team;

public class curling {
	CurlingMatch match;
	LinkedList<Player> homeTeam, awayTeam;

	@Before
	public void setUp() {
		match = new CurlingMatch();

		match.formTeams();
		homeTeam = match.getHomeTeam();
		awayTeam = match.getAwayTeam();
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
		// Test that each team has 4 players.
		assertEquals(4, homeTeam.size());
		assertEquals(4, awayTeam.size());
	}


	@Test
	public void testFormTeamsComposition() {
		// Test that each team has one player of each type.

		// Test that each member of each team has the correct team member variable.
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
		Stone stone1 = new Stone(Team.HOME, 1, 2);
		Stone stone2 = new Stone(Team.HOME, 1, 3);
		Stone stone3 = new Stone(Team.AWAY, 0, 3);
		Stone stone4 = new Stone(Team.AWAY, 0, 2);
		Stone stone5 = new Stone(Team.HOME, -1, 9);
		Stone stone6 = new Stone(Team.AWAY, -1, 0);
		Stone stone7 = new Stone(Team.HOME, 5, 1);
		Stone stone8 = new Stone(Team.HOME, 5, 1);

		assertEquals(Stone.Compare.EQUAL, stone1.compareTo(stone2));
		assertEquals(Stone.Compare.EQUAL, stone3.compareTo(stone4));
		assertEquals(Stone.Compare.EQUAL, stone5.compareTo(stone6));
		assertEquals(Stone.Compare.EQUAL, stone7.compareTo(stone8));
	}

	@Test
	public void testCompareFarther() {
		Stone stone1 = new Stone(Team.HOME, 2, 2);
		Stone stone2 = new Stone(Team.HOME, 1, 3);
		Stone stone3 = new Stone(Team.AWAY, 9, 3);
		Stone stone4 = new Stone(Team.AWAY, 0, 2);
		Stone stone5 = new Stone(Team.HOME, -2, 9);
		Stone stone6 = new Stone(Team.AWAY, -1, 0);
		Stone stone7 = new Stone(Team.HOME, 6, 1);
		Stone stone8 = new Stone(Team.HOME, 5, 1);

		assertEquals(Stone.Compare.FARTHER, stone1.compareTo(stone2));
		assertEquals(Stone.Compare.FARTHER, stone3.compareTo(stone4));
		assertEquals(Stone.Compare.FARTHER, stone5.compareTo(stone6));
		assertEquals(Stone.Compare.FARTHER, stone7.compareTo(stone8));
	}

	@Test
	public void testCompareCloser() {
		Stone stone1 = new Stone(Team.HOME, 1, 2);
		Stone stone2 = new Stone(Team.HOME, 2, 3);
		Stone stone3 = new Stone(Team.AWAY, 0, 3);
		Stone stone4 = new Stone(Team.AWAY, 9, 2);
		Stone stone5 = new Stone(Team.HOME, -1, 9);
		Stone stone6 = new Stone(Team.AWAY, -2, 0);
		Stone stone7 = new Stone(Team.HOME, 5, 1);
		Stone stone8 = new Stone(Team.HOME, 6, 1);

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
