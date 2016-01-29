package io.github.conorolive;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JSpinner;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

public class Jankare {

	private JFrame frmJankare;
	private ImageSegments image;
	private int k = 1;
	private JTextField textField;
	private JTable table;

	// Launch the application.

	public static void main(String[] args) {
	   
		try {
	    	
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
	    } catch (ClassNotFoundException | InstantiationException |
	    		IllegalAccessException | UnsupportedLookAndFeelException e1) {
			
	    	e1.printStackTrace();
		
	    }
		
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
		frmJankare.setMinimumSize(new Dimension(550, 450));
		frmJankare.setTitle("Jankare");
		frmJankare.setBounds(100, 100, 550, 450);
		frmJankare.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJankare.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmJankare.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel mainPanel = new JPanel();
		tabbedPane.addTab("Image", null, mainPanel, null);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		Box controlsVerticalBox = Box.createVerticalBox();
		mainPanel.add(controlsVerticalBox, BorderLayout.SOUTH);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		controlsVerticalBox.add(verticalStrut_2);
		
		Box optionsHorizontalBox = Box.createHorizontalBox();
		controlsVerticalBox.add(optionsHorizontalBox);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		optionsHorizontalBox.add(horizontalStrut);
		
		JSpinner kSpinner = new JSpinner();
		
		kSpinner.setModel(new SpinnerNumberModel(new Integer(k), new Integer(k),
				null, new Integer(k)));
		
		kSpinner.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		kSpinner.setAlignmentX(Component.LEFT_ALIGNMENT);
		kSpinner.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				
				JSpinner spinner = (JSpinner)(e.getSource());
				Jankare.this.k = (Integer)spinner.getValue();
				
			}
			
		});
		
		optionsHorizontalBox.add(kSpinner);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		optionsHorizontalBox.add(horizontalStrut_2);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		controlsVerticalBox.add(verticalStrut_1);
		
		Box textHorizontalBox = Box.createHorizontalBox();
		
		GridBagConstraints textHorizontalBoxConstraints = 
				new GridBagConstraints();
		
		textHorizontalBoxConstraints.fill = GridBagConstraints.HORIZONTAL;
		
		controlsVerticalBox.add(textHorizontalBox, 
				textHorizontalBoxConstraints);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		textHorizontalBox.add(horizontalStrut_5);
		
		textField = new JTextField();
		textField.setMargin(new Insets(4, 4, 4, 4));
		textHorizontalBox.add(textField);
		textField.setColumns(10);
		

		JButton segmentButton = new JButton("Segment");
		segmentButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		segmentButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if (Jankare.this.image == null) {
					
					JOptionPane.showMessageDialog(frmJankare, "An image file "
							+ "must be chosen first.");
					
				} else {
					
					Jankare.this.image.colorSegmentation(k);
					textField.setText(Arrays.toString(image.getColorsAsHex()));
					
				}
				
			}
			
		});
		
		optionsHorizontalBox.add(segmentButton);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		optionsHorizontalBox.add(horizontalStrut_1);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		textHorizontalBox.add(horizontalStrut_4);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		controlsVerticalBox.add(verticalStrut);
		
		final ScalingPane imagePanel = new ScalingPane();
		mainPanel.add(imagePanel, BorderLayout.CENTER);
		//JPanel imagePanel = new JPanel();
		imagePanel.setBackground(Color.lightGray);
		imagePanel.setLayout(new BorderLayout());
		
		JButton fileButton = new JButton("Choose File");
		fileButton.setAlignmentY(1.0f);
		fileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
			    
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"JPG, PNG, or GIF Images", "jpg", "gif", "png");
				
			    chooser.setFileFilter(filter);
			    
			    int returnVal = chooser.showOpenDialog(frmJankare);
			    
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	
			       BufferedImage imageResource = null;
			       
			       try {
			    	   
			    	   imageResource = ImageIO.read(chooser.getSelectedFile());
			    	   
			       } catch (IOException exception) {
			    	   
						JOptionPane.showMessageDialog(frmJankare, 
								exception.toString());
						
			       }
			       
			       imagePanel.setImage(imageResource);
			       imagePanel.repaint();
			       
			       image = new ImageSegments(chooser.getSelectedFile());

			    }
			    
			}
			
		});
		
		optionsHorizontalBox.add(fileButton);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		optionsHorizontalBox.add(horizontalStrut_3);
		
		JPanel exportPanel = new JPanel();
		tabbedPane.addTab("Export", null, exportPanel, null);
		exportPanel.setLayout(new BoxLayout(exportPanel, BoxLayout.X_AXIS));
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				
			new Object[][] {
				
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				
			},
			
			new String[] {
					
				"Name Value", "Color Value"
			
			}
			
		));
		
		JScrollPane scrollPane = new JScrollPane(table);
		exportPanel.add(scrollPane);
		
		Box verticalBox = Box.createVerticalBox();
		exportPanel.add(verticalBox);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_3);
		
		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_7);
		
		JButton btnNewButton = new JButton("Export to File");
		btnNewButton.setAlignmentY(Component.TOP_ALIGNMENT);
		horizontalBox.add(btnNewButton);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_6);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_4);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);
		
		Component horizontalStrut_8 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_8);
		
		JButton btnCopyToClipboard = new JButton("Copy to Clipboard");
		btnCopyToClipboard.setAlignmentY(0.0f);
		horizontalBox_1.add(btnCopyToClipboard);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_9);
		
		Component verticalStrut_5 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_5);
		
	}
	
}
