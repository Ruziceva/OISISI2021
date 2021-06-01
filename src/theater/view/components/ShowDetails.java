package theater.view.components;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;

import javax.swing.AbstractAction;
import javax.swing.Action;
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
		setSize(500, 250);
		setMinimumSize(new Dimension(500, 250));
		setLocationRelativeTo(null);
		setModal(true);

		// setLayout(new GridBagLayout());
		JPanel panel = new JPanel();

		JLabel nameLabel = new JLabel("Naziv:");
		JLabel nameField = new JLabel();

		JLabel descriptionLabel = new JLabel("Opis :");
		JTextArea descriptionField = new JTextArea();

		JLabel priceLabel = new JLabel("Cena :");
		JLabel priceField = new JLabel();

		JLabel dateLabel = new JLabel("Datum:");

		panel = new JPanel(new GridLayout(5, 2, 15, 5));

		panel.add(nameLabel);
		panel.add(nameField);

		panel.add(descriptionLabel);
		panel.add(new JScrollPane(descriptionField));
		descriptionField.setEditable(false);
		descriptionField.setEnabled(false);


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

		panel.add(new JButton(new AbstractAction() {
			{
				putValue(Action.NAME, "Nazad");
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		}));

		add(panel);
	}
}