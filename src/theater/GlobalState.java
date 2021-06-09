package theater;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import theater.model.Show;
import theater.model.Ticket;
import theater.model.User;

//global state of app (all users, shows, active user,.....)
public class GlobalState {

	private static GlobalState instance;

	// all registered users
	private List<User> users;
	// user that is logged in right now
	private User loggedInUser;

	// all shows
	private List<Show> shows;

	// all tickets
	private List<Ticket> tickets;

	// we keep constructor as private so class can force having of only one instance
	// in app (singleton pattern)
	private GlobalState() {
		users = (List<User>) loadObjectFromFile("./users.data");
		// if file is deleted we add default admin
		if (users == null) {
			users = new LinkedList<User>();
			// add some temp users
			User u = new User();
			u.setUsername("admin");
			u.setPassword("admin");
			u.setType("ADMIN");
			users.add(u);
		}
		shows = (List<Show>) loadObjectFromFile("./shows.data");
		if (shows == null)
			shows = new LinkedList<Show>();

		tickets = (List<Ticket>) loadObjectFromFile("./tickets.data");
		if (tickets == null)
			tickets = new LinkedList<>();
	}

	// this is how everywhere will be the same instance
	public static GlobalState getInstance() {
		if (instance == null)// if there is no instance, new one is created
			instance = new GlobalState();
		return instance;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> shows) {
		this.shows = shows;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public void saveAll() {
		saveObjectToFile(users, "./users.data");
		saveObjectToFile(shows, "./shows.data");
		saveObjectToFile(tickets, "./tickets.data");
	}

	private void saveObjectToFile(Object o, String path) {
		File f = new File(path);
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
			out.writeObject(o);
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {

			try {
				if (out != null)
					out.close();
			} catch (Exception e) {
			}
		}
	}

	private Object loadObjectFromFile(String path) {
		File f = new File(path);
		ObjectInputStream in = null;
		Object ret = null;
		try {
			in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
			ret = in.readObject();
		} catch (Exception e) {
			System.out.println();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e) {
			}
		}
		return ret;
	}

}
