package curling;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class StatusPanel extends JPanel{
	JTextField player, otherStatus1, otherStatus2;

	public StatusPanel() {
		setBorder(new TitledBorder (new EtchedBorder(), "Status"));
		setLayout(new GridLayout(3, 0));
		createLayout();
	}
	
	public void createLayout()
	{
		JPanel playerStatusPanel = new JPanel();
		playerStatusPanel.setBorder(new TitledBorder (new EtchedBorder(), "Player"));
		player =  new JTextField(10);
		player.setEditable(false);
		playerStatusPanel.add(player);
		
		JPanel otherStatus1StatusPanel = new JPanel();
		otherStatus1StatusPanel.setBorder(new TitledBorder (new EtchedBorder(), "OtherStatus1"));
		otherStatus1 =  new JTextField(10);
		otherStatus1.setEditable(false);
		otherStatus1StatusPanel.add(otherStatus1);
		
		JPanel otherStatus2StatusPanel = new JPanel();
		otherStatus2StatusPanel.setBorder(new TitledBorder (new EtchedBorder(), "OtherStatus2"));
		otherStatus2 =  new JTextField(10);
		otherStatus2.setEditable(false);
		otherStatus2StatusPanel.add(otherStatus2);
		
		add(playerStatusPanel);
		add(otherStatus1StatusPanel);
		add(otherStatus2StatusPanel);
	}

}
