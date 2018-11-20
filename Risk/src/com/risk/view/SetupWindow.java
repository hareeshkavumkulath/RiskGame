package com.risk.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;

import com.risk.controller.MapController;
import com.risk.model.Map;
import com.risk.model.MapMessage;
import com.risk.model.Player;
/**
 * Tournament mode player set up
 * 
 * @author Jingya Pan
 * @version 1.0
 */
public class SetupWindow extends JFrame {

	/**
	 * set the serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("javadoc")
	private JPanel contentPane;
	@SuppressWarnings("javadoc")
	private JTextField textField;
	@SuppressWarnings("javadoc")
	private JList<String> mapList;
	@SuppressWarnings("javadoc")
	private ButtonGroup btnGroup;
	@SuppressWarnings("javadoc")
	private String[] fileNames;
	
	/**
	 * Create the frame.
	 */
	public SetupWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 514);
		getContentPane().setLayout(null);
		setVisible(true);
		
		mapList = new JList<String>();
		mapList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		mapList.setBounds(25, 47, 243, 331);
		getContentPane().add(mapList);
		
		File folder = new File(".\\Maps\\");
		File[] listOfFiles = folder.listFiles();
		fileNames = new String[listOfFiles.length];
		
		int k = 0;
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String fileExtension = listOfFiles[i].getName().substring((listOfFiles[i].getName().lastIndexOf(".")+1));
				if(fileExtension.equals("txt")) {
					fileNames[k] = listOfFiles[i].getName();
					k++;
				}
			}
		}
		mapList.setListData(fileNames);
		
		btnGroup = new ButtonGroup();
		
		initialize();
	}
	/**
	 * initialize
	 */
	public void initialize() {
		
		JCheckBox chckbxAggressive = new JCheckBox("Aggressive");
		chckbxAggressive.setBounds(310, 69, 139, 29);
		chckbxAggressive.setActionCommand("AGGRESSIVE");
		getContentPane().add(chckbxAggressive);
		
		JCheckBox chckbxBenevolent = new JCheckBox("Benevolent");
		chckbxBenevolent.setBounds(310, 114, 139, 29);
		chckbxBenevolent.setActionCommand("BENEVOLENT");
		getContentPane().add(chckbxBenevolent);
		
		JCheckBox chckbxRandom = new JCheckBox("Random");
		chckbxRandom.setBounds(310, 165, 139, 29);
		chckbxRandom.setActionCommand("RANDOM");
		getContentPane().add(chckbxRandom);
		
		JCheckBox chckbxCheater = new JCheckBox("Cheater");
		chckbxCheater.setBounds(310, 215, 139, 29);
		chckbxCheater.setActionCommand("CHEATER");
		getContentPane().add(chckbxCheater);
		
		btnGroup.add(chckbxAggressive);
		btnGroup.add(chckbxBenevolent);
		btnGroup.add(chckbxRandom);
		btnGroup.add(chckbxCheater);
		
		JLabel lblNewLabel = new JLabel("Number of Games:");
		lblNewLabel.setBounds(310, 266, 152, 36);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNumberOfTurns = new JLabel("Number of Turns:");
		lblNumberOfTurns.setBounds(310, 318, 152, 36);
		getContentPane().add(lblNumberOfTurns);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinner.setBounds(477, 271, 32, 26);
		getContentPane().add(spinner);
		
		JLabel lblSelectPlayerStrategies = new JLabel("Select Player Strategies:");
		lblSelectPlayerStrategies.setBounds(310, 23, 256, 36);
		getContentPane().add(lblSelectPlayerStrategies);
		
		textField = new JTextField();
		textField.setBounds(477, 323, 53, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNumberOenterNumber = new JLabel("* (Enter number between 10 and 50)");
		lblNumberOenterNumber.setForeground(Color.GRAY);
		lblNumberOenterNumber.setBounds(545, 318, 270, 36);
		getContentPane().add(lblNumberOenterNumber);
		
		JButton btnNewButton = new JButton("Start Tournament");
		btnNewButton.setBounds(612, 394, 186, 42);
		getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Map> maps = getMaps();
				if(maps != null) {
					ArrayList<Player> players = getPlayers();
				}else {
					JOptionPane.showMessageDialog(null, "Select 1-5 Maps", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		
	}
	
	/**
	 * Getters of the player
	 * 
	 * @return ArrayList list of players
	 */
	protected ArrayList<Player> getPlayers() {
		return null;
	}
	
	/**
	 * Get the selected map and load it
	 * 
	 * @return ArrayList list of maps
	 */
	protected ArrayList<Map> getMaps() {
		int[] indices = mapList.getSelectedIndices();
		if(indices.length > 5 || indices.length < 1) {
			return null;
		}else {
			ArrayList<Map> maps = new ArrayList<Map>();
			for(int i=0;i<indices.length;i++) {
				MapController controller = new MapController();
				File file = new File(".\\Maps\\" + fileNames[indices[i]]); 
				MapMessage message = controller.processFile(file);
				if(message.isValidMap()) {
					Map map = message.getMap();
					maps.add(map);
				}
			}
			return maps;
		}
	}
}
