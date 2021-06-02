package theater.view.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import theater.GlobalState;
import theater.controller.UserController;
import theater.view.components.NewShow;
import theater.view.components.ShowsTable;

public class ShowsPage extends JPanel {

	private JTable table;
	private Image background;

	public ShowsPage() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		table = new ShowsTable();
		scrollPane.setViewportView(table);

		File f = null;
		f = new File("./images/home.jpg");
		try {
			background = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel lab = new JLabel("Predstave");

		lab.setFont(new Font("arial", Font.BOLD, 50));
		JSplitPane p = new JSplitPane();
		p.setOrientation(JSplitPane.HORIZONTAL_SPLIT);

		p.add(lab, JSplitPane.TOP);
		JPanel buttons = new JPanel();
		JButton logout = new JButton("Odjava");
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserController().logOut();
			}
		});
		logout.setPreferredSize(new Dimension(150, 40));

		JButton newShow = new JButton("Nova");
		newShow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new NewShow(null).setVisible(true);
			}
		});
		newShow.setPreferredSize(new Dimension(150, 40));
		//add new btn for admin
		if (GlobalState.getInstance().getLoggedInUser().getType() == "ADMIN")
			buttons.add(newShow);
		buttons.add(logout);

		buttons.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p.add(buttons, JSplitPane.RIGHT);
		p.setEnabled(false);
		p.setDividerSize(0);
		p.add(lab, JSplitPane.LEFT);

		JSplitPane split = new JSplitPane();
		split.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split.add(p, JSplitPane.TOP);
		split.add(scrollPane, JSplitPane.BOTTOM);
		split.setDividerSize(0);
		split.setEnabled(false);
		add(split);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = getSize();
		g.drawImage(background.getScaledInstance(d.width, d.height, Image.SCALE_FAST), 0, 0, null);
	}

}
