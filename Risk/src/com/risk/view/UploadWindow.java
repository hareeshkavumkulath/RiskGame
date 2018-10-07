package swingInterface;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class UploadWindow {


    private JFrame frame;
    private JTextField fileName;
    private File file;
    private JButton uploadButton;
    private StringBuffer fileContent = new StringBuffer();

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
        fileInfo.setBounds(46, 148, 1000, 2000);
        frame.getContentPane().add(fileInfo);

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



