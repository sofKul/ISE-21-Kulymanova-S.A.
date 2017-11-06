import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JList;

public class Main {

	private JFrame frame;
	Aerodrome aerodrome;

	private Frame btnColor;
	private Frame btnSelectFigtherColor;
	private JTextField numPlace;
	JPanel panel;
	private String[] elements = new String[6];
	JList listLevels;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		aerodrome = new Aerodrome(5);

		initialize();
		for (int i = 0; i < 5; i++) {
			elements[i] = "������� " + (i+1);
		}

		listLevels.setSelectedIndex(aerodrome.getCurrentLevel());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1080, 559);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

			panel = new Panel(aerodrome);
			panel.setBounds(10, 11, 854, 499);
			frame.getContentPane().add(panel);
		

		JButton btnSetPlane = new JButton("Set Plane");
		btnSetPlane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Color colorDialog = JColorChooser.showDialog(null, "JColorChooser Sample", null);
				if (colorDialog != null) {
					ITransport plane = new War(1000, 100, 30, 30, colorDialog);
					int place = aerodrome.putPlaneInAerodrome(plane);
					panel.repaint();
					JOptionPane.showMessageDialog(null, "���� ����� " + place);
				}

			}
		});
		btnSetPlane.setBounds(939, 293, 115, 23);
		frame.getContentPane().add(btnSetPlane);

		JButton btnSetFigther = new JButton("Set Figther");
		btnSetFigther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color colorDialog1 = JColorChooser.showDialog(null, "JColorChooser Sample", null);
				if (colorDialog1 != null) {
					Color colorDialog = JColorChooser.showDialog(null, "JColorChooser Sample", null);
					if (colorDialog != null) {
						ITransport plane = new Figther(1000, 100, 30, 30, colorDialog1, true, true, colorDialog);
						int place = aerodrome.putPlaneInAerodrome(plane);
						panel.repaint();
						JOptionPane.showMessageDialog(null, "���� ����� " + place);
					}
				}

			}
		});
		btnSetFigther.setBounds(939, 330, 115, 23);
		frame.getContentPane().add(btnSetFigther);

		JPanel panelTake = new JPanel();
		panelTake.setBounds(901, 11, 153, 170);
		frame.getContentPane().add(panelTake);

		JButton btnTake = new JButton("Take");
		btnTake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (checkPlace(numPlace.getText())) {
					ITransport plane = aerodrome.getPlaneInAerodrome(Integer.parseInt(numPlace.getText()));
					Graphics gr = panelTake.getGraphics();
					gr.clearRect(0, 0, panelTake.getWidth(), panelTake.getHeight());
					plane.setPosition(5, 5);
					plane.draw(gr);
					panel.repaint();
				}

			}
		});
		btnTake.setBounds(973, 233, 81, 23);
		frame.getContentPane().add(btnTake);

		JLabel lblNewLabel = new JLabel("Place");
		lblNewLabel.setBounds(912, 205, 46, 14);
		frame.getContentPane().add(lblNewLabel);

		numPlace = new JTextField();
		numPlace.setBounds(968, 202, 86, 20);
		frame.getContentPane().add(numPlace);
		numPlace.setColumns(10);

		listLevels = new JList(elements);
		listLevels.setBounds(891, 373, 153, 111);
		frame.getContentPane().add(listLevels);

		JButton btnLevelDown = new JButton("<<");
		btnLevelDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aerodrome.levelDown();
				listLevels.setSelectedIndex(aerodrome.getCurrentLevel());
				panel.repaint();
			}
		});
		btnLevelDown.setBounds(869, 495, 89, 23);
		frame.getContentPane().add(btnLevelDown);

		JButton btnLevelUp = new JButton(">>");
		btnLevelUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aerodrome.levelUp();
				listLevels.setSelectedIndex(aerodrome.getCurrentLevel());
				panel.repaint();
			}
		});
		btnLevelUp.setBounds(973, 495, 89, 23);
		frame.getContentPane().add(btnLevelUp);

	}

	private boolean checkPlace(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}

		if (Integer.parseInt(str) > 20)
			return false;
		return true;
	}
}
