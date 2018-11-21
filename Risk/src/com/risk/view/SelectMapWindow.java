package com.risk.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import com.risk.controller.MapController;
import com.risk.model.Map;
import com.risk.model.MapMessage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
/**
 * SelectMapWindow is used to select the map you uploaded and saved in the previous page
 * 
 * @author Hareesh Kavumkulath
 * @version 1.1
 *
 */
public class SelectMapWindow {
	
	/**
	 * Logger object setup for the log file
	 */
	static Logger logger = Logger.getLogger(StartWindow.class.getName());

	@SuppressWarnings("javadoc")
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectMapWindow window = new SelectMapWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SelectMapWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Select Map");
		frame.setBounds(100, 100, 373, 714);
		frame.getContentPane().setLayout(null);
		
		JList<String> mapFilesJList = new JList<String>();
		mapFilesJList.setFont(new Font("Calibri", Font.PLAIN, 20));
		mapFilesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mapFilesJList.setBorder(new LineBorder(Color.BLUE));
		mapFilesJList.setBounds(25, 34, 301, 542);
		frame.getContentPane().add(mapFilesJList);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setFont(new Font("Calibri", Font.PLAIN, 22));
		btnSelect.setBounds(211, 613, 115, 29);
		frame.getContentPane().add(btnSelect);
		
		File folder = new File(".\\Maps\\");
		File[] listOfFiles = folder.listFiles();
		String[] fileNames = new String[listOfFiles.length];
		
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

		mapFilesJList.setListData(fileNames);	
		
		btnSelect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String fileName = mapFilesJList.getSelectedValue();
				File file = new File(".\\Maps\\" + fileName);  
				if(fileName == null) {
					JOptionPane.showMessageDialog(frame, "Please select the map.");
				} else {
					logger.log(Level.INFO, fileName + " is selected, proceeding to Setup Player Window");
					MapController controller = new MapController();
					MapMessage message = controller.processFile(file);
					if(message.isValidMap()) {
						Map map = message.getMap();
						SetPlayersWindow setPlayersWindow = new SetPlayersWindow();
						setPlayersWindow.main(map);
					}
				}
			}
		});
		
	}
}
