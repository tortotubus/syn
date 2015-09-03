package start;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.SpringLayout;
import javax.swing.JSeparator;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Component;
import javax.swing.Box;

public class Jankare extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jankare frame = new Jankare();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Jankare() {
		setTitle("Jankare");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.LIGHT_GRAY));
		sl_contentPane.putConstraint(SpringLayout.NORTH, panel, 5, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, panel, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panel, 290, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panel, 459, SpringLayout.WEST, contentPane);
		contentPane.add(panel);
		
		JProgressBar progressBar = new JProgressBar();
		sl_contentPane.putConstraint(SpringLayout.NORTH, progressBar, 6, SpringLayout.SOUTH, panel);
		sl_contentPane.putConstraint(SpringLayout.WEST, progressBar, -459, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, progressBar, -5, SpringLayout.EAST, contentPane);
		contentPane.add(progressBar);
		
		JButton btnChooseImage = new JButton("Choose Image");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnChooseImage, 6, SpringLayout.SOUTH, progressBar);
		contentPane.add(btnChooseImage);
		
		JTextPane textPane = new JTextPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textPane, 6, SpringLayout.SOUTH, progressBar);
		sl_contentPane.putConstraint(SpringLayout.WEST, textPane, 125, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textPane, -5, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textPane, -5, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnChooseImage, -120, SpringLayout.WEST, textPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnChooseImage, -19, SpringLayout.WEST, textPane);
		contentPane.add(textPane);
	}
}
