package um.edu.mt;

public class Transaction {

	private int sourceAccountNumber, destinationAccountNumber;
	private long amount;
	
	public Transaction(int src, int dest, long amount) {
		this.sourceAccountNumber = src;
		this.destinationAccountNumber = dest;
		this.amount = amount;
	}
	
	public boolean process() {
		AccountDatabase db = AccountDatabase.getInstance();
		
		Account src = db.getAccount(sourceAccountNumber);
		Account dest = db.getAccount(destinationAccountNumber);
		
		if(src == null || dest == null) {
			System.out.println("ERROR: Invalid account number(s) specified");
			return false;
		}
		
		long time = System.currentTimeMillis();
		
		if((time < src.getLastTransaction() + 15000) || (time < src.getLastTransaction() + 15000)) {
			System.out.println("One or more accounts are locked. Please wait before attempting another transaction");
			return false;
		}
		
		if(src.adjustBalance(-this.amount)) {
			dest.adjustBalance(this.amount);
			
			time = System.currentTimeMillis();
			src.setLastTransaction(time);
			dest.setLastTransaction(time);
			
			db.commit(src);
			db.commit(dest);
			return true;
		}
		else {
			System.out.println("ERROR: Insufficient funds for Account #" + sourceAccountNumber + " (Balance: € " + src.getBalance() + ")");
			return false;
		}
	}
}
