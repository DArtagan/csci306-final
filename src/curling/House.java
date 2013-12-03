package curling;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JPanel;

public class House extends JPanel implements MouseListener {
	private ArrayList<Stone> stones;
	private HouseLayout layout;
	protected PropertyChangeSupport propertyChangeSupport;


	public House() {
		// The house starts with 0 stones, and stones are added as players
		// send them in.
		stones = new ArrayList<Stone>();

		// GUI related
		propertyChangeSupport = new PropertyChangeSupport(this);
		addMouseListener(this);
	}

	public void addStone(Stone stone) {
		stones.add(stone);
	}

	public HashMap<Team, Integer> calcScore() {
		HashMap<Team, Integer> result = new HashMap<Team, Integer>();

		result.put(Team.HOME, 0);
		result.put(Team.AWAY, 0);

		if (stones.size() > 0) {
			ArrayList<Stone> sorted_stones = new ArrayList<Stone>(stones);
			Collections.sort(sorted_stones);
			Stone baseStone = sorted_stones.get(0);

			Team currentTeam = null;
			for (Stone stone : sorted_stones) {
				currentTeam = stone.getTeam();
				if (baseStone.compareTo(stone) == 0 && baseStone.getTeam() != currentTeam) {
					result.put(baseStone.getTeam(), 0);
					break;
				} else if (currentTeam != baseStone.getTeam()) {
					break;
				} else {
					result.put(currentTeam, result.get(currentTeam) + 1);
				}
			}
		}
		return result;
	}

	public ArrayList<Stone> getStones() {
		return stones;
	}

	public String toString() {
		String output = "";
		for (Stone stone : stones) {
			output += stone + ", ";
		}
		return output;
	}

	public void reset() {
		stones.clear();
	}

	public void removeLastStone() {
		if (stones.size() > 0) {
			stones.remove(stones.size() - 1);
		}
	}

	public void paintComponent(Graphics g){
		HouseLayout layout = new HouseLayout();
		layout.draw(g);
		for (Stone stone : stones) {
			stone.draw(g);
		}
	}

	// Board mouse listener
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
		if (CurlingMatch.intention != null) {
			Stone stone = new Stone(CurlingMatch.currentPlayer.getTeam());
			stone.setPurpose(CurlingMatch.intention);
			stone.setPosition(e.getX(), e.getY());
			addStone(stone);
			propertyChangeSupport.firePropertyChange("StonePlaced", null, null);
		}
		repaint();
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
}