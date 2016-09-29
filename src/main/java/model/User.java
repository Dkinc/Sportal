package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class User {

	private String username;
	private String password;
	private String email;
	private String profilePic;// url --> profile pic
	

	public User(String username, String password ,String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}
	
	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		if(isValidImageURL(profilePic)){
			this.profilePic = profilePic;
		}
	}

	public void setUsername(String username) {
		if(username != null && username != ""){
			this.username = username;
		}
	}

	public void setEmail(String email) {
		if(isValidEmailAddress(email)){
			this.email = email;
		}
	}
	
	void searchNewsByTitle(String title){
		//ToDo
	}
	
	void searchNewsByCategory(String category){
		//ToDo
	}

	public boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}
	
	// да се тества, за да се види дали работи коректно
	public boolean isValidImageURL(String url){  
        try {  
            BufferedImage image = ImageIO.read(new URL(url));    
            if(image != null){  
                return true;
            } else{
                return false;
            }

        } catch (MalformedURLException e) {  
             
            System.err.println("URL error with image");  
            e.printStackTrace();
            return false;
        } catch (IOException e) {  
            System.err.println("IO error with image");  
            
            e.printStackTrace();
            return false;  
        }  
    }
}
