package com.risk.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.risk.controller.MapController;
import com.risk.model.Continent;
import com.risk.model.MapMessage;
import com.risk.model.Territory;

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
    private MapMessage mapMessage;
    
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
		
		JList<String> continentsJList = new JList<String>();
		continentsJList.setBorder(new LineBorder(Color.BLUE));
		continentsJList.setBounds(46, 144, 201, 349);
		continentsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		frame.getContentPane().add(continentsJList);
        
		/* Territories Information - Start */
		JLabel labelTerritories = new JLabel("Territories");
		labelTerritories.setBounds(277, 108, 115, 20);
		frame.getContentPane().add(labelTerritories);
		
		JList<String> territoriesList = new JList<String>();
		territoriesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		territoriesList.setBorder(new LineBorder(Color.BLUE));
		territoriesList.setBounds(277, 144, 211, 349);
		frame.getContentPane().add(territoriesList);
		
		/* Territories Information - End */
		
		/* Adjacent Territories Information - Start */
		JLabel labelAdjacentTerritories = new JLabel("Adjacent Territories");
		labelAdjacentTerritories.setBounds(524, 108, 201, 20);
		frame.getContentPane().add(labelAdjacentTerritories);		
		
		JList<String> adjTerritoriesList = new JList<String>();
		adjTerritoriesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		adjTerritoriesList.setBorder(new LineBorder(Color.BLUE));
		adjTerritoriesList.setBounds(524, 144, 211, 349);
		frame.getContentPane().add(adjTerritoriesList);
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
				mapMessage = (MapMessage)MapController.processFile(file);
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
					continentsJList.setListData(continentNames);
					
				}else {
					String message = mapMessage.getMessage().toString();
					//continentInfo.setText(message);
				}				
			}
		});
        
        /* Continents selection Action --> Display Territories - Start */
        
        continentsJList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				JList list = (JList) listSelectionEvent.getSource();
				int selections[] = list.getSelectedIndices();
				ArrayList<Territory> territories = mapMessage.getContinents().get(selections[0]).getTerritories();
				String[] territoryNames = new String[territories.size()];
				for(int i=0;i<territories.size();i++) {
					territoryNames[i] = territories.get(i).getName();
				}
				territoriesList.setListData(territoryNames);
				
			}
		});
        
        /* Continents selection Action --> Display Territories - End */
        
        /* Territory Selection Action --> Display Adjacent Territories - Start */
        
        
        /* Territory Selection Action --> Display Adjacent Territories - End */

    }
}



