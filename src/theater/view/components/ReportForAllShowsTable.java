package theater.view.components;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class ReportForAllShowsTable extends JTable {

	private static final long serialVersionUID = 8900651367165240112L;

	public ReportForAllShowsTable() {
		setRowHeight(40);

		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(false);

		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new ReportForAllShowTableModel());
		setFont(new Font("arial", Font.PLAIN, 24));

		getTableHeader().setFont(new Font("arial", Font.BOLD, 24));

	}

}