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
		columns.add("Ime");
		columns.add("Datum");
		columns.add("Cena");
		columns.add("Rasprodato");
		columns.add("");// details btn
		if (GlobalState.getInstance().getLoggedInUser().getType() == "ADMIN")
			columns.add(""); // edit btn
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex >= 4;
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
		return String.class;
	}

	@Override
	public Object getValueAt(int r, int c) {
		Show show = GlobalState.getInstance().getShows().get(r);
		switch (c) {
		case 0:
			return show.getName();
		case 1:
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
			return (show.getDate().format(formatter));
		case 2:
			return show.getPrice();
		case 3:
			return show.isSold() ? "DA" : "NE";
		case 4:
			return "Vise informacija";
		case 5:
			return "Izmeni";

		}
		return null;
	}
}
