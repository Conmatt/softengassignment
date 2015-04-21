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
		
		if(src == null || dest == null) {
			System.out.println("ERROR: Invalid account number(s) specified");
			return false;
		} 
		
		if(src.adjustBalance(-this.amount)) {
			dest.adjustBalance(this.amount);
			System.out.println(dest.getName());
			System.out.println(dest.getNumber());
			System.out.println(dest.getBalance());
			return true;
		}
		else {
			System.out.println("ERROR: Insufficient funds for Account #" + sourceAccountNumber + " (Balance: € " + src.getBalance() + ")");
			return false;
		}
	}
}
