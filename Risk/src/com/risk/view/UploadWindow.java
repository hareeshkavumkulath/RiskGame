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
 * @author Anqi Wang
 * @version 1.0
 * 
 * @date 10-07-2018
 * @modifiedDate 10-08-2018
 * @modifiedBy Jingya Pan
 * @modifiedBy Anqi Wang
 * @modifiedBy Hareesh Kavumkulath
 *
 */
public class UploadWindow {


    private JFrame frame;
    private JTextField fileName;
    private File file;
    private JButton uploadButton;
    private MapMessage mapMessage;
    private JTextField mapName;
    private boolean mapStatus = false;
    
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
		
		// Continents Information
		JLabel labelContinents = new JLabel("Continents");
		labelContinents.setBounds(45, 108, 115, 20);
		frame.getContentPane().add(labelContinents);
		
		JList<String> continentsJList = new JList<String>();
		continentsJList.setBorder(new LineBorder(Color.BLUE));
		continentsJList.setBounds(46, 144, 201, 349);
		continentsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		frame.getContentPane().add(continentsJList);
        
		// Territories Information
		JLabel labelTerritories = new JLabel("Territories");
		labelTerritories.setBounds(277, 108, 115, 20);
		frame.getContentPane().add(labelTerritories);
		
		JList<String> territoriesJList = new JList<String>();
		territoriesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		territoriesJList.setBorder(new LineBorder(Color.BLUE));
		territoriesJList.setBounds(277, 144, 211, 349);
		frame.getContentPane().add(territoriesJList);		
		
		// Adjacent Territories Information
		JLabel labelAdjacentTerritories = new JLabel("Adjacent Territories");
		labelAdjacentTerritories.setBounds(524, 108, 201, 20);
		frame.getContentPane().add(labelAdjacentTerritories);		
		
		JList<String> adjTerritoriesJList = new JList<String>();
		adjTerritoriesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		adjTerritoriesJList.setBorder(new LineBorder(Color.BLUE));
		adjTerritoriesJList.setBounds(524, 144, 211, 349);
		frame.getContentPane().add(adjTerritoriesJList);
		
		// Remove Continent and Remove Territory Buttons
		JButton btnRemoveContinent = new JButton("Remove Continent");
		btnRemoveContinent.setBounds(46, 516, 201, 29);
		frame.getContentPane().add(btnRemoveContinent);
		
		JButton btnRemoveTerritory = new JButton("Remove Territory");
		btnRemoveTerritory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemoveTerritory.setBounds(277, 516, 211, 29);
		frame.getContentPane().add(btnRemoveTerritory);
		
		//Save Map fields
		mapName = new JTextField();
		mapName.setToolTipText("Enter name for Map");
		mapName.setColumns(10);
		mapName.setBounds(527, 565, 281, 26);
		frame.getContentPane().add(mapName);
		
