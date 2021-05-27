package theater.view.pages;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import theater.view.MainWindow;

//page for login / registration
public class HomePage extends JPanel {

	private static final long serialVersionUID = -3858888796508059429L;
	private Image background;

	private Dimension btnSize = new Dimension(200, 60);
	private Font btnFont = new Font("arial", Font.PLAIN, 32);

	public HomePage() {
		// load image
		File f = null;
		f = new File("./images/home.jpg");
		try {
			background = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setLayout(new GridBagLayout());

		displayButtons();
	}

	private void displayButtons() {
		GridBagConstraints gc = new GridBagConstraints();
		gc.insets = new Insets(0, 0, 100, 0);
		gc.gridy = 0;
		JLabel title = new JLabel("POZORISTE");
		title.setFont(new Font("arial", Font.BOLD, 50));
		title.setForeground(Color.WHITE);
		add(title, gc);
		gc.insets = new Insets(10, 0, 0, 0);

		JButton loginBtn = new JButton("Prijava");
		loginBtn.setFont(btnFont);
		loginBtn.setFocusPainted(false);
		loginBtn.setPreferredSize(btnSize);
		loginBtn.setBackground(Color.WHITE);
		
		gc.gridy = 1;

		add(loginBtn, gc);

		JButton registerBtn = new JButton("Registracija");
		registerBtn.setFont(new Font("arial", Font.PLAIN, 32));
		registerBtn.setBackground(Color.WHITE);
		registerBtn.setPreferredSize(btnSize);
		registerBtn.setFocusPainted(false);
		
		gc.gridy = 2;
		gc.insets = new Insets(20, 0, 0, 0);
		add(registerBtn, gc);
		revalidate();
		repaint();
	}

	

	// background picture
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = getSize();
		g.drawImage(background.getScaledInstance(d.width, d.height, Image.SCALE_FAST), 0, 0, null);
	}

}
