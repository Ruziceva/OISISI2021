package theater;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import theater.model.Show;
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

	// we keep constructor as private so class can force having of only one instance
	// in app (singleton pattern)
	private GlobalState() {
		// TODO: load users from file
		users = new LinkedList<User>();
		shows = new LinkedList<Show>();
		// add some temp users
		User u = new User();
		u.setUsername("admin");
		u.setPassword("123");
		u.setType("ADMIN");
		users.add(u);

		u = new User();
		u.setUsername("user");
		u.setPassword("123");
		u.setType("USER");
		users.add(u);

		Show s = new Show();
		s.setDate(LocalDateTime.now());
		s.setDescription("xd");
		s.setName("LOL");
		s.setId(0l);
		s.setPrice(22);
		shows.add(s);
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

}
