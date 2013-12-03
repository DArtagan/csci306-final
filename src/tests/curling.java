package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import curling.CurlingMatch;
import curling.House;
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
		match.advanceTurn();  //should be the first players turn
		assertEquals(match.getCurrentPlayer(), match.getHomeTeam().getFirst());  // Home team lead player starts
		match.advanceTurn();  // change to the opponents turn
		assertEquals(match.getCurrentPlayer(), match.getAwayTeam().getFirst());  // Away team lead player's first throw
		match.advanceTurn();  //should be home team's first player's turn
		assertEquals(match.getCurrentPlayer(), match.getHomeTeam().getFirst());  // Home team lead player's second throw
	}

	@Test
	public void testAdvanceTurnChangeSet() {
		match.advanceTurn();//starts the game so that player 1's first throw on the home team
		// advance turn three times so it is the last throw of the first set of players
		match.advanceTurn();
		match.advanceTurn();
		match.advanceTurn();
		assertEquals(match.getCurrentPlayer(), match.getAwayTeam().get(0));
		match.advanceTurn();  // change to the opponents turn, the second set of throwers
		assertEquals(match.getCurrentPlayer(), match.getHomeTeam().get(1));//should be home team's second player's turn
		match.advanceTurn();
		assertEquals(match.getCurrentPlayer(), match.getAwayTeam().get(1));//should be away team's second player's turn
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

		assert(stone1.compareTo(stone2) == 0);
		assert(stone3.compareTo(stone4) == 0);
		assert(stone5.compareTo(stone6) == 0);
		assert(stone7.compareTo(stone8) == 0);
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

		assert(stone1.compareTo(stone2) < 0);
		assert(stone3.compareTo(stone4) < 0);
		assert(stone5.compareTo(stone6) < 0);
		assert(stone7.compareTo(stone8) < 0);
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

		assert(stone1.compareTo(stone2) > 0);
		assert(stone3.compareTo(stone4) > 0);
		assert(stone5.compareTo(stone6) > 0);
		assert(stone7.compareTo(stone8) > 0);
	}

	@Test
	public void testScoreHouse() {
		House house = match.getHouse();
		HashMap<Team, Integer> result = new HashMap<Team, Integer>();
		result.put(Team.AWAY, 0);
		result.put(Team.HOME, 0);
		assertEquals(house.calcScore(), result);

		house.addStone(new Stone(Team.HOME, 1, 2));
		house.addStone(new Stone(Team.HOME, 2, 3));
		house.addStone(new Stone(Team.AWAY, 9, 2));
		house.addStone(new Stone(Team.AWAY, 3, 0));
		house.addStone(new Stone(Team.HOME, 5, 1));
		house.addStone(new Stone(Team.HOME, 6, 1));

		result.clear();
		result.put(Team.HOME, 2);
		result.put(Team.AWAY, 0);
		assertEquals(result, house.calcScore());

		result.put(Team.HOME, 2);
		result.put(Team.AWAY, 0);
		house.addStone(new Stone(Team.AWAY, 3, 5));
		assertEquals(house.calcScore(), result);

		result.put(Team.HOME, 0);
		result.put(Team.AWAY, 1);
		house.addStone(new Stone(Team.AWAY, 0, 1));
		assertEquals(house.calcScore(), result);
	}

	@Test
	public void testScoreMatch() {
		HashMap<Team, Integer> scoreMap = new HashMap<Team, Integer>();

		scoreMap.put(Team.HOME, 0);
		scoreMap.put(Team.AWAY, 0);
		assertEquals(scoreMap, match.getScore());

		House house = match.getHouse();
		house.addStone(new Stone(Team.HOME, 1, 2));
		house.addStone(new Stone(Team.HOME, 2, 3));
		house.addStone(new Stone(Team.HOME, 5, 1));
		house.addStone(new Stone(Team.HOME, 6, 1));
		house.addStone(new Stone(Team.AWAY, 9, 2));
		house.addStone(new Stone(Team.AWAY, 3, 0));

		scoreMap.put(Team.HOME, 2);
		scoreMap.put(Team.AWAY, 0);
		assertEquals(scoreMap, match.getScore());

		house.addStone(new Stone(Team.AWAY, 0, 1));
		scoreMap.put(Team.HOME, 0);
		scoreMap.put(Team.AWAY, 1);
		assertEquals(scoreMap, match.getScore());
	}
}
