package um.edu.mt;

import java.util.HashMap;

public class AccountDatabase {

	private static HashMap<Integer, Account> map = new HashMap<Integer, Account>();
	
	private AccountDatabase() {
	}
	
	public static void addAccount(Account newAccount) {
	
		Integer number = new Integer(newAccount.getNumber());
		
		if(map.containsKey(number)) {
			System.out.println("ERROR: Account Number Unavailable");
		}
		else {
			map.push(number, newAccount);
		}
	}
	
	public static Account getAccount(int accountNumber) {
		
        Integer number = new Integer(accountNumber);
            
		if(map.containsKey(number)) {
			return map.get(number);
		}
		else {
			return null;
		}
	}
}

	
	
	

