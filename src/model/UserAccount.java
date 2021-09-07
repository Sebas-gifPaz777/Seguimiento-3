package model;

public class UserAccount {

	private String username;
	private String gender;
	private String career;
	private String birthday;
	private String browser;
	
	public UserAccount(String username, String gender, String career, String birthday, String browser) {
		this.username = username;
		this.gender = gender;
		this.career = career;
		this.birthday = birthday;
		this.browser = browser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}
	
}