		JButton btnSaveMap = new JButton("Save Map");
		btnSaveMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(mapStatus) {
					
				}else {
					JOptionPane.showMessageDialog(frame, "Please validate the Map.");
				}				
			}
		});
		btnSaveMap.setBounds(843, 564, 115, 29);
		frame.getContentPane().add(btnSaveMap);
		
		JTextPane errorMessage = new JTextPane();
		errorMessage.setBounds(46, 148, 300, 500);
		errorMessage.setEditable(true);
		
		JScrollPane messageScrollPane = new JScrollPane(errorMessage);
		messageScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		messageScrollPane.setBounds(761, 144, 251, 349);
		frame.getContentPane().add(messageScrollPane);
		
		JButton validateAgain = new JButton("Validate Again");
		validateAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MapController controller = new MapController();
				System.out.println("Size:"+mapMessage.getTerritories().size());
				boolean status = controller.validateMap(mapMessage.getTerritories());
				System.out.println("Status:"+status);
				if(status) {
					JOptionPane.showMessageDialog(frame, "Map is valid. You can save the Map.");
					mapStatus = true;
				}else {
					JOptionPane.showMessageDialog(frame, "Map is invalid.");
					mapStatus = false;
				}
			}
		});
		validateAgain.setBounds(287, 561, 191, 29);
		frame.getContentPane().add(validateAgain);
		
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
				if(file == null) {
					 String message = "File Chosen was cancelled" + ". Please select a \".map\" file";
	                    JOptionPane.showMessageDialog(new JFrame(), message, "No File Selected",
	                            JOptionPane.ERROR_MESSAGE);
	
				}
				MapController mapController = new MapController();
				mapMessage = (MapMessage)mapController.processFile(file);
				System.out.println(mapMessage.isValidMap());
				if(mapMessage.isValidMap()) {
					errorMessage.setForeground(Color.GREEN);
					errorMessage.setText("Valid Map");
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
					errorMessage.setForeground(Color.RED);
					errorMessage.setText(message);
					//continentInfo.setText(message);
				}				
			}
		});
        
        /* Continents selection Action --> Display Territories - Start */
        continentsJList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				String[] adjTerritoryNames = {};
				adjTerritoriesJList.setListData(adjTerritoryNames);
				JList list = (JList) listSelectionEvent.getSource();
				int selections[] = list.getSelectedIndices();
				ArrayList<Territory> territories = mapMessage.getContinents().get(selections[0]).getTerritories();
				String[] territoryNames = new String[territories.size()];
				for(int i=0;i<territories.size();i++) {
					territoryNames[i] = territories.get(i).getName();
				}
				territoriesJList.setListData(territoryNames);
				
			}
		});        
        /* Continents selection Action --> Display Territories - End */
        
        /* Territory Selection Action --> Display Adjacent Territories - Start */
        territoriesJList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				JList list = (JList) listSelectionEvent.getSource();
				String selection = (String) list.getSelectedValue();
				ArrayList<Territory> territories = mapMessage.getTerritories();
				Territory selectedTerritory = null;
				for(int i=0;i<territories.size();i++) {
					if(territories.get(i).getName().equals(selection)) {
						selectedTerritory = territories.get(i);
					}
				}
				try {
					String[] adjTerritoryNames = new String[selectedTerritory.getAdjacentTerritories().size()];
					for(int i=0;i<selectedTerritory.getAdjacentTerritories().size();i++) {
						adjTerritoryNames[i] = selectedTerritory.getAdjacentTerritories().get(i);
					}
					adjTerritoriesJList.setListData(adjTerritoryNames);
				} catch(Exception e) {
					System.out.println(e.toString());
				}
			}
		});
        /* Territory Selection Action --> Display Adjacent Territories - End */
        
        /* Remove Continent Button Action - Start */
        btnRemoveContinent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] territoryNames = {};
				territoriesJList.setListData(territoryNames);
				if(continentsJList.getSelectedValue() != null) {
					MapController mapController = new MapController(new StringBuffer(""), mapMessage.getContinents(), mapMessage.getTerritories(), true, new StringBuffer(""));
					mapMessage = (MapMessage)mapController.removeContinent(continentsJList.getSelectedValue());
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
						mapStatus = false;
					}
				}else {
					System.out.println("Select a continent");
				}
			}
		});
        /* Remove Continent Button Action - End */       
        
        /* Remove Territory Button Action - Start */
        btnRemoveTerritory.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(territoriesJList.getSelectedValue() != null) {
					MapController mapController = new MapController(new StringBuffer(""), mapMessage.getContinents(), mapMessage.getTerritories(), true, new StringBuffer(""));
					mapMessage = (MapMessage)mapController.removeTerritory(territoriesJList.getSelectedValue());
					if(mapMessage.isValidMap()) {
						String continentName = continentsJList.getSelectedValue();
						Continent continent = new Continent();
						for(int i=0;i<mapMessage.getContinents().size();i++) {
							if(mapMessage.getContinents().get(i).getName().equals(continentName)) {
								continent = mapMessage.getContinents().get(i);
							}
						}
						ArrayList<Territory> territories = continent.getTerritories();
						String[] territoryNames = new String[territories.size()];
						for(int i=0;i<territories.size();i++) {
							territoryNames[i] = territories.get(i).getName();
						}
						territoriesJList.setListData(territoryNames);
						//if the territory is empty, so the according continents need to be removed.
						if(territories.size()==0) {
							mapMessage = (MapMessage)mapController.removeContinent(continentsJList.getSelectedValue());
							ArrayList<Continent> continents = mapMessage.getContinents();
							String[] continentNames = new String[continents.size()];
							for(int i = 0; i < continents.size(); i++) {
								Continent thisContinent = (Continent) continents.get(i);
								continentNames[i] = thisContinent.getName(); 
							}
							continentsJList.setListData(continentNames);
						}
						mapStatus = false;
					}
					String[] adjTerritoryNames = {};
					adjTerritoriesJList.setListData(adjTerritoryNames);
				}else {
					System.out.println("Select a territory");
				}
			}
		});
        /* Remove Territory Button Action - End */  
        
    }
}



