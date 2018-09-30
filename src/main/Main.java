package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
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

	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem helpMenuItem;
	private JMenuItem optionsMenuItem;
	private JMenuItem exitMenuItem;
	
	private JLabel ATT_lbl;
	private JLabel STR_lbl;
	private JLabel ATT_BONUS__lbl;
	private JLabel STR_BONUS_lbl;
	private JLabel AS_lbl;
	private JLabel ENEMY_DEF_lbl;
	private JLabel ENEMY_DEF_BONUS_lbl;
	private JLabel STANCE_lbl;
	private JLabel VOID_label;
	private JLabel RANGED_lbl;
	private JLabel MAX_HIT_lbl;
	private JLabel ACCURACY_lbl;
	private JLabel DPS_lbl;
	private JLabel MAX_HIT_RESULT_lbl;
	private JLabel ACCURACY_RESULT_lbl;
	private JLabel DPS_RESULT_lbl;
	private JLabel ATT_POT_lbl;
	private JLabel STR_POT_lbl;
	private JLabel ATT_PRAYER_lbl;
	private JLabel STR_PRAYER_lbl;
	private JLabel DHAROKS_lbl;
	private JLabel HP_lbl;
	private JLabel HP_SEPERATOR_label;
	private JTextField ATT_textField;
	private JTextField STR_textField;
	private JTextField ATT_BONUS_textField;
	private JTextField STR_BONUS_textField;
	private JTextField AS_textField;
	private JTextField ENEMY_DEF_textField;
	private JTextField ENEMY_DEF_BONUS_textField;
	private JTextField CUR_HP_textField;
	private JTextField MAX_HP_textField;
	private JComboBox<String> STANCE_comboBox;
	private JComboBox<String> ATT_POTION_comboBox;
	private JComboBox<String> STR_POTION_comboBox;
	private JComboBox<String> ATT_PRAYER_comboBox;
	private JComboBox<String> STR_PRAYER_comboBox;
	private JCheckBox VOID_checkBox;
	private JCheckBox RANGED_checkBox;
	private JCheckBox DHAROKS_checkBox;
	private JButton CALCULATE_btn;

	private DecimalFormat integerFormat = new DecimalFormat("#");
	private DecimalFormat realFormat = new DecimalFormat("#.##");

	private double maxHit = 0;
	private double accuracy = 0;
	private double dps = 0;

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
	public Main()
	{
		setResizable(false);
		setTitle("OSRS DPS calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 452);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		setContentPane(contentPane);

		fileMenu = new JMenu("Menu");
		helpMenuItem = new JMenuItem("Help");
		optionsMenuItem = new JMenuItem("Options");
		exitMenuItem = new JMenuItem("Exit");
		menuBar = new JMenuBar();
		
		fileMenu.add(helpMenuItem);
		fileMenu.add(optionsMenuItem);
		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);
		
		setJMenuBar(menuBar);

		helpMenuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				new Help(getLocation(), getWidth(), getHeight());
			}
		});
		
		optionsMenuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//TODO: Implement LookAndFeel options in the options menu
			}
		});
		
		exitMenuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		
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
		
		ATT_lbl = new JLabel("ATT/RNG");
		STR_lbl = new JLabel("STR/RNG");
		ATT_BONUS__lbl = new JLabel("ATT_BONUS");
		STR_BONUS_lbl = new JLabel("STR_BONUS");
		AS_lbl = new JLabel("AS");
		ENEMY_DEF_lbl = new JLabel("E_DEF");
		ENEMY_DEF_BONUS_lbl = new JLabel("E_DEF_BONUS");
		STANCE_lbl = new JLabel("Stance");
		VOID_label = new JLabel("Void");
		RANGED_lbl = new JLabel("Ranged");
		MAX_HIT_lbl = new JLabel("Max hit:");
		ACCURACY_lbl = new JLabel("Accuracy:");
		DPS_lbl = new JLabel("DPS:");
		MAX_HIT_RESULT_lbl = new JLabel("0");
		ACCURACY_RESULT_lbl = new JLabel("0%");
		DPS_RESULT_lbl = new JLabel("0");
		ATT_POT_lbl = new JLabel("Potion 1");
		STR_POT_lbl = new JLabel("Potion 2");
		ATT_PRAYER_lbl = new JLabel("Prayer 1");
		STR_PRAYER_lbl = new JLabel("Prayer 2");
		DHAROKS_lbl = new JLabel("Dharoks");
		HP_lbl = new JLabel("HP");
		HP_SEPERATOR_label = new JLabel("/");
		ATT_textField = new JTextField();
		STR_textField = new JTextField();
		ATT_BONUS_textField = new JTextField();
		STR_BONUS_textField = new JTextField();
		AS_textField = new JTextField();
		ENEMY_DEF_textField = new JTextField();
		ENEMY_DEF_BONUS_textField = new JTextField();
		CUR_HP_textField = new JTextField();
		MAX_HP_textField = new JTextField();
		STANCE_comboBox = new JComboBox<String>();
		ATT_POTION_comboBox = new JComboBox<String>();
		STR_POTION_comboBox = new JComboBox<String>();
		ATT_PRAYER_comboBox = new JComboBox<String>();
		STR_PRAYER_comboBox = new JComboBox<String>();
		VOID_checkBox = new JCheckBox("");
		RANGED_checkBox = new JCheckBox("");
		DHAROKS_checkBox = new JCheckBox("");
		CALCULATE_btn = new JButton("Calculate");
		
		ATT_lbl.setBounds(34, 14, 105, 23);
		STR_lbl.setBounds(34, 45, 105, 23);
		ATT_BONUS__lbl.setBounds(34, 76, 105, 23);
		STR_BONUS_lbl.setBounds(34, 107, 105, 23);
		AS_lbl.setBounds(34, 138, 105, 23);
		ENEMY_DEF_lbl.setBounds(284, 14, 105, 23);
		ENEMY_DEF_BONUS_lbl.setBounds(284, 45, 105, 23);
		STANCE_lbl.setBounds(34, 169, 105, 23);
		VOID_label.setBounds(34, 200, 105, 23);
		RANGED_lbl.setBounds(34, 231, 105, 23);
		MAX_HIT_lbl.setBounds(34, 315, 105, 14);
		ACCURACY_lbl.setBounds(34, 340, 105, 14);
		DPS_lbl.setBounds(34, 365, 105, 14);
		MAX_HIT_RESULT_lbl.setBounds(149, 315, 86, 14);
		ACCURACY_RESULT_lbl.setBounds(149, 340, 86, 14);
		DPS_RESULT_lbl.setBounds(149, 365, 86, 14);
		ATT_POT_lbl.setBounds(284, 76, 105, 23);
		STR_POT_lbl.setBounds(284, 107, 105, 23);	
		ATT_PRAYER_lbl.setBounds(284, 138, 105, 23);
		STR_PRAYER_lbl.setBounds(284, 169, 105, 23);
		DHAROKS_lbl.setBounds(284, 200, 105, 23);
		HP_lbl.setBounds(284, 231, 105, 20);
		HP_SEPERATOR_label.setBounds(444, 232, 46, 23);
		
		ATT_textField.setText("1");
		ATT_textField.setBounds(149, 14, 86, 23);
		ATT_textField.setColumns(10);

		STR_textField.setText("1");
		STR_textField.setColumns(10);
		STR_textField.setBounds(149, 45, 86, 23);

		ATT_BONUS_textField.setText("0");
		ATT_BONUS_textField.setColumns(10);
		ATT_BONUS_textField.setBounds(149, 76, 86, 23);

		STR_BONUS_textField.setText("0");
		STR_BONUS_textField.setColumns(10);
		STR_BONUS_textField.setBounds(149, 107, 86, 23);

		AS_textField.setText("2.4");
		AS_textField.setColumns(10);
		AS_textField.setBounds(149, 138, 86, 23);
	
		VOID_checkBox.setBounds(149, 200, 97, 23);
		RANGED_checkBox.setBounds(149, 231, 97, 23);
		DHAROKS_checkBox.setBounds(399, 200, 97, 23);

		ENEMY_DEF_textField.setText("1");
		ENEMY_DEF_textField.setColumns(10);
		ENEMY_DEF_textField.setBounds(399, 14, 86, 23);

		ENEMY_DEF_BONUS_textField.setText("0");
		ENEMY_DEF_BONUS_textField.setColumns(10);
		ENEMY_DEF_BONUS_textField.setBounds(399, 45, 86, 23);

		STANCE_comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Accurate", "Aggressive", "Controlled", "Other" }));
		STANCE_comboBox.setSelectedIndex(0);
		STANCE_comboBox.setToolTipText("");
		STANCE_comboBox.setBounds(149, 169, 105, 23);
		
		ATT_PRAYER_comboBox.setToolTipText("");
		ATT_PRAYER_comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"None", "5% ATT", "10% ATT", "15% ATT", "Chivarly", "Piety", "Rigour"}));
		ATT_PRAYER_comboBox.setSelectedIndex(0);
		ATT_PRAYER_comboBox.setBounds(399, 138, 105, 23);
		
		STR_PRAYER_comboBox.setToolTipText("");
		STR_PRAYER_comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"None", "5% STR", "10% STR", "15% STR", "Chivalry", "Piety", "Rigour"}));
		STR_PRAYER_comboBox.setSelectedIndex(0);
		STR_PRAYER_comboBox.setBounds(399, 169, 105, 23);
		
		ATT_POTION_comboBox.setToolTipText("");
		ATT_POTION_comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"None", "Attack", "Super attack", "Zamorak brew", "Ranging", "Super ranging", "Overload"}));
		ATT_POTION_comboBox.setSelectedIndex(0);
		ATT_POTION_comboBox.setBounds(399, 76, 105, 23);
		
		STR_POTION_comboBox.setToolTipText("");
		STR_POTION_comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"None", "Strength", "Super strength", "Zamorak brew", "Overload"}));
		STR_POTION_comboBox.setSelectedIndex(0);
		STR_POTION_comboBox.setBounds(399, 107, 105, 23);
		
		CUR_HP_textField.setEnabled(false);
		CUR_HP_textField.setText("1");
		CUR_HP_textField.setColumns(10);
		CUR_HP_textField.setBounds(399, 231, 33, 23);
		
		MAX_HP_textField.setEnabled(false);
		MAX_HP_textField.setText("10");
		MAX_HP_textField.setColumns(10);
		MAX_HP_textField.setBounds(461, 231, 33, 23);
		
		CALCULATE_btn.setBounds(34, 266, 89, 33);
		
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
		
		CALCULATE_btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculate();
			}
		});
		
		contentPane.add(STR_textField);
		contentPane.add(ATT_BONUS_textField);
		contentPane.add(STR_BONUS_textField);
		contentPane.add(ATT_textField);
		contentPane.add(AS_textField);
		contentPane.add(ATT_lbl);
		contentPane.add(STR_lbl);
		contentPane.add(ATT_BONUS__lbl);
		contentPane.add(STR_BONUS_lbl);
		contentPane.add(AS_lbl);
		contentPane.add(ENEMY_DEF_lbl);
		contentPane.add(ENEMY_DEF_textField);
		contentPane.add(ENEMY_DEF_BONUS_lbl);
		contentPane.add(ENEMY_DEF_BONUS_textField);
		contentPane.add(STANCE_lbl);
		contentPane.add(VOID_label);
		contentPane.add(VOID_checkBox);
		contentPane.add(MAX_HIT_lbl);
		contentPane.add(ACCURACY_lbl);
		contentPane.add(DPS_RESULT_lbl);
		contentPane.add(DPS_lbl);
		contentPane.add(MAX_HIT_RESULT_lbl);
		contentPane.add(ACCURACY_RESULT_lbl);
		contentPane.add(RANGED_checkBox);
		contentPane.add(RANGED_lbl);
		contentPane.add(ATT_POTION_comboBox);
		contentPane.add(STR_PRAYER_comboBox);
		contentPane.add(STANCE_comboBox);
		contentPane.add(STR_POTION_comboBox);
		contentPane.add(ATT_PRAYER_comboBox);	
		contentPane.add(CALCULATE_btn);
		contentPane.add(ATT_POT_lbl);
		contentPane.add(STR_POT_lbl);
		contentPane.add(ATT_PRAYER_lbl);
		contentPane.add(DHAROKS_lbl);
		contentPane.add(DHAROKS_checkBox);
		contentPane.add(HP_lbl);
		contentPane.add(HP_SEPERATOR_label);
		contentPane.add(STR_PRAYER_lbl);
		contentPane.add(CUR_HP_textField);
		contentPane.add(MAX_HP_textField);
		
		calculate();
		
	}
	
	private void calculate()
	{
		maxHit = formulas.maxHit(Integer.parseInt(STR_textField.getText()), Integer.parseInt(STR_BONUS_textField.getText()),
				STR_POTION_comboBox.getSelectedIndex(), STR_PRAYER_comboBox.getSelectedIndex(), VOID_checkBox.isSelected(),
				STANCE_comboBox.getSelectedIndex(), RANGED_checkBox.isSelected());
		
		accuracy = formulas.accuracy(Integer.parseInt(ATT_textField.getText()), Integer.parseInt(ATT_BONUS_textField.getText()),
				ATT_POTION_comboBox.getSelectedIndex(), ATT_PRAYER_comboBox.getSelectedIndex(), VOID_checkBox.isSelected(), 
				STANCE_comboBox.getSelectedIndex(),  Integer.parseInt(ENEMY_DEF_textField.getText()), 
				Integer.parseInt(ENEMY_DEF_BONUS_textField.getText()));
		
		dps = 1 / Double.parseDouble(AS_textField.getText()) * accuracy * (maxHit / 2);
		
		MAX_HIT_RESULT_lbl.setText(integerFormat.format(maxHit));
		ACCURACY_RESULT_lbl.setText(realFormat.format(100 * accuracy) + "%");
		DPS_RESULT_lbl.setText(realFormat.format(dps));
	}
}
