package theater.view.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

public class RegisterPage extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7834036077421634526L;
	private Dimension btnSize = new Dimension(200, 60);
	private Font btnFont = new Font("arial", Font.PLAIN, 32);
	private Image background;

	public RegisterPage() {
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
		gc.gridy = 1;

		JPanel panel = new JPanel();

		JLabel usernameLabel = new JLabel("Korisnicko ime:");
		JTextField usernameField = new JTextField();
		usernameLabel.setFont(new Font("arial", Font.PLAIN, 32));

		usernameField.setPreferredSize(new Dimension(150, 30));
		usernameLabel.setForeground(Color.WHITE);

		JLabel passwordLabel = new JLabel("Lozinka :");
		passwordLabel.setFont(new Font("arial", Font.PLAIN, 32));

		JPasswordField passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(150, 30));
		passwordLabel.setForeground(Color.WHITE);

		JLabel passwordLabel2 = new JLabel("Ponovljena lozinka :");
		passwordLabel2.setFont(new Font("arial", Font.PLAIN, 32));

		JPasswordField passwordField2 = new JPasswordField();
		passwordField2.setPreferredSize(new Dimension(150, 30));
		passwordLabel2.setForeground(Color.WHITE);

		JLabel nameLabel = new JLabel("Ime:");
		nameLabel.setFont(new Font("arial", Font.PLAIN, 32));

		JTextField nameField = new JTextField();
		nameField.setPreferredSize(new Dimension(150, 30));
		nameLabel.setForeground(Color.WHITE);

		JLabel lNameLabel = new JLabel("Prezime :");
		lNameLabel.setFont(new Font("arial", Font.PLAIN, 32));

		JTextField lnameField = new JTextField();
		lnameField.setPreferredSize(new Dimension(150, 30));
		lNameLabel.setForeground(Color.WHITE);

		JButton register = (new JButton("Registruj se"));
		register.setFocusPainted(false);
		register.setFont(btnFont);
		register.setSize(btnSize);
		register.setBackground(Color.WHITE);

		panel = new JPanel(new GridLayout(7, 2, 15, 5));

		panel.add(usernameLabel);
		panel.add(usernameField);
		panel.add(passwordLabel);
		panel.add(passwordField);

		panel.add(passwordLabel2);
		panel.add(passwordField2);

		panel.add(nameLabel);
		panel.add(nameField);

		panel.add(lNameLabel);
		panel.add(lnameField);
		panel.add(register);

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
		panel.add(backBtn);

		panel.setBackground(new Color(0, 0, 0, 0));
		
		add(panel, gc);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = getSize();
		g.drawImage(background.getScaledInstance(d.width, d.height, Image.SCALE_FAST), 0, 0, null);
	}

}
