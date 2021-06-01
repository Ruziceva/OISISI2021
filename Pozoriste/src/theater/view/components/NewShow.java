package theater.view.components;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import theater.GlobalState;
import theater.model.Show;

public class NewShow extends JDialog {

	public NewShow() {
		setSize(500, 250);
		setMinimumSize(new Dimension(500, 250));
		setLocationRelativeTo(null);
		setModal(true);

		// setLayout(new GridBagLayout());
		JPanel panel = new JPanel();

		JLabel nameLabel = new JLabel("Naziv:");
		JTextField nameField = new JTextField();

		JLabel descriptionLabel = new JLabel("Opis :");
		JTextArea descriptionField = new JTextArea();

		JLabel priceLabel = new JLabel("Cena :");
		JTextField priceField = new JTextField();

		JLabel dateLabel = new JLabel("Datum:");

		JButton insert = (new JButton("Dodaj"));
		panel = new JPanel(new GridLayout(5, 2, 15, 5));

		panel.add(nameLabel);
		panel.add(nameField);

		panel.add(descriptionLabel);
		panel.add(new JScrollPane(descriptionField));

		panel.add(priceLabel);
		panel.add(priceField);

		panel.add(dateLabel);

		JSpinner timeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "dd-MM-yyyy HH:mm:ss");
		timeSpinner.setEditor(timeEditor);

		timeSpinner.setValue(new Date()); //

		panel.add(timeSpinner);

		panel.add(insert);

		panel.add(new JButton(new AbstractAction() {
			{
				putValue(Action.NAME, "Nazad");
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		}));

		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				//TODO: validate
				
				String name = nameField.getText().trim();
				String price = priceField.getText().trim();
				String description = descriptionField.getText();
				LocalDateTime date = ((Date) timeSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault())
						.toLocalDateTime();
			
				Show show = new Show();
				show.setName(name);
				show.setDescription(description);
				show.setPrice(Float.parseFloat(price));
				show.setId((long) GlobalState.getInstance().getShows().size());
				show.setDate(date);
				GlobalState.getInstance().getShows().add(show);
				ShowTableModel.refresh();
				setVisible(false);
				
				
				dispose();

			}
		});

		add(panel);
	}
}