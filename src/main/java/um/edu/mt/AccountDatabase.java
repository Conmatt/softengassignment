package um.edu.mt;

import java.util.HashMap;

public class AccountDatabase {

	private static AccountDatabase instance = null;
	private HashMap<Integer, Account> db;
	private int size;
	
	private AccountDatabase() {
		db = new HashMap<Integer, Account>();
		size = 0;
	}
	
	public static AccountDatabase initialise() {
		if(instance == null) {
			instance = new AccountDatabase();
		}
		
		return instance;
	}
	
	public static AccountDatabase getInstance() {
		return instance;
	}
	
	public void reset() {
		db.clear();
		size = 0;
	}
	
	public boolean addAccount(Account newAccount) {
	
		Integer number = new Integer(newAccount.getNumber());
		
		if(db.containsKey(number)) {
			System.out.println("ERROR: Account number unavailable");
			return false;
		}
		else {
			db.put(number, newAccount);
			size++;
			return true;
		}
	}
	
	public Account getAccount(int accountNumber) {
		
        Integer number = new Integer(accountNumber);
            
		if(db.containsKey(number)) {
			return db.get(number);
		}
		else {
			return null;
		}
	}
	
	public int getSize() {
		return size;
	}
	
	public void commit(Account update) {
		db.put(new Integer(update.getNumber()), update);
	}
}

	
	

