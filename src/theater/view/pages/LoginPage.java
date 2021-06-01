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

import theater.controller.UserController;
import theater.view.MainWindow;

public class LoginPage extends JPanel {

	private Dimension btnSize = new Dimension(200, 60);
	private Font btnFont = new Font("arial", Font.PLAIN, 32);
	private Image background;

	public LoginPage() {
		// load image
		File f = null;
		f = new File("./images/home.jpg");
		try {
			background = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setLayout(new GridBagLayout());
		showLoginForm();
	}

	private void showLoginForm() {
		GridBagConstraints gc = new GridBagConstraints();
		gc.insets = new Insets(0, 0, 100, 0);
		gc.gridy = 0;
		JLabel title = new JLabel("POZORISTE");
		title.setFont(new Font("arial", Font.BOLD, 50));
		title.setForeground(Color.WHITE);
		add(title, gc);

		JLabel usernameLabel = new JLabel("Korisnicko ime:");
		JTextField usernameField = new JTextField();
		usernameField.setPreferredSize(new Dimension(150, 30));
		usernameLabel.setForeground(Color.WHITE);

		JLabel passLabel = new JLabel("Lozinka :");
		passLabel.setForeground(Color.WHITE);
		JPasswordField passField = new JPasswordField();
		passField.setPreferredSize(new Dimension(150, 30));

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(usernameLabel, c);

		c.gridx = 1;
		panel.add(usernameField, c);

		passLabel.setFont(new Font("arial", Font.PLAIN, 32));
		usernameLabel.setFont(new Font("arial", Font.PLAIN, 32));

		c.gridx = 0;
		c.gridy = 1;

		panel.add(passLabel, c);
		c.gridx = 1;
		c.gridy = 1;

		panel.add(passField, c);
		JLabel error = new JLabel("");
		error.setFont(new Font("arial", Font.BOLD, 18));
		error.setForeground(Color.cyan);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 3;

		panel.add(error, c);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;

		JButton loginBtn = new JButton("Prijava");
		loginBtn.setFont(btnFont);
		loginBtn.setBackground(Color.WHITE);

		loginBtn.setSize(btnSize);
		panel.add(loginBtn, c);

		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserController uc = new UserController();
				boolean res = uc.logIn(usernameField.getText(), new String(passField.getPassword()));
				if (!res) {
					error.setText("Pogresni podaci");
				} else {
					MainWindow.getInstance().showPage(new ShowsPage());
				}
			}
		});

		JButton backBtn = new JButton("Nazad");
		backBtn.setFont(btnFont);
		backBtn.setSize(btnSize);
		backBtn.setBackground(Color.WHITE);

		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.getInstance().showPage(new HomePage());
			}
		});
		c.gridx = 1;
		c.gridy = 2;

		panel.add(backBtn, c);

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		add(panel, c);
		panel.setBackground(new Color(0, 0, 0, 0));
		revalidate();
		repaint();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = getSize();
		g.drawImage(background.getScaledInstance(d.width, d.height, Image.SCALE_FAST), 0, 0, null);
	}

}
