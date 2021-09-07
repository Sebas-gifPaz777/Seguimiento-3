package model;

import java.util.ArrayList;
import java.util.List;

public class Classroom {

	private List<UserAccount> accounts;
	
	public Classroom(){
		
		accounts= new ArrayList <UserAccount>();
		accounts.add(new UserAccount("jhonny", "male","SIS", "O1/O1/2001", "HEY"));
	}
	
	public boolean add(UserAccount newAccount) {
		
		if(accounts.add(newAccount))
			return true;
		else
			return false;
	}
	 //TODO
	public boolean remove(int index) {
		return true;
	}
	
	public List<UserAccount> getAccounts() {
		return accounts;
	}
}
