package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import curling.CurlingMatch;
import curling.Player;
import curling.Role;
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
		match.advanceTurn();//should be the first players turn
		assertEquals(match.getCurrentPlayer(), match.getHomeTeam().getFirst());// Home team lead player starts
		match.advanceTurn();// change to the opponents turn
		assertEquals(match.getCurrentPlayer(), match.getAwayTeam().getFirst());// Away team lead player's first throw
		match.advanceTurn();//should be home team's first player's turn
		assertEquals(match.getCurrentPlayer(), match.getHomeTeam().getFirst());// Home team lead player's second throw
	}

	@Test
	public void testAdvanceTurnChangeSet() {
		// advance turn four times so the second set of players come in
		match.advanceTurn();
		match.advanceTurn();
		match.advanceTurn();
		match.advanceTurn();
		assertEquals(match.getCurrentPlayer(), match.getHomeTeam().get(match.getTurn()));// Home team lead player starts
		match.advanceTurn();// change to the opponents turn
		assertEquals(match.getCurrentPlayer(), match.getAwayTeam().get(match.getTurn()));// Away team lead player's first throw
		match.advanceTurn();//should be home team's first player's turn
		assertEquals(match.getCurrentPlayer(), match.getHomeTeam().get(match.getTurn()));// Home team lead player's second throw
	}

	@Test
	public void testAdvanceTurnNewGame() {
		// Test that advancing the turn when no one is playing will start a new game
		// The game turn should initialize to zero
		// do some stuff in the game
		match.setTurn(15);// set the game to the last turn
		match.advanceTurn();
		assertEquals(0, match.getTurn());
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
		// Home team:
		boolean hasSkip = false;
		boolean hasLead = false;
		boolean hasSecond = false;
		boolean hasThird = false;
		for (Player player : homeTeam) {
			switch (player.getRole()) {
				case SKIP: hasSkip = true; return;
				case LEAD: hasLead = true; return;
				case SECOND: hasSecond = true; return;
				case THIRD: hasThird = true; return;
				default: assertTrue(false); return;
			}
		}
		assertTrue(hasSkip);
		assertTrue(hasLead);
		assertTrue(hasSecond);
		assertTrue(hasThird);

		// Away team:
		hasSkip = false;
		hasLead = false;
		hasSecond = false;
		hasThird = false;
		for (Player player : awayTeam) {
			switch (player.getRole()) {
				case SKIP: hasSkip = true; return;
				case LEAD: hasLead = true; return;
				case SECOND: hasSecond = true; return;
				case THIRD: hasThird = true; return;
				default: assertTrue(false); return;
			}
		}
		assertTrue(hasSkip);
		assertTrue(hasLead);
		assertTrue(hasSecond);
		assertTrue(hasThird);
	}

	@Test
	public void testTeamMembersOnCorrectTeam() {
		// Test that each member of each team has the correct team member variable.
		// Home team:
		for (Player player : homeTeam) {
			switch (player.getTeam()) {
				case HOME: return;
				default: assertTrue(false);
			}
		}

		// Away team:
		for (Player player : awayTeam) {
			switch (player.getTeam()) {
				case AWAY: return;
				default: assertTrue(false);
			}
		}
	}

	@Test
	public void testSendPlayerStoneCount() {
		// Test that the number of stones that the player has sent has
		// been decremented. Each player should only be able to send two
		// stones.
		Player player = new Player(Team.HOME, Role.SKIP);
		player.sendStone();
		assertEquals(1, player.getStones().size());
		player.sendStone();
		assertEquals(0, player.getStones().size());


		// Test that every time any player sends a stone, the house
		// gains one. CurlingMatch.advanceTurn() should have a single
		// player send a single stone.
		assertEquals(0, match.getHouse().getStones().size());
		match.advanceTurn();
		assertEquals(1, match.getHouse().getStones().size());
		match.advanceTurn();
		assertEquals(2, match.getHouse().getStones().size());
		match.advanceTurn();
		assertEquals(3, match.getHouse().getStones().size());
		match.advanceTurn();
		match.advanceTurn();
		assertEquals(5, match.getHouse().getStones().size());
		// ...
	}

	@Test(expected=RuntimeException.class)
	public void testSendPlayerOutOfStones() {
		// Test that a runtime exception is thrown when a sendStone() is
		// called, but a player is out of stones.
		Player player = new Player(Team.HOME, Role.SKIP);
		player.sendStone();
		player.sendStone();
		player.sendStone();
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
