package theater.view.pages;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import theater.view.components.ReportForAllShows;
import theater.view.components.ShowsTable;

public class ShowsPage extends JPanel {

	private JTable table;

	public ShowsPage() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		table = new ShowsTable();
		scrollPane.setViewportView(table);

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

		JButton report = new JButton("Izvestaj");
		report.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ReportForAllShows().setVisible(true);
			}
		});
		report.setPreferredSize(new Dimension(150, 40));
		newShow.setPreferredSize(new Dimension(150, 40));
		// add new btn for admin
		if (GlobalState.getInstance().getLoggedInUser().getType().equals("ADMIN")) {
			buttons.add(newShow);
			buttons.add(report);

		}
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

}
