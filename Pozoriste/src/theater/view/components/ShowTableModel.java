package theater.view.components;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import theater.GlobalState;
import theater.model.Show;

public class ShowTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 2710362894062472488L;

	private List<String> columns = new ArrayList<String>();

	private ShowTableModel() {
		columns.add("id");
		columns.add("name");
		columns.add("description");
		columns.add("date");
		columns.add("price");
		if (GlobalState.getInstance().getLoggedInUser().getType() == "ADMIN")
			columns.add("edit");
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public int getRowCount() {
		return GlobalState.getInstance().getShows().size();
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
		switch (columnIndex) {
		case 0:
			return Long.class;
		case 1:
		case 2:
		case 3:
			return String.class;
		case 4:
			return Float.class;
		case 5:
			return JButton.class;
		default:
			return null;
		}
	}

	@Override
	public Object getValueAt(int r, int c) {
		Show show = GlobalState.getInstance().getShows().get(r);
		switch (c) {
		case 0:
			return show.getId();
		case 1:
			return show.getName();
		case 2:
			return show.getDescription();
		case 3:
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return f.format(show.getDate());
		case 4:
			return show.getPrice();
		case 5:
			return new JButton("Iznemi");

		}
		return null;
	}
}
