package theater.view.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import theater.view.components.ShowsTable;


public class ShowsPage extends JPanel {
	
	private JTable table;
	private Image background;

	public ShowsPage() {
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
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
		
		JLabel lab=new JLabel("Predstave");
		lab.setFont(new Font("arial", Font.BOLD, 50));
		add(lab);
		add(scrollPane);


	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = getSize();
		g.drawImage(background.getScaledInstance(d.width, d.height, Image.SCALE_FAST), 0, 0, null);
	}

}
