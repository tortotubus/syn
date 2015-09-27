package io.github.conorolive;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JTextField;

public class Jankare {

	private JFrame frmJankare;
	private ImageSegments image;
	private int k = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
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
		
		Box controlsVerticalBox = Box.createVerticalBox();
		frmJankare.getContentPane().add(controlsVerticalBox, BorderLayout.SOUTH);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		controlsVerticalBox.add(verticalStrut_2);
		
		Box optionsHorizontalBox = Box.createHorizontalBox();
		controlsVerticalBox.add(optionsHorizontalBox);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		optionsHorizontalBox.add(horizontalStrut);
		
		JSpinner kSpinner = new JSpinner();
		kSpinner.setModel(new SpinnerNumberModel(new Integer(k), new Integer(k), null, new Integer(k)));
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
		GridBagConstraints textHorizontalBoxConstraints = new GridBagConstraints();
		textHorizontalBoxConstraints.fill = GridBagConstraints.HORIZONTAL;
		controlsVerticalBox.add(textHorizontalBox, textHorizontalBoxConstraints);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		textHorizontalBox.add(horizontalStrut_5);
		
		JPanel textPanelContainer = new JPanel();
		textPanelContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
		textPanelContainer.setDoubleBuffered(false);
		textPanelContainer.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		textHorizontalBox.add(textPanelContainer);
		textPanelContainer.setLayout(new BoxLayout(textPanelContainer, BoxLayout.X_AXIS));
		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setMargin(new Insets(4, 4, 4, 4));
		textArea.setLineWrap(true);
		textArea.setAlignmentX(0.0f);
		textPanelContainer.add(textArea);
		

		JButton segmentButton = new JButton("Segment");
		segmentButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		segmentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Jankare.this.image == null) {
					JOptionPane.showMessageDialog(frmJankare, "An image file must be chosen first.");
				} else {
					Jankare.this.image.colorSegmentation(k);
					textArea.setText(Arrays.toString(image.getColorsAsHex()));
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
		
		ScalingPane imagePanel = new ScalingPane();
		//JPanel imagePanel = new JPanel();
		imagePanel.setBackground(Color.lightGray);
		frmJankare.getContentPane().add(imagePanel);
		imagePanel.setLayout(new BorderLayout());
		
		JButton fileButton = new JButton("Choose File");
		fileButton.setAlignmentY(1.0f);
		fileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
			    
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG, or GIF Images", "jpg", "gif", "png");
			    chooser.setFileFilter(filter);
			    
			    int returnVal = chooser.showOpenDialog(frmJankare);
			    
			    if(returnVal == JFileChooser.APPROVE_OPTION) {			       
			       BufferedImage imageResource = null;
			       
			       try {
			    	   imageResource = ImageIO.read(chooser.getSelectedFile());
			       } catch (IOException exception) {
						JOptionPane.showMessageDialog(frmJankare, exception.toString());
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
		
	}
	
}
