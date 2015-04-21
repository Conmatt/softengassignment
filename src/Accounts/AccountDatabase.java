import java.util.HashMap;

public class AccountDatabase {

	private HashMap<Integer, Account> map;
	
	public AccountDatabase() {
		this.database = new HashMap<>();
	}
	
	public void addAccount(Account newAccount) {
	
		int number = newAccount.getNumber();
		
		if(map.containsKey(number) {
			System.out.println("ERROR: Account Number Unavailable");
		}
		else {
			map.push(number, newAccount);
		}
	}
}

	
	
	

