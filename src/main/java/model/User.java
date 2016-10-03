package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import model.db.UserDAO;

public class User {

	private String username;
	private String password;
	private String email;
	private String profilePic;// url --> profile pic
	
    public User(){}
	
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public boolean hasAdministrationFunctionality(){
		return false;
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
	
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(isStrongPassword(password)){
			this.password = password;
		}
	}

	public void changePassword(String password){
		if(isStrongPassword(password)){
			this.password = password;
			UserDAO.getInstance().writeNewPassword(this.getUsername(),password);
		}
	}
	
	public void changeProfilePic(String url){
		if(isValidImageURL(url)){
			this.profilePic = url;
			UserDAO.getInstance().writeNewProfilePic(this.getUsername(),url);
		}
		else{
			System.out.println("invalid url for image");
		}
	}

	public boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}
	
	// �� �� ������, �� �� �� ���� ���� ������ ��������
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
	
	boolean isStrongPassword(String password){
		String lowerCase = "abcdefghijklmnopqrstuvwxyz";
		String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String digits = "0123456789";
		boolean lowCaseFlag = false;
		boolean uppCaseFlag = false;
		boolean digitFlag = true;
		for (int i = 0; i < lowerCase.length(); i++) {
			if(password.indexOf(lowerCase.charAt(i)) != -1){
				lowCaseFlag = true;
			}
			if(password.indexOf(upperCase.charAt(i)) != -1){
				uppCaseFlag = true;
			}
			if(i<digits.length() && password.indexOf(digits.charAt(i)) != -1){
				digitFlag = true;
			}
		}
		return (password.length() >= 5) && lowCaseFlag && uppCaseFlag && digitFlag;
	}
}
