package com.risk.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import com.risk.controller.GameController;
import com.risk.model.Game;
import com.risk.model.GameInstructions;

/**
 * Select Game Window is used to select the game you saved
 * 
 * @author Jingya Pan
 * @version 1.0
 *
 */
public class SelectGameWindow extends JFrame{
	
	/**
	 * Default Serialization ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger object setup for the log file
	 */
	static Logger logger = Logger.getLogger(StartWindow.class.getName());

	@SuppressWarnings("javadoc")
	private JFrame frame;


	/**
	 * Create the application.
	 */
	public SelectGameWindow() {
		
	}

	/**
	 * Loads The Game to the File
	 * @param gameFile File Game Object File
	 * @return Game Previous Game
	 */
	public Game loadGameFromFile(File gameFile) {
		
		Game loadResult = null;
		try {
			
			// read object from file
			FileInputStream fis = new FileInputStream(gameFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Game result = (Game) ois.readObject();
			ois.close();
			
			loadResult = result;

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		return loadResult;
	}
	/**
	 * Initialize the contents of the 
	 */
	public void initialize() {
		setTitle("Select Game");
		setBounds(100, 100, 373, 714);
		getContentPane().setLayout(null);
		setVisible(true);
		JList<String> mapFilesJList = new JList<String>();
		mapFilesJList.setFont(new Font("Calibri", Font.PLAIN, 20));
		mapFilesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mapFilesJList.setBorder(new LineBorder(Color.BLUE));
		mapFilesJList.setBounds(25, 34, 301, 542);
		getContentPane().add(mapFilesJList);

		JButton btnSelect = new JButton("Select");
		btnSelect.setFont(new Font("Calibri", Font.PLAIN, 22));
		btnSelect.setBounds(211, 613, 115, 29);
		getContentPane().add(btnSelect);

		File folder = new File(".\\Games\\");
		File[] listOfFiles = folder.listFiles();
		String[] fileNames = new String[listOfFiles.length];

		int k = 0;
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String fileExtension = listOfFiles[i].getName()
						.substring((listOfFiles[i].getName().lastIndexOf(".") + 1));
				if (fileExtension.equals("game")) {
					fileNames[k] = listOfFiles[i].getName();
					k++;
				}
			}
		}

		mapFilesJList.setListData(fileNames);

		btnSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String fileName = mapFilesJList.getSelectedValue();
				File file = new File(".\\Games\\" + fileName);
				if (fileName == null) {
					JOptionPane.showMessageDialog(frame, "Please select the Game.");
				} else {
					
					Game savedGame = loadGameFromFile(file);
					if (savedGame!=null)
					{
						GameController gameController = new GameController(savedGame,
								new GameInstructions("Risk Game\r\n"));

						GameWindow gameWindow = new GameWindow(savedGame, gameController);
						gameWindow.onGame();
						
					}else {
						JOptionPane.showMessageDialog(frame, "Couldn't load the Game");
					}
					
				}
			}
		});

	}
}