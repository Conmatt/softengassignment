package um.edu.mt;

import java.util.HashMap;

public class AccountDatabase {

	private static HashMap<Integer, Account> map = new HashMap<Integer, Account>();
	
	private AccountDatabase() {
	}
	
	public static boolean addAccount(Account newAccount) {
	
		Integer number = new Integer(newAccount.getNumber());
		
		if(map.containsKey(number)) {
			System.out.println("ERROR: Account number unavailable");
			return false;
		}
		else {
			map.put(number, newAccount);
			return true;
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
	
	public static void commit(int number, Account update) {
		map.put(new Integer(number), update);
	}
}

	
	
	

