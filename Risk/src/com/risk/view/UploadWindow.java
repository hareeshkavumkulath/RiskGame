package com.risk.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.risk.controller.MapController;
import com.risk.model.Continent;
import com.risk.model.MapMessage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Map Upload Window for the user to upload .map file. It validates and prints the map.
 * 
 * @author Hareesh Kavumkulath
 * @author Anqi Wang
 * @version 1.0
 * 
 * @date 10-07-2018
 * @modifiedDate 10-07-2018
 * @modifiedBy Jingya Pan
 * @modifiedBy Anqi Wang
 *
 */
public class UploadWindow {


    private JFrame frame;
    private JTextField fileName;
    private File file;
    private JButton uploadButton;
    
    /**
     * Launch the application.
     */
    //public void displayUploadMapWindow() {
    public void main() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UploadWindow window = new UploadWindow();
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
    public UploadWindow() {
        initialize();
    }

    public void initialize() {
    	frame = new JFrame();
		frame.setBounds(100, 100, 1071, 681);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		frame.setTitle("Upload Map File");
		
		fileName = new JTextField();
		fileName.setBounds(46, 49, 281, 26);
		frame.getContentPane().add(fileName);
		fileName.setColumns(10);
		
		JButton browseButton = new JButton("Browse");
		browseButton.setBounds(376, 48, 115, 29);
		frame.getContentPane().add(browseButton);
		
		uploadButton = new JButton("Upload");
		uploadButton.setBounds(530, 48, 115, 29);
		frame.getContentPane().add(uploadButton);
		
		/* Continents Information - Start */
		JLabel labelContinents = new JLabel("Continents");
		labelContinents.setBounds(45, 108, 115, 20);
		frame.getContentPane().add(labelContinents);
		
		JTextPane continentInfo = new JTextPane();
		continentInfo.setBounds(46, 148, 300, 500);
		continentInfo.setEditable(true);
        
		JScrollPane scrollPaneContinents = new JScrollPane(continentInfo);
		scrollPaneContinents.setBounds(46, 142, 230, 349);
		scrollPaneContinents.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPaneContinents);
		
		/* Territories Information - Start */
		JLabel labelTerritories = new JLabel("Territories");
		labelTerritories.setBounds(293, 108, 115, 20);
		frame.getContentPane().add(labelTerritories);
		
		JTextPane territoryInfo = new JTextPane();
		territoryInfo.setBounds(46, 148, 300, 500);
		territoryInfo.setEditable(true);
		
		JScrollPane scrollPaneTerritories = new JScrollPane(territoryInfo);
		scrollPaneTerritories.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneTerritories.setBounds(291, 142, 230, 349);
		frame.getContentPane().add(scrollPaneTerritories);
		/* Territories Information - End */
		
		/* Adjacent Territories Information - Start */
		JLabel labelAdjacentTerritories = new JLabel("Adjacent Territories");
		labelAdjacentTerritories.setBounds(540, 108, 201, 20);
		frame.getContentPane().add(labelAdjacentTerritories);
		
		JTextPane adjacentTerritoryInfo = new JTextPane();
		adjacentTerritoryInfo.setBounds(46, 148, 300, 500);
		adjacentTerritoryInfo.setEditable(true);
		
		JScrollPane scrollPaneAdjacentTerritories = new JScrollPane(adjacentTerritoryInfo);
		scrollPaneAdjacentTerritories.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneAdjacentTerritories.setBounds(542, 142, 230, 349);
		frame.getContentPane().add(scrollPaneAdjacentTerritories);
		/* Adjacent Territories Information - End */
		
        browseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(".Map File", "map"));
                fileChooser.setAcceptAllFileFilterUsed(false);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    fileName.setText(fileChooser.getSelectedFile().getName().toString());
                    file = fileChooser.getSelectedFile();
                } else {
                    String message = "File Chosen was cancelled" + ". Please select a \".map\" file";
                    JOptionPane.showMessageDialog(new JFrame(), message, "No File Selected",
                            JOptionPane.ERROR_MESSAGE);

                }

            }
        });
        
        uploadButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MapMessage mapMessage = (MapMessage)MapController.processFile(file);
				System.out.println(mapMessage.isValidMap());
				if(mapMessage.isValidMap()) {
					StringBuffer continentsInfo = new StringBuffer();
					ArrayList<Continent> continents = mapMessage.getContinents();
					String[] continentNames = new String[continents.size()];
					for(int i = 0; i < continents.size(); i++) {
						Continent thisContinent = (Continent) continents.get(i);
						continentNames[i] = thisContinent.getName(); 
						continentsInfo.append(thisContinent.getName());
						continentsInfo.append("\r\n");
					}
					continentInfo.setText(continentsInfo.toString());
				}else {
					String message = mapMessage.getMessage().toString();
					continentInfo.setText(message);
				}				
			}
		});


    }
}



