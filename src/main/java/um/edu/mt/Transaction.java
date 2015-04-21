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
		Account src = AccountDatabase.getAccount(sourceAccountNumber);
		Account dest = AccountDatabase.getAccount(destinationAccountNumber);
		
		if(src.adjustBalance(-amount)) {
			dest.adjustBalance(amount);
			src.
			return true;
		}
		else {
			System.out.println("ERROR: Insufficient funds for Account #" + sourceAccountNumber + " (Balance: € " + src.getBalance() + ")");
			return false;
		}
	}
}
		
		
		
		