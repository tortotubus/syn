package io.github.conorolive;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.Box;
import javax.swing.JSpinner;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.BoxLayout;
import javax.swing.SpinnerNumberModel;

public class Jankare {

	private JFrame frmJankare;
	private ImageSegments image;
	private int k;

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
		
		JSpinner kSpinner = new JSpinner();
		kSpinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		kSpinner.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		kSpinner.setAlignmentX(Component.LEFT_ALIGNMENT);
		kSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSpinner spinner = (JSpinner)(e.getSource());
				Jankare.this.k = (Integer)spinner.getValue();
			}
		});
		horizontalBox.add(kSpinner);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_2);
		
		JButton fileButton = new JButton("Choose File");
		fileButton.setAlignmentY(1.0f);
		fileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
			    
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG, or GIF Images", "jpg", "gif", "png");
			    chooser.setFileFilter(filter);
			    
			    int returnVal = chooser.showOpenDialog(frmJankare);
			    
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       image = new ImageSegments(chooser.getSelectedFile());
			    }
			}
		});
		horizontalBox.add(fileButton);
		
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
		
		JTextArea colorText = new JTextArea();
		colorText.setMargin(new Insets(4, 4, 4, 4));
		colorText.setWrapStyleWord(true);
		colorText.setLineWrap(true);
		panel_1.add(colorText);
		

		JButton segmentButton = new JButton("Segment");
		segmentButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		segmentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Jankare.this.image == null) {
					JOptionPane.showMessageDialog(frmJankare, "An image file must be chosen first.");
				} else {
					Jankare.this.image.colorSegmentation(k);
					colorText.setText(Arrays.toString(image.getColorsAsHex()));
				}
			}
		});
		horizontalBox.add(segmentButton);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_1);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_4);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		frmJankare.getContentPane().add(panel, BorderLayout.CENTER);
	}

}
