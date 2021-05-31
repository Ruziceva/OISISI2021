package theater.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import theater.view.pages.HomePage;

public class MainWindow extends JFrame {
	private static MainWindow instance;

	private MainWindow() {
		setTitle("Pozoriste");
		setSize(1024, 768);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		add(new HomePage());
	}

	public static MainWindow getInstance() {
		if (instance == null)
			instance = new MainWindow();
		return instance;
	}

	public void showPage(JPanel page) {
		getContentPane().removeAll();
		getContentPane().add(page);
		revalidate();
		repaint();
	}

}
