package theater.view.components;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import theater.GlobalState;

public class ReportForAllShows extends JDialog {
	public ReportForAllShows() {
		setSize(500, 650);
		setLocationRelativeTo(null);
		setModal(true);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JButton back = new JButton(new AbstractAction() {
			{
				putValue(Action.NAME, "Nazad");
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});

		back.setFont(new Font("arial", Font.PLAIN, 24));
		float total = GlobalState.getInstance().getTickets().stream().reduce((Float) 0f, (acc, t) -> acc + t.getPrice(),
				Float::sum);

		add(new JScrollPane(new ReportForAllShowsTable()));
		JLabel tot = new JLabel("UKUPNO: " + total);
		tot.setFont(new Font("arial", Font.PLAIN, 24));
		tot.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(tot);
		add(back);
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
	}

}