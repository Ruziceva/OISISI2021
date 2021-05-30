package theater.view.components;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import theater.GlobalState;
import theater.model.Show;

public class ShowTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 2710362894062472488L;

	private List<String> columns = new ArrayList<String>();

	public ShowTableModel() {
		columns.add("ID");
		columns.add("Ime");
		columns.add("Opis");
		columns.add("Datum");
		columns.add("Cena");
		if (GlobalState.getInstance().getLoggedInUser().getType() == "ADMIN")
			columns.add("");
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex==5;
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
			return String.class;
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
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
			return (show.getDate().format(formatter));
		case 4:
			return show.getPrice();
		case 5:
			return "Izmeni";

		}
		return null;
	}
}
