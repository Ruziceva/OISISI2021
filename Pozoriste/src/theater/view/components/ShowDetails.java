package theater.view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;
import java.util.Map.Entry;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import theater.model.Show;

public class ShowDetails extends JDialog {

	public ShowDetails(Show inputShow) {
		// show is null when creating new one, else is the one being edited
		setSize(500, 600);
		// setMinimumSize(new Dimension(500, 250));
		setLocationRelativeTo(null);
		setModal(true);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

		JPanel panel = new JPanel();
		JPanel buttons = new JPanel(new GridLayout(1, 1));
		buttons.setMaximumSize(new Dimension(200, 100));
		JLabel nameLabel = new JLabel("Naziv:");
		JLabel nameField = new JLabel();

		JLabel descriptionLabel = new JLabel("Opis :");
		JTextArea descriptionField = new JTextArea();

		JLabel priceLabel = new JLabel("Cena :");
		JLabel priceField = new JLabel();

		JLabel dateLabel = new JLabel("Datum:");

		panel = new JPanel(new GridLayout(4, 2, 15, 5));

		panel.add(nameLabel);
		panel.add(nameField);

		panel.add(descriptionLabel);
		panel.add(new JScrollPane(descriptionField));
		descriptionField.setEditable(false);

		panel.add(priceLabel);
		panel.add(priceField);

		panel.add(dateLabel);

		JLabel timeSpinner = new JLabel();

		panel.add(timeSpinner);

		nameField.setText(inputShow.getName());
		descriptionField.setText(inputShow.getDescription());
		priceField.setText("" + inputShow.getPrice());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		timeSpinner.setText((inputShow.getDate().format(formatter)));

		JButton back = new JButton(new AbstractAction() {
			{
				putValue(Action.NAME, "Nazad");
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});

		back.setFont(new Font("arial", Font.BOLD, 24));

		buttons.add(back);
		add(panel);

		JPanel seats = new JPanel();
		seats.setLayout(new GridLayout(6, 5, 10, 10));
		for (Entry<Integer, Boolean> input : inputShow.getSeats().entrySet()) {
			int i = input.getKey();
			JPanel s = new Seat(1 + (int) i / 6, i % 5 + 1, input.getValue() ? "" : "FREE");
			seats.add(s);
		}

		add(seats);
		add(buttons);
	}

	class Seat extends JPanel {

		Seat(int row, int col, String state) {
			if (state == "FREE") {
				setBackground(Color.GREEN);
			} else {
				setBackground(Color.RED);
			}
			add(new JLabel("Red: " + row));

			add(new JLabel("Kolona: " + col));
		}

	}
}