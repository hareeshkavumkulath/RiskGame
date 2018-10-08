package com.risk.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
        uploadButton.setBounds(376, 101, 115, 29);
        frame.getContentPane().add(uploadButton);

        JTextPane fileInfo = new JTextPane();
        fileInfo.setBounds(46, 148, 300, 500);
        
		JScrollPane scrollpane = new JScrollPane(fileInfo);
		scrollpane.setBounds(40, 100, 300, 500);
		scrollpane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.add(scrollpane);

        JPanel panel = new JPanel();
        panel.setBounds(809, 548, -784, -544);
        frame.getContentPane().add(panel);
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


    }
}



