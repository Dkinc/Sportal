package model;

public class Comment {

	private User user; 
	private String text;
	private String title;
	private String dateAndTime; // time of publishing
	private int likes = 0;
	private int dislikes = 0;
	
	public Comment(User user, String text, String title) {
		super();
		this.user = user;
		this.text = text;
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		if(text != null && text != ""){
			this.text = text;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title != null && title != ""){
			this.title = title;
		}
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		if(likes>0){
			this.likes = likes;
		}
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		if(dislikes>0){
			this.dislikes = dislikes;
		}
	}
	
	public String getUserName(User user){
		return user.getUsername();
	}
}
