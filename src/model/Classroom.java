package model;

import java.util.ArrayList;
import java.util.List;

public class Classroom {

	private List<UserAccount> accounts;
	
	public Classroom(){
		
		accounts= new ArrayList <UserAccount>();
	}
	
	public boolean add(UserAccount newAccount) {
		
		if(accounts.add(newAccount))
			return true;
		else
			return false;
	}
	
	public boolean chechUser(String usernm,String passwd,String gender,String major,String date,String brow, String photo) {
		String[] elements= {usernm,passwd,photo,gender,major,date};

		boolean empty=false;

		for(int i=0;i<elements.length && !empty;i++) {
			if(elements[i]=="")
				empty=true;
		}
		
		return empty;
	}
	 //TODO
	public boolean remove(int index) {
		return true;
	}
	
	public List<UserAccount> getAccounts() {
		return accounts;
	}
	
	public UserAccount userExist(String usern) {
		UserAccount found=null;
		for(int i=0;i<accounts.size();i++) {
			if(accounts.get(i).getUsername().equalsIgnoreCase(usern)) {
				found=accounts.get(i);
			}
		}
		return found;
	}
}
