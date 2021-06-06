package theater.view.components;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.table.AbstractTableModel;

import theater.GlobalState;
import theater.model.Show;
import theater.model.Ticket;

public class ReportForOneShowTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 2710362894062472488L;

	private List<String> columns = new ArrayList<String>();
	private long showId;
	private List<Ticket> tickets;

	public ReportForOneShowTableModel(long showId) {
		columns.add("ID");
		columns.add("Cena");
		this.showId = showId;
		tickets = GlobalState.getInstance().getTickets().stream().filter(ticket -> ticket.getShow().getId() == showId)
				.collect(Collectors.toList());
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public int getRowCount() {
		return (int) tickets.size();
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
		switch (c) {
		case 0:
			return tickets.get(r).getId();
		case 1:
			return tickets.get(r).getPrice();

		}
		return null;
	}

}
