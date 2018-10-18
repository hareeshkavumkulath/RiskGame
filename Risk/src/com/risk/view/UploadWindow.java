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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Map Upload Window for the user to upload .map file. It validates and prints the map.
 * 
 * @author Anqi Wang
 */
public class UploadWindow {

	@SuppressWarnings("javadoc") 
    private JFrame frame;
	@SuppressWarnings("javadoc")
    private JTextField fileName;
	@SuppressWarnings("javadoc")
    private File file;
	@SuppressWarnings("javadoc")
    private JButton uploadButton;
	@SuppressWarnings("javadoc")
    private MapMessage mapMessage;
	@SuppressWarnings("javadoc")
    private JTextField mapName;
	@SuppressWarnings("javadoc")
    private boolean mapStatus = false;
	@SuppressWarnings("javadoc")
    private JTextPane mapTextPane;
	@SuppressWarnings("javadoc")
    private String mapStringAfterValidation;
    
    /**
     * Launch the application.
     */
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
    /**
     * The method is used to initialize
     */
    public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1735, 1075);
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
		
		//Save Map fields
		mapName = new JTextField();
		mapName.setToolTipText("Enter name for Map");
		mapName.setColumns(10);
		mapName.setBounds(1190, 898, 281, 26);
		frame.getContentPane().add(mapName);
		
		JButton btnSaveMap = new JButton("Save Map");
		btnSaveMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!mapName.getText().equals("")) {
					if(mapStringAfterValidation.equals(mapTextPane.getText())) {
						if(mapStatus) {
							File fileName = new File(".\\Maps\\" + mapName.getText() + ".txt");
							try {
								writeFile(fileName, mapTextPane.getText());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							frame.dispose();
						}else {
							JOptionPane.showMessageDialog(frame, "Please validate the Map.");
						}				
					}
					else {
						JOptionPane.showMessageDialog(frame, "Please validate the Map.");
					}
				}else {
					JOptionPane.showMessageDialog(frame, "Please enter name for the map.");
				}
			}
		});
		btnSaveMap.setBounds(1500, 897, 115, 29);
		frame.getContentPane().add(btnSaveMap);
		
		JTextPane errorMessage = new JTextPane();
		errorMessage.setBounds(46, 148, 300, 500);
		errorMessage.setEditable(true);
		
		JScrollPane messageScrollPane = new JScrollPane(errorMessage);
		messageScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		messageScrollPane.setBounds(46, 509, 689, 415);
		frame.getContentPane().add(messageScrollPane);
		
		JButton validateAgain = new JButton("Validate Again");
		validateAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MapController mapController = new MapController();
				mapMessage = (MapMessage)mapController.validateMap(mapTextPane.getText());
				populateEditField(mapMessage);
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
					mapStringAfterValidation = mapTextPane.getText();
				}else {
					String message = mapMessage.getMessage().toString();
					errorMessage.setForeground(Color.RED);
					errorMessage.setText(message);
				}
			}
		});
		validateAgain.setBounds(918, 897, 191, 29);
		frame.getContentPane().add(validateAgain);
		
		JScrollPane mapScrollPane = new JScrollPane((Component) null);
		mapScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		mapScrollPane.setBounds(877, 46, 803, 811);
		frame.getContentPane().add(mapScrollPane);
		
		mapTextPane = new JTextPane();
		mapTextPane.setText("[Continents]\n" +"<ContinentName1>=<ArmyNumber1>\n"+ "<ContinentName2>=<ArmyNumber2>\n" + "<ContinentName3>=<ArmyNumber3>\n" +
							"...\n"+"...\n"+"...\n"+"\n"+ "[Territories]\n" + "<TerritoryName1>,0,0,<ContinentName1>,<adjacentTerritory1>,<adjacentTerritory2><adjacentTerritory3>\n"+
							"<TerritoryName2>,0,0,<ContinentName2>,<adjacentTerritory1>,<adjacentTerritory2><adjacentTerritory3>\n"+
							"<TerritoryName3>,0,0,<ContinentName3>,<adjacentTerritory1>,<adjacentTerritory2><adjacentTerritory3>\n"+"...\n"+"...\n"+"...\n"
							
);
		mapTextPane.setEditable(true);
		mapScrollPane.setViewportView(mapTextPane);
		
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
                } 
            }
        });
        
        uploadButton.addActionListener(new ActionListener() {
       	@Override
			public void actionPerformed(ActionEvent e) {
				if(file == null) {
					 String message = "Please select an upload file.";
	                    JOptionPane.showMessageDialog(new JFrame(), message, "No File Selected",
	                            JOptionPane.ERROR_MESSAGE);
	
				}
				MapController mapController = new MapController();
				mapMessage = (MapMessage)mapController.processFile(file);
				populateEditField(mapMessage);
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
					mapStringAfterValidation = mapTextPane.getText();
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
        /* Remove Territory Button Action - End */  
        
    }
    /**
     * The method is used to read form the file and show the text in mapTextPane
     * 
     * @param tempMapMessage mapMessage
     */
	protected void populateEditField(MapMessage tempMapMessage) {
		if(tempMapMessage.isValidMap) {
			ArrayList<Continent> continents = new ArrayList<Continent>();
			ArrayList<Territory> territories = new ArrayList<Territory>();
			continents = tempMapMessage.getContinents();
			territories = tempMapMessage.getTerritories();
			StringBuffer mapText = new StringBuffer("");
			mapText = mapText.append("[Continents]\n");
			for(int i=0;i<continents.size();i++) {
				mapText = mapText.append(continents.get(i).getName()+ "=" +continents.get(i).getNumberOfArmies());
				mapText = mapText.append("\n");
			}
			mapText.append("\n");
			mapText.append("\n");
			mapText = mapText.append("[Territories]\n");
			
			for(int i=0;i<territories.size();i++) {
				mapText = mapText.append(territories.get(i).getName() + ",0,0," + territories.get(i).getContinent());
				if(territories.get(i).getAdjacentTerritories().size() > 0) {
					mapText = mapText.append(",");
					for(int j=0;j<territories.get(i).getAdjacentTerritories().size();j++) {
						mapText = mapText.append(territories.get(i).getAdjacentTerritories().get(j));
						if(j != territories.get(i).getAdjacentTerritories().size() - 1) {
							mapText = mapText.append(",");
						}
					}
				}
				mapText = mapText.append("\n");
			}
			mapText = mapText.append("\n");
			mapTextPane.setText(mapText.toString());
			mapStatus = true;
		}else {
			
		}
	}
	/**
	 * The method is used to write the file from the text given in the mapTextPane
	 * 
	 * @param fileName fileName we created
	 * @param text text from mapTextPane
	 * @throws IOException
	 */
	public void writeFile(File fileName, String text) throws IOException
	{
		BufferedWriter out = new BufferedWriter(new FileWriter(fileName)); 
		out.write(text);
		out.close();
	}
	
}



