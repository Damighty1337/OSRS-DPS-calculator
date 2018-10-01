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

	private DecimalFormat integerFormat = new DecimalFormat("#");
	private DecimalFormat realFormat = new DecimalFormat("#.##");

	private double maxHit = 0;
	private double accuracy = 0;
	private double dps = 0;

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

		JMenu fileMenu = new JMenu("Menu");
		JMenuItem helpMenuItem = new JMenuItem("Help");
		JMenuItem optionsMenuItem = new JMenuItem("Options");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		JMenuBar menuBar = new JMenuBar();

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
				// TODO: Implement LookAndFeel options in the options menu
			}
		});

		exitMenuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});

		JLabel[] labels = new JLabel[] { new JLabel("ATT/RNG"), new JLabel("STR/RNG"), new JLabel("ATT_BONUS"),
				new JLabel("STR_BONUS"), new JLabel("AS"), new JLabel("E_DEF"), new JLabel("E_DEF_BONUS"),
				new JLabel("Stance"), new JLabel("Void"), new JLabel("Ranged"), new JLabel("Max hit:"),
				new JLabel("Accuracy:"), new JLabel("DPS:"), new JLabel("Potion 1"), new JLabel("Potion 2"),
				new JLabel("Prayer 1"), new JLabel("Prayer 2"), new JLabel("Dharoks"), new JLabel("HP"),
				new JLabel("/") };

		labels[0].setBounds(34, 14, 105, 23);
		labels[1].setBounds(34, 45, 105, 23);
		labels[2].setBounds(34, 76, 105, 23);
		labels[3].setBounds(34, 107, 105, 23);
		labels[4].setBounds(34, 138, 105, 23);
		labels[5].setBounds(284, 14, 105, 23);
		labels[6].setBounds(284, 45, 105, 23);
		labels[7].setBounds(34, 169, 105, 23);
		labels[8].setBounds(34, 200, 105, 23);
		labels[9].setBounds(34, 231, 105, 23);
		labels[10].setBounds(34, 315, 105, 14);
		labels[11].setBounds(34, 340, 105, 14);
		labels[12].setBounds(34, 365, 105, 14);
		labels[13].setBounds(284, 76, 105, 23);
		labels[14].setBounds(284, 107, 105, 23);
		labels[15].setBounds(284, 138, 105, 23);
		labels[16].setBounds(284, 169, 105, 23);
		labels[17].setBounds(284, 200, 105, 23);
		labels[18].setBounds(284, 231, 105, 20);
		labels[19].setBounds(444, 232, 46, 23);

		JLabel MAX_HIT_RESULT_lbl = new JLabel("0");
		JLabel ACCURACY_RESULT_lbl = new JLabel("0%");
		JLabel DPS_RESULT_lbl = new JLabel("0");

		JTextField ATT_textField = new JTextField();
		JTextField STR_textField = new JTextField();
		JTextField ATT_BONUS_textField = new JTextField();
		JTextField STR_BONUS_textField = new JTextField();
		JTextField AS_textField = new JTextField();
		JTextField ENEMY_DEF_textField = new JTextField();
		JTextField ENEMY_DEF_BONUS_textField = new JTextField();
		JTextField CUR_HP_textField = new JTextField();
		JTextField MAX_HP_textField = new JTextField();
		JComboBox<String> STANCE_comboBox = new JComboBox<String>();
		JComboBox<String> ATT_POTION_comboBox = new JComboBox<String>();
		JComboBox<String> STR_POTION_comboBox = new JComboBox<String>();
		JComboBox<String> ATT_PRAYER_comboBox = new JComboBox<String>();
		JComboBox<String> STR_PRAYER_comboBox = new JComboBox<String>();
		JCheckBox VOID_checkBox = new JCheckBox("");
		JCheckBox RANGED_checkBox = new JCheckBox("");
		JCheckBox DHAROKS_checkBox = new JCheckBox("");
		JButton CALCULATE_btn = new JButton("Calculate");

		MAX_HIT_RESULT_lbl.setBounds(149, 315, 86, 14);
		ACCURACY_RESULT_lbl.setBounds(149, 340, 86, 14);
		DPS_RESULT_lbl.setBounds(149, 365, 86, 14);

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

		STANCE_comboBox.setToolTipText("");
		STANCE_comboBox.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Accurate", "Aggressive", "Controlled", "Other" }));
		STANCE_comboBox.setSelectedIndex(0);
		STANCE_comboBox.setBounds(149, 169, 105, 23);

		ATT_PRAYER_comboBox.setToolTipText("");
		ATT_PRAYER_comboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "None", "5% ATT", "10% ATT", "15% ATT", "Chivarly", "Piety", "Rigour" }));
		ATT_PRAYER_comboBox.setSelectedIndex(0);
		ATT_PRAYER_comboBox.setBounds(399, 138, 105, 23);

		STR_PRAYER_comboBox.setToolTipText("");
		STR_PRAYER_comboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "None", "5% STR", "10% STR", "15% STR", "Chivalry", "Piety", "Rigour" }));
		STR_PRAYER_comboBox.setSelectedIndex(0);
		STR_PRAYER_comboBox.setBounds(399, 169, 105, 23);

		ATT_POTION_comboBox.setToolTipText("");
		ATT_POTION_comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "None", "Attack", "Super attack",
				"Zamorak brew", "Ranging", "Super ranging", "Overload" }));
		ATT_POTION_comboBox.setSelectedIndex(0);
		ATT_POTION_comboBox.setBounds(399, 76, 105, 23);

		STR_POTION_comboBox.setToolTipText("");
		STR_POTION_comboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "None", "Strength", "Super strength", "Zamorak brew", "Overload" }));
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

		ATT_POTION_comboBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (ATT_POTION_comboBox.getSelectedIndex() == 3)
				{
					STR_POTION_comboBox.setSelectedIndex(3);
				}
			}
		});

		STR_POTION_comboBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (STR_POTION_comboBox.getSelectedIndex() == 3)
				{
					ATT_POTION_comboBox.setSelectedIndex(3);
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
				maxHit = formulas.maxHit(Integer.parseInt(STR_textField.getText()),
						Integer.parseInt(STR_BONUS_textField.getText()), STR_POTION_comboBox.getSelectedIndex(),
						STR_PRAYER_comboBox.getSelectedIndex(), VOID_checkBox.isSelected(),
						STANCE_comboBox.getSelectedIndex(), RANGED_checkBox.isSelected(), DHAROKS_checkBox.isSelected(),
						Integer.parseInt(CUR_HP_textField.getText()), Integer.parseInt(MAX_HP_textField.getText()));

				accuracy = formulas.accuracy(Integer.parseInt(ATT_textField.getText()),
						Integer.parseInt(ATT_BONUS_textField.getText()), ATT_POTION_comboBox.getSelectedIndex(),
						ATT_PRAYER_comboBox.getSelectedIndex(), VOID_checkBox.isSelected(),
						STANCE_comboBox.getSelectedIndex(), Integer.parseInt(ENEMY_DEF_textField.getText()),
						Integer.parseInt(ENEMY_DEF_BONUS_textField.getText()));

				dps = 1 / Double.parseDouble(AS_textField.getText()) * accuracy * (maxHit / 2);

				MAX_HIT_RESULT_lbl.setText(integerFormat.format(maxHit));
				ACCURACY_RESULT_lbl.setText(realFormat.format(100 * accuracy) + "%");
				DPS_RESULT_lbl.setText(realFormat.format(dps));
			}
		});

		for (JLabel label : labels)
		{
			getContentPane().add(label);
		}

		getContentPane().add(ATT_textField);
		getContentPane().add(STR_textField);
		getContentPane().add(ATT_BONUS_textField);
		getContentPane().add(STR_BONUS_textField);
		getContentPane().add(AS_textField);
		getContentPane().add(ENEMY_DEF_textField);
		getContentPane().add(ENEMY_DEF_BONUS_textField);
		getContentPane().add(STANCE_comboBox);
		getContentPane().add(VOID_checkBox);
		getContentPane().add(RANGED_checkBox);
		getContentPane().add(MAX_HIT_RESULT_lbl);
		getContentPane().add(ACCURACY_RESULT_lbl);
		getContentPane().add(DPS_RESULT_lbl);
		getContentPane().add(ATT_POTION_comboBox);
		getContentPane().add(STR_POTION_comboBox);
		getContentPane().add(ATT_PRAYER_comboBox);
		getContentPane().add(STR_PRAYER_comboBox);
		getContentPane().add(DHAROKS_checkBox);
		getContentPane().add(CUR_HP_textField);
		getContentPane().add(MAX_HP_textField);
		getContentPane().add(CALCULATE_btn);

		CALCULATE_btn.doClick();

	}
}
