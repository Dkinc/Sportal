package model;

import java.util.concurrent.ConcurrentHashMap;

import model.db.AdminDAO;
import model.db.UserDAO;

public class AdminManager {

	private static AdminManager instance;

	private ConcurrentHashMap<String, User> registerredUsers;// username -> user
																// (admin is
																// included)

	private AdminManager() {
		registerredUsers = new ConcurrentHashMap<String, User>();
		for (User u : UserDAO.getInstance().getAllUsers()) {
			registerredUsers.put(u.getUsername(), u);
		}
	}

	public synchronized static AdminManager getInstance() {
		if (instance == null) {
			instance = new AdminManager();
		}
		return instance;
	}

	public synchronized void addNews(String title, String text, String category, String picturesURL) {
		News n = new News(title, text, category, picturesURL);
		// Dobavqt li se v kolekciq?
		AdminDAO.getInstance().addNews(n);
	}

}
