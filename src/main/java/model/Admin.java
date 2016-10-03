package model;

public class Admin extends User {

	public Admin() {
	}

	public Admin(String username, String password, String email) {
		super(username, password, email);
	}

	public void addNews(String title, String text, String category, String picturesURL, String videoURL) {
		if (hasAdministrationFunctionality()) {
			NewsManager.getInstance().makeNews(title, text, category, picturesURL, videoURL);
		}
	}

	@Override
	public boolean hasAdministrationFunctionality() {
		return true;
	}
}
