package io.github.conorolive;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.Box;
import java.awt.GridBagLayout;
import javax.swing.JSpinner;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.BoxLayout;

public class Jankare {

	private JFrame frmJankare;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jankare window = new Jankare();
					window.frmJankare.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Jankare() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJankare = new JFrame();
		frmJankare.setResizable(false);
		frmJankare.setTitle("Jankare");
		frmJankare.setBounds(100, 100, 551, 450);
		frmJankare.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJankare.getContentPane().setLayout(new BorderLayout(0, 0));
		
		Box verticalBox = Box.createVerticalBox();
		frmJankare.getContentPane().add(verticalBox, BorderLayout.SOUTH);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_2);
		
		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);
		
		JSpinner spinner = new JSpinner();
		spinner.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		spinner.setAlignmentX(Component.LEFT_ALIGNMENT);
		spinner.addChangeListener(new ChangeListener() {
			
		});
		horizontalBox.add(spinner);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_2);
		
		JButton button_1 = new JButton("Choose File");
		button_1.setAlignmentY(1.0f);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
			    
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG, or GIF Images", "jpg", "gif", "png");
			    chooser.setFileFilter(filter);
			    
			    int returnVal = chooser.showOpenDialog(frmJankare);
			    
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       ImageSegments image = new ImageSegments(chooser.getSelectedFile());
			    }
			}
		});
		horizontalBox.add(button_1);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_3);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_1);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_5);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		horizontalBox_1.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JTextArea txtrColors = new JTextArea();
		txtrColors.setMargin(new Insets(4, 4, 4, 4));
		txtrColors.setWrapStyleWord(true);
		txtrColors.setLineWrap(true);
		panel_1.add(txtrColors);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_4);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		frmJankare.getContentPane().add(panel, BorderLayout.CENTER);
	}

}
