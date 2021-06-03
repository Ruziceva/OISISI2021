package theater.view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import theater.GlobalState;
import theater.model.Show;
import theater.model.Ticket;

public class ShowDetails extends JDialog {
	private Set<Integer> wantedSeats = new HashSet<>();

	public ShowDetails(Show inputShow) {
		setSize(500, 650);
		setLocationRelativeTo(null);
		setModal(true);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

		JPanel panel = new JPanel();
		JPanel buttons = new JPanel(new GridLayout(1, 1));
		buttons.setMaximumSize(new Dimension(300, 100));
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

		back.setBorder(new EmptyBorder(0, 20, 0, 0));

		JButton reserve = new JButton(new AbstractAction() {
			{
				putValue(Action.NAME, "Rezervisi");
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				for (Integer i : wantedSeats) {
					inputShow.getSeats().put(i, true);
					Ticket t = new Ticket();
					t.setColumn(i % 5 + 1);
					t.setId((long) GlobalState.getInstance().getTickets().size());
					t.setOwner(GlobalState.getInstance().getLoggedInUser());
					t.setPrice(inputShow.getPrice());
					t.setRow((1 + (int) i / 6));
					t.setShow(inputShow);
					GlobalState.getInstance().getTickets().add(t);
				}
				if (inputShow.getSeats().values().stream().filter(v -> v).count() == 30) {
					inputShow.setSold(true);
					ShowTableModel.refresh();
				}
				setVisible(false);

			}
		});

		reserve.setFont(new Font("arial", Font.BOLD, 24));

		buttons.add(reserve);
		buttons.add(back);

		add(panel);

		JPanel price = new JPanel();
		JLabel totalPrice = new JLabel("   Cena: 0.0");
		totalPrice.setFont(new Font("arial", Font.BOLD, 24));
		JLabel countLabel = new JLabel("Broj karata: 0");
		countLabel.setFont(new Font("arial", Font.BOLD, 24));

		price.add(countLabel);
		price.add(totalPrice);
		price.setMaximumSize(new Dimension(500, 120));

		JPanel seats = new JPanel();
		seats.setLayout(new GridLayout(6, 5, 10, 10));
		for (Entry<Integer, Boolean> input : inputShow.getSeats().entrySet()) {
			int i = input.getKey();
			JPanel s = new Seat(1 + (int) i / 6, i % 5 + 1, input.getValue() ? "NOT_FREE" : "FREE", wantedSeats, i,
					totalPrice, countLabel, inputShow.getPrice());
			seats.add(s);
		}

		add(seats);

		if (GlobalState.getInstance().getLoggedInUser().getType().equals("USER")) {
			add(price);
		}

		add(buttons);
	}

	// displays one seat, handles click event
	class Seat extends JPanel {
		private String state;

		Seat(int row, int col, String state, Set<Integer> wantedSeats, int number, JLabel priceLabel, JLabel countLabel,
				float price) {
			this.state = state;
			if (state == "FREE") {
				setBackground(Color.GREEN);
			} else {
				setBackground(Color.RED);
			}
			add(new JLabel("Red: " + row));

			add(new JLabel("Kolona: " + col));
			if (GlobalState.getInstance().getLoggedInUser().getType().equals("USER"))
				addMouseListener(new MouseListener() {

					@Override
					public void mouseReleased(MouseEvent e) {
					}

					@Override
					public void mousePressed(MouseEvent e) {
					}

					@Override
					public void mouseExited(MouseEvent e) {
					}

					@Override
					public void mouseEntered(MouseEvent e) {
					}

					@Override
					public void mouseClicked(MouseEvent e) {
						if (Seat.this.state == "FREE") {
							wantedSeats.add(number);
							Seat.this.state = "WANTED";
							setBackground(Color.YELLOW);
						} else if (Seat.this.state == "WANTED") {
							Seat.this.state = "FREE";
							wantedSeats.remove(number);
							setBackground(Color.GREEN);
						}
						priceLabel.setText("   Cena: " + wantedSeats.size() * price);
						countLabel.setText("Broj karata: " + wantedSeats.size());
					}
				});
			;
		}

	}
}