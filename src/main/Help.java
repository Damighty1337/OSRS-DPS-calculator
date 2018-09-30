package main;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class Help extends JFrame
{

	private JPanel contentPane;
	
	private JLabel HELP_lbl;

	/**
	 * Create the frame.
	 */
	public Help(Point location, int width, int height)
	{
		setResizable(false);
		setTitle("Help");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 495, 408);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		setContentPane(contentPane);
		
		HELP_lbl = new JLabel("<html>\r\n<b>Documentation</b><br><br>\r\nATT/RNG = Attack or ranged level<br><br>\r\nSTR/RNG = Strength or ranged level<br><br>\r\nATT_BONUS = Equipment bonus for corresponding attack type<br><br>\r\nSTR_BONUS = Strength or ranged strength bonus<br><br>\r\nAS = Interval between attacks in seconds<br><br>\r\nE_DEF = Enemy defence level<br><br>\r\nE_DEF_BONUS = Enemy defence equipment bonus for corresponding attack type<br><br>\r\nStance = Attack style. Note that aggressive and controlled are melee only styles<br><br>\r\nVoid = Check if using a Void set<br><br>\r\nRanged = Check if using a ranged weapon\r\n</html");
		HELP_lbl.setVerticalAlignment(SwingConstants.TOP);
		
		contentPane.add(HELP_lbl);
		
		setLocation(location.x + (width - getWidth()) / 2, location.y + (height - getHeight()) / 2);
		setVisible(true);
	}
}
