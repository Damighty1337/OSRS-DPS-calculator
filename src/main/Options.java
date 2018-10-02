package main;

import java.awt.Frame;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Options extends JFrame
{

	private JPanel contentPane;

	public Options(Point location, int width, int height)
	{
		setResizable(false);
		setTitle("Options");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 288, 164);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel STYLE_lbl = new JLabel("Window style:");
		JComboBox<String> STYLE_comboBox = new JComboBox<String>();
		JButton APPLY_btn = new JButton("Apply");

		STYLE_lbl.setBounds(getWidth() / 2 - 105, 31, 105, 23);
		STYLE_comboBox.setBounds(getWidth() / 2 - 130 / 8, 31, 130, 23);
		APPLY_btn.setBounds(getWidth() / 2 - 130 / 2, 75, 130, 34);
		

		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
		{
			STYLE_comboBox.addItem(info.getName());
		}

		APPLY_btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
				{
					if (STYLE_comboBox.getSelectedItem().toString() == info.getName())
					{
						try
						{
							UIManager.setLookAndFeel(info.getClassName());

							for (Frame frame : Frame.getFrames())
							{
								SwingUtilities.updateComponentTreeUI(frame);
							}
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				}
			}
		});
		
		contentPane.setLayout(null);
		contentPane.add(STYLE_lbl);
		contentPane.add(STYLE_comboBox);
		contentPane.add(APPLY_btn);

		setLocation(location.x + (width - getWidth()) / 2, location.y + (height - getHeight()) / 2);
		setVisible(true);
		
	}
}
