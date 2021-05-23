package theater.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Show {
	private Long id;
	private String name;
	private String description;
	private LocalDateTime date;
	private float price;
	private boolean sold;
	private Map<Integer, Boolean> seats;

	public Show() {
		seats = new HashMap<Integer, Boolean>();
		//init all seats to avaliable
		for (int i = 0; i < 30; ++i)
			seats.put(i, false);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public Map<Integer, Boolean> getSeats() {
		return seats;
	}

	public void setSeats(Map<Integer, Boolean> seats) {
		this.seats = seats;
	}

}
