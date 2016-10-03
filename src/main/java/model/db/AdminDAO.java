package model.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.News;

public class AdminDAO {
	private static AdminDAO instance;
	static final String addNewsQuery = "INSERT INTO news (title,text,category,picture_address) VALUES (?,?,?,?);";

	// Trqbva da se promeni category v DB ot int v string
	private AdminDAO() {

	}

	public synchronized static AdminDAO getInstance() {
		if (instance == null) {
			instance = new AdminDAO();
		}
		return instance;
	}

	public void addNews(News n) {
		try {
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(addNewsQuery);
			st.setString(1, n.getTitle());
			st.setString(2, n.getText());
			st.setString(3, n.getCategory());
			st.setString(4, n.getPicturesURL());
			st.executeUpdate();
			System.out.println("Movie added successfully");
		} catch (SQLException e) {
			System.out.println("Couldn't add news to DB!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
