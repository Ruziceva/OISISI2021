package theater.view.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpinnerDateModel;

public class SearchBar extends JPanel {
	private JTextField field1;
	private JTextField field2;

	public SearchBar(JTable table) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNewLabel = new JLabel("Parametar:");
		panel.add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Naziv", "Cena", "Datum" }));
		comboBox.setSelectedIndex(0);
		panel.add(comboBox);

		JLabel lblVrednost = new JLabel("Vrednost:");
		panel.add(lblVrednost);
		JCheckBox cb1 = new JCheckBox();
		panel.add(cb1);

		JSpinner timeSpinner1 = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor1 = new JSpinner.DateEditor(timeSpinner1, "yyyy-MM-dd HH:mm");
		timeSpinner1.setEditor(timeEditor1);

		JSpinner timeSpinner2 = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(timeSpinner2, "yyyy-MM-dd HH:mm");
		timeSpinner2.setEditor(timeEditor2);
		cb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timeSpinner1.setEnabled(cb1.isSelected());
			}
		});
		field1 = new JTextField();
		panel.add(field1);
		field1.setColumns(10);

		JCheckBox cb2 = new JCheckBox();
		cb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timeSpinner2.setEnabled(cb2.isSelected());
			}
		});
		panel.add(cb2);
		field2 = new JTextField();
		field2.setColumns(10);
		panel.add(field2);

		cb1.setSelected(true);
		cb2.setSelected(true);

		JButton btnNewButton_1 = new JButton("Trazi");
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int sel = comboBox.getSelectedIndex();
				panel.removeAll();

				panel.add(lblNewLabel);
				panel.add(comboBox);
				panel.add(lblVrednost);

				switch (sel) {

				case 0:
					panel.add(field1);
					break;
				case 1:
					panel.add(field1);
					panel.add(new JLabel("-"));
					panel.add(field2);
					break;
				case 2:
					panel.add(cb1);
					panel.add(timeSpinner1);
					panel.add(new JLabel("-"));
					panel.add(cb2);
					panel.add(timeSpinner2);
					break;
				}
				revalidate();
				repaint();
			}
		});

		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RowFilter<Object, Object> f = null;
				// f.add(Tabela.sakrijIzbrisane());

				int sel = comboBox.getSelectedIndex();
				switch (sel) {
				case 0:
					f = (nameFilter(field1.getText()));
					break;
				case 1:
					float min = Float.MIN_VALUE, max = Float.MAX_VALUE;
					try {
						min = Float.parseFloat(field1.getText());
					} catch (Exception ex) {
					}
					try {
						max = Float.parseFloat(field2.getText());
					} catch (Exception ex) {
					}
					f = (priceFilter(min, max));
					break;

				case 2:
					LocalDateTime dateM = ((Date) timeSpinner1.getValue()).toInstant().atZone(ZoneId.systemDefault())
							.toLocalDateTime();
					LocalDateTime dateM2 = ((Date) timeSpinner2.getValue()).toInstant().atZone(ZoneId.systemDefault())
							.toLocalDateTime();
					LocalDateTime minD = cb1.isSelected() ? dateM : null;
					LocalDateTime maxD = cb2.isSelected() ? dateM2 : null;
					f = (dateFilter(minD, maxD));
					break;

				}
				((DefaultRowSorter) table.getRowSorter()).setRowFilter(f);
			}
		});
		comboBox.setSelectedIndex(0);
		setLayout(new BorderLayout());
		add(panel);
		btnNewButton_1.setPreferredSize(new Dimension(100, 30));
		add(btnNewButton_1, BorderLayout.EAST);
	}

	// use regex ignorig case
	public static RowFilter nameFilter(String search) {
		return RowFilter.regexFilter("(?i)" + search, 0);
	}

	public static RowFilter<Object, Object> priceFilter(float min, float max) {
		return new RowFilter<Object, Object>() {
			public boolean include(Entry<? extends Object, ? extends Object> entry) {
				ShowTableModel t = (ShowTableModel) entry.getModel();
				float price = (float) t.getValueAt((int) entry.getIdentifier(), 2);
				return price >= min && price <= max;
			}
		};

	}

	public static RowFilter<Object, Object> dateFilter(LocalDateTime min, LocalDateTime max) {
		return new RowFilter<Object, Object>() {
			public boolean include(Entry<? extends Object, ? extends Object> entry) {
				ShowTableModel t = (ShowTableModel) entry.getModel();
				String d = (String) t.getValueAt((int) entry.getIdentifier(), 1);
				LocalDateTime date = LocalDateTime.parse(d, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
				boolean minB = min == null || !date.isBefore(min);
				boolean maxB = max == null || !date.isAfter(max);
				return maxB && minB;
			}
		};

	}

}
