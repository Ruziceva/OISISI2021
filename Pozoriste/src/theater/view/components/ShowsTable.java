package theater.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import theater.GlobalState;

public class ShowsTable extends JTable {

	private static final long serialVersionUID = 8900651367165240112L;

	public ShowsTable() {
		setRowHeight(40);

		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(false);

		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new ShowTableModel());
		setFont(new Font("arial", Font.PLAIN, 24));
		if (GlobalState.getInstance().getLoggedInUser().getType() == "ADMIN") {
			Action delete = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					JTable table = (JTable) e.getSource();
					int modelRow = Integer.valueOf(e.getActionCommand());
					// TODO: show edit form
				}
			};

			new ButtonColumn(this, delete, 5);
		}
		
		//details:
		Action details = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				// TODO: show edit form
			}
		};

		new ButtonColumn(this, details, 4);
		
		getTableHeader().setFont(new Font("arial", Font.BOLD, 24));

	}


}