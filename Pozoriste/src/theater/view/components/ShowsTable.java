package theater.view.components;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableRowSorter;

import theater.GlobalState;

public class ShowsTable extends JTable {

	private static final long serialVersionUID = 8900651367165240112L;

	public ShowsTable() {
		setRowHeight(40);

		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(false);
		setAutoCreateRowSorter(true);

		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new ShowTableModel());
		((TableRowSorter )getRowSorter()).setSortable(4,false);

		setFont(new Font("arial", Font.PLAIN, 24));
		if (GlobalState.getInstance().getLoggedInUser().getType().equals( "ADMIN")) {
			((TableRowSorter )getRowSorter()).setSortable(5,false);

			Action delete = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					JTable table = (JTable) e.getSource();
					int modelRow = Integer.valueOf(e.getActionCommand());
					new NewShow(GlobalState.getInstance().getShows().get(modelRow)).setVisible(true);
				}
			};

			new ButtonColumn(this, delete, 5);
		}

		// details:
		Action details = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				new ShowDetails(GlobalState.getInstance().getShows().get(modelRow)).setVisible(true);;
			}
		};

		new ButtonColumn(this, details, 4);

		getTableHeader().setFont(new Font("arial", Font.BOLD, 24));

	}

}