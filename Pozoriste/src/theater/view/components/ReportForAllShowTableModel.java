package theater.view.components;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import theater.GlobalState;
import theater.model.Show;

public class ReportForAllShowTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 2710362894062472488L;

	private List<String> columns = new ArrayList<String>();

	public ReportForAllShowTableModel() {
		columns.add("ID");
		columns.add("Cena");
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public int getRowCount() {
		return (int) GlobalState.getInstance().getShows().size();
	}

	@Override
	public int getColumnCount() {
		return columns.size();
	}

	@Override
	public String getColumnName(int column) {
		return columns.get(column);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public Object getValueAt(int r, int c) {
		Show s = GlobalState.getInstance().getShows().get(r);
		switch (c) {
		case 0:
			return s.getId();
		case 1:
			return GlobalState.getInstance().getTickets().stream().filter(t -> t.getShow().getId() == s.getId())
					.reduce((Float) 0f, (acc, t) -> acc + t.getPrice(), Float::sum);

		}
		return null;
	}

}
