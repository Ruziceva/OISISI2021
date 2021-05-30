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
		getColumnModel().getColumn(2).setCellRenderer(new WordWrapCellRenderer());
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
		getTableHeader().setFont(new Font("arial", Font.BOLD, 24));

	}

	// render descritpion as multiline
	class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {
		WordWrapCellRenderer() {
			setLineWrap(true);
			setWrapStyleWord(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			setText(value.toString());
			setFont(new Font("arial", Font.PLAIN, 24));
			setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
			if (table.getRowHeight(row) != getPreferredSize().height) {
				table.setRowHeight(row, getPreferredSize().height);
			}
			if (isSelected)
				setBackground(new Color(184, 207, 229));
			return this;
		}

	}

}