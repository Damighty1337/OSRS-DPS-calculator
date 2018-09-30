package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.awt.event.ItemEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main extends JFrame
{

	Formulas formulas = new Formulas();

	private JPanel contentPane;
	private JTextField ATT_textField;
	private JTextField STR_textField;
	private JTextField ATT_BONUS_textField;
	private JTextField STR_BONUS_textField;
	private JTextField AS_textField;
	private JLabel STR_lbl;
	private JLabel ATT_BONUS__lbl;
	private JLabel STR_BONUS_lbl;
	private JLabel AS_lbl;
	private JLabel ENEMY_DEF_lbl;
	private JTextField ENEMY_DEF_textField;
	private JLabel ENEMY_DEF_BONUS_lbl;
	private JTextField ENEMY_DEF_BONUS_textField;
	
	private JMenu fileMenu;
	private JMenuItem helpMenuItem;
	private JMenuItem optionsMenuItem;
	private JMenuItem exitMenuItem;

	private DecimalFormat integerFormat = new DecimalFormat("#");
	private DecimalFormat realFormat = new DecimalFormat("#.##");

	private double maxHit = 0;
	private double accuracy = 0;
	private double dps = 0;
	private JTextField CUR_HP_textField;
	private JTextField MAX_HP_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Main frame = new Main();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Main()
	{
		setResizable(false);
		setTitle("OSRS DPS calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 452);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fileMenu = new JMenu("Menu");
		helpMenuItem = new JMenuItem("Help");
		helpMenuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				new Help(getLocation(), getWidth(), getHeight());
			}
		});
		
		optionsMenuItem = new JMenuItem("Options");
		optionsMenuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		
		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		
		fileMenu.add(helpMenuItem);
		fileMenu.add(optionsMenuItem);
		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);
		
		/*
		try 
		{
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
		    {
		        if ("Nimbus".equals(info.getName())) 
		        {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			
			try
			{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			} 
		}
		*/

		ATT_textField = new JTextField();
		ATT_textField.setText("1");
		ATT_textField.setBounds(149, 14, 86, 23);
		contentPane.add(ATT_textField);
		ATT_textField.setColumns(10);

		STR_textField = new JTextField();
		STR_textField.setText("1");
		STR_textField.setColumns(10);
		STR_textField.setBounds(149, 45, 86, 23);
		contentPane.add(STR_textField);

		ATT_BONUS_textField = new JTextField();
		ATT_BONUS_textField.setText("0");
		ATT_BONUS_textField.setColumns(10);
		ATT_BONUS_textField.setBounds(149, 76, 86, 23);
		contentPane.add(ATT_BONUS_textField);

		STR_BONUS_textField = new JTextField();
		STR_BONUS_textField.setText("0");
		STR_BONUS_textField.setColumns(10);
		STR_BONUS_textField.setBounds(149, 107, 86, 23);
		contentPane.add(STR_BONUS_textField);

		AS_textField = new JTextField();
		AS_textField.setText("2.4");
		AS_textField.setColumns(10);
		AS_textField.setBounds(149, 138, 86, 23);
		contentPane.add(AS_textField);

		JLabel ATT_lbl = new JLabel("ATT/RNG");
		ATT_lbl.setBounds(34, 14, 105, 23);
		contentPane.add(ATT_lbl);

		STR_lbl = new JLabel("STR/RNG");
		STR_lbl.setBounds(34, 45, 105, 23);
		contentPane.add(STR_lbl);

		ATT_BONUS__lbl = new JLabel("ATT_BONUS");
		ATT_BONUS__lbl.setBounds(34, 76, 105, 23);
		contentPane.add(ATT_BONUS__lbl);

		STR_BONUS_lbl = new JLabel("STR_BONUS");
		STR_BONUS_lbl.setBounds(34, 107, 105, 23);
		contentPane.add(STR_BONUS_lbl);

		AS_lbl = new JLabel("AS");
		AS_lbl.setBounds(34, 138, 105, 23);
		contentPane.add(AS_lbl);

		ENEMY_DEF_lbl = new JLabel("E_DEF");
		ENEMY_DEF_lbl.setBounds(284, 14, 105, 23);
		contentPane.add(ENEMY_DEF_lbl);

		ENEMY_DEF_textField = new JTextField();
		ENEMY_DEF_textField.setText("0");
		ENEMY_DEF_textField.setColumns(10);
		ENEMY_DEF_textField.setBounds(399, 14, 86, 23);
		contentPane.add(ENEMY_DEF_textField);

		ENEMY_DEF_BONUS_lbl = new JLabel("E_DEF_BONUS");
		ENEMY_DEF_BONUS_lbl.setBounds(284, 45, 105, 23);
		contentPane.add(ENEMY_DEF_BONUS_lbl);

		ENEMY_DEF_BONUS_textField = new JTextField();
		ENEMY_DEF_BONUS_textField.setText("0");
		ENEMY_DEF_BONUS_textField.setColumns(10);
		ENEMY_DEF_BONUS_textField.setBounds(399, 45, 86, 23);
		contentPane.add(ENEMY_DEF_BONUS_textField);

		JLabel STANCE_lbl = new JLabel("Stance");
		STANCE_lbl.setBounds(34, 169, 105, 23);
		contentPane.add(STANCE_lbl);

		JLabel VOID_label = new JLabel("Void");
		VOID_label.setBounds(34, 200, 105, 23);
		contentPane.add(VOID_label);

		JCheckBox VOID_checkBox = new JCheckBox("");
		VOID_checkBox.setBounds(149, 200, 97, 23);
		contentPane.add(VOID_checkBox);

		JLabel MAX_HIT_lbl = new JLabel("Max hit:");
		MAX_HIT_lbl.setBounds(34, 315, 105, 14);
		contentPane.add(MAX_HIT_lbl);

		JLabel ACCURACY_lbl = new JLabel("Accuracy: ");
		ACCURACY_lbl.setBounds(34, 340, 105, 14);
		contentPane.add(ACCURACY_lbl);

		JLabel DPS_lbl = new JLabel("DPS:");
		DPS_lbl.setBounds(34, 365, 105, 14);
		contentPane.add(DPS_lbl);

		JLabel MAX_HIT_RESULT_lbl = new JLabel("0");
		MAX_HIT_RESULT_lbl.setBounds(149, 315, 86, 14);
		contentPane.add(MAX_HIT_RESULT_lbl);

		JLabel ACCURACY_RESULT_lbl = new JLabel("0%");
		ACCURACY_RESULT_lbl.setBounds(149, 340, 86, 14);
		contentPane.add(ACCURACY_RESULT_lbl);

		JLabel DPS_RESULT_lbl = new JLabel("0");
		DPS_RESULT_lbl.setBounds(149, 365, 86, 14);
		contentPane.add(DPS_RESULT_lbl);

		JCheckBox RANGED_checkBox = new JCheckBox("");
		RANGED_checkBox.setBounds(149, 231, 97, 23);
		contentPane.add(RANGED_checkBox);

		JComboBox STANCE_comboBox = new JComboBox();
		STANCE_comboBox.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				if (STANCE_comboBox.getSelectedIndex() == 1 || STANCE_comboBox.getSelectedIndex() == 2)
				{
					RANGED_checkBox.setSelected(false);
					RANGED_checkBox.setEnabled(false);
				}
				else
				{
					RANGED_checkBox.setEnabled(true);
				}
			}
		});
		STANCE_comboBox.setModel(new DefaultComboBoxModel(new String[] { "Accurate", "Aggressive", "Controlled", "Other" }));
		STANCE_comboBox.setSelectedIndex(0);
		STANCE_comboBox.setToolTipText("");
		STANCE_comboBox.setBounds(149, 169, 105, 23);
		contentPane.add(STANCE_comboBox);

		JLabel RANGED_lbl = new JLabel("Ranged");
		RANGED_lbl.setBounds(34, 231, 105, 23);
		contentPane.add(RANGED_lbl);
		
		JComboBox STR_PRAYER_comboBox = new JComboBox();
		STR_PRAYER_comboBox.setToolTipText("");
		STR_PRAYER_comboBox.setModel(new DefaultComboBoxModel(new String[] {"None", "5% STR", "10% STR", "15% STR", "Chivalry", "Piety", "Rigour"}));
		STR_PRAYER_comboBox.setSelectedIndex(0);
		STR_PRAYER_comboBox.setBounds(399, 169, 105, 23);
		contentPane.add(STR_PRAYER_comboBox);
		
		JComboBox ATT_POTION_comboBox = new JComboBox();
		ATT_POTION_comboBox.setToolTipText("");
		ATT_POTION_comboBox.setModel(new DefaultComboBoxModel(new String[] {"None", "Attack", "Super attack", "Zamorak brew", "Ranging", "Super ranging", "Overload"}));
		ATT_POTION_comboBox.setSelectedIndex(0);
		ATT_POTION_comboBox.setBounds(399, 76, 105, 23);
		contentPane.add(ATT_POTION_comboBox);
		
		JComboBox STR_POTION_comboBox = new JComboBox();
		STR_POTION_comboBox.setToolTipText("");
		STR_POTION_comboBox.setModel(new DefaultComboBoxModel(new String[] {"None", "Strength", "Super strength", "Overload"}));
		STR_POTION_comboBox.setSelectedIndex(0);
		STR_POTION_comboBox.setBounds(399, 107, 105, 23);
		contentPane.add(STR_POTION_comboBox);
		
		JComboBox ATT_PRAYER_comboBox = new JComboBox();
		ATT_PRAYER_comboBox.setToolTipText("");
		ATT_PRAYER_comboBox.setModel(new DefaultComboBoxModel(new String[] {"None", "5% ATT", "10% ATT", "15% ATT", "Chivarly", "Piety", "Rigour"}));
		ATT_PRAYER_comboBox.setSelectedIndex(0);
		ATT_PRAYER_comboBox.setBounds(399, 138, 105, 23);
		contentPane.add(ATT_PRAYER_comboBox);	
		
		ATT_PRAYER_comboBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (ATT_PRAYER_comboBox.getSelectedIndex() == 4)
				{
					STR_PRAYER_comboBox.setSelectedIndex(4);
				}
				else if (ATT_PRAYER_comboBox.getSelectedIndex() == 5)
				{
					STR_PRAYER_comboBox.setSelectedIndex(5);
				}
				else if (ATT_PRAYER_comboBox.getSelectedIndex() == 6)
				{
					STR_PRAYER_comboBox.setSelectedIndex(6);
				}
			}
		});
		
		STR_PRAYER_comboBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (STR_PRAYER_comboBox.getSelectedIndex() == 4)
				{
					ATT_PRAYER_comboBox.setSelectedIndex(4);
				}
				else if (STR_PRAYER_comboBox.getSelectedIndex() == 5)
				{
					ATT_PRAYER_comboBox.setSelectedIndex(5);
				}
				else if (STR_PRAYER_comboBox.getSelectedIndex() == 6)
				{
					ATT_PRAYER_comboBox.setSelectedIndex(6);
				}
			}
		});
		
		JButton CALCULATE_btn = new JButton("Calculate");
		CALCULATE_btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				maxHit = formulas.maxHit(Integer.parseInt(STR_textField.getText()), Integer.parseInt(STR_BONUS_textField.getText()),
						STR_PRAYER_comboBox.getSelectedIndex(), VOID_checkBox.isSelected(), STANCE_comboBox.getSelectedIndex(), 
						RANGED_checkBox.isSelected());
				
				accuracy = formulas.accuracy(Integer.parseInt(ATT_textField.getText()), Integer.parseInt(ATT_BONUS_textField.getText()),
						ATT_PRAYER_comboBox.getSelectedIndex(), VOID_checkBox.isSelected(), STANCE_comboBox.getSelectedIndex(), 
						Integer.parseInt(ENEMY_DEF_textField.getText()), Integer.parseInt(ENEMY_DEF_BONUS_textField.getText()));
				
				dps = 1 / Double.parseDouble(AS_textField.getText()) * accuracy * (maxHit / 2);

				MAX_HIT_RESULT_lbl.setText(integerFormat.format(maxHit));
				ACCURACY_RESULT_lbl.setText(realFormat.format(100 * accuracy) + "%");
				DPS_RESULT_lbl.setText(realFormat.format(dps));
			}
		});
		CALCULATE_btn.setBounds(34, 266, 89, 33);
		contentPane.add(CALCULATE_btn);
		
		JLabel ATT_POT_lbl = new JLabel("Potion 1");
		ATT_POT_lbl.setBounds(284, 76, 105, 23);
		contentPane.add(ATT_POT_lbl);
		
		JLabel STR_POT_lbl = new JLabel("Potion 2");
		STR_POT_lbl.setBounds(284, 107, 105, 23);
		contentPane.add(STR_POT_lbl);
		
		JLabel ATT_PRAYER_lbl = new JLabel("Prayer 1");
		ATT_PRAYER_lbl.setBounds(284, 138, 105, 23);
		contentPane.add(ATT_PRAYER_lbl);
		
		JLabel DHAROKS_lbl = new JLabel("Dharoks");
		DHAROKS_lbl.setBounds(284, 200, 105, 23);
		contentPane.add(DHAROKS_lbl);
		
		JCheckBox DHAROKS_checkBox = new JCheckBox("");
		DHAROKS_checkBox.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				if (DHAROKS_checkBox.isSelected())
				{
					CUR_HP_textField.setEnabled(true);
					MAX_HP_textField.setEnabled(true);
				}
				else
				{
					CUR_HP_textField.setEnabled(false);
					MAX_HP_textField.setEnabled(false);
				}
			}
		});
		DHAROKS_checkBox.setBounds(399, 200, 97, 23);
		contentPane.add(DHAROKS_checkBox);
		
		JLabel HP_lbl = new JLabel("HP");
		HP_lbl.setBounds(284, 231, 105, 20);
		contentPane.add(HP_lbl);
		
		CUR_HP_textField = new JTextField();
		CUR_HP_textField.setEnabled(false);
		CUR_HP_textField.setText("1");
		CUR_HP_textField.setColumns(10);
		CUR_HP_textField.setBounds(399, 231, 33, 23);
		contentPane.add(CUR_HP_textField);
		
		MAX_HP_textField = new JTextField();
		MAX_HP_textField.setEnabled(false);
		MAX_HP_textField.setText("10");
		MAX_HP_textField.setColumns(10);
		MAX_HP_textField.setBounds(461, 231, 33, 23);
		contentPane.add(MAX_HP_textField);
		
		JLabel HP_SEPERATOR_label = new JLabel("/");
		HP_SEPERATOR_label.setBounds(444, 232, 46, 23);
		contentPane.add(HP_SEPERATOR_label);
		
		JLabel STR_PRAYER_lbl = new JLabel("Prayer 2");
		STR_PRAYER_lbl.setBounds(284, 169, 105, 23);
		contentPane.add(STR_PRAYER_lbl);
		
	}
}
