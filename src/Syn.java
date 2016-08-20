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

import io.github.conorolive.gui.ScalingImagePanel;
import io.github.conorolive.syn.ImageSegmentor;

public class Syn {

	private JFrame frmSyn;
	private ImageSegmentor image;
	private int k = 1;
	private JTextField textField;

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
					Syn window = new Syn();
					window.frmSyn.setVisible(true);

				} catch (Exception e) {

					e.printStackTrace();

				}

			}

		});

	}

	/**
	 * Create the application.
	 */

	public Syn() {

		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {

		frmSyn = new JFrame();
		frmSyn.setMinimumSize(new Dimension(550, 450));
		frmSyn.setTitle("Syn");
		frmSyn.setBounds(100, 100, 550, 450);
		frmSyn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSyn.getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmSyn.getContentPane().add(tabbedPane, BorderLayout.CENTER);

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
				Syn.this.k = (Integer)spinner.getValue();

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

				if (Syn.this.image == null) {

					JOptionPane.showMessageDialog(frmSyn, "An image file "
							+ "must be chosen first.");

				} else {

					Syn.this.image.segmentPixels(k);
					textField.setText(image.getColorsAsHex().toString());

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

		final ScalingImagePanel imagePanel = new ScalingImagePanel();
		mainPanel.add(imagePanel, BorderLayout.CENTER);

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

			    int returnVal = chooser.showOpenDialog(frmSyn);

			    if(returnVal == JFileChooser.APPROVE_OPTION) {

			       BufferedImage imageResource = null;

			       try {

			    	   imageResource = ImageIO.read(chooser.getSelectedFile());

				       imagePanel.setImage(imageResource);
				       imagePanel.repaint();

			    	   image = new ImageSegmentor(imageResource);


			       } catch (IOException exception) {

						JOptionPane.showMessageDialog(frmSyn,
								exception.toString());

			       }

			    }

			}

		});

		optionsHorizontalBox.add(fileButton);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		optionsHorizontalBox.add(horizontalStrut_3);


	}

}
