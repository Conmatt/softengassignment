package um.edu.mt;

import java.util.HashMap;

public class AccountDatabase {

	private static HashMap<Integer, Account> map = new HashMap<Integer, Account>();
	
	private AccountDatabase() {
	}
	
	public static void addAccount(Account newAccount) {
	
		int number = newAccount.getNumber();
		
		if(map.containsKey(Integer(number))) {
			System.out.println("ERROR: Account Number Unavailable");
		}
		else {
			map.push(Integer(number), newAccount);
		}
	}
	
	public static Account getAccount(int accountNumber) {
		
		if(map.containsKey(accountNumber)) {
			return map.get(accountNumber);
		}
		else {
			return null;
		}
	}
}

	
	
	

