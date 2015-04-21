public class Transaction {

	private int sourceAccountNumber, destinationAccountNumber;
	private long amount;
	
	public Transaction(int source, int dest, long amount) {
		this.sourceAccountNumber = source;
		this.destinationAccountNumber = destination;
		this.amount = amount;
	}
	
	public boolean process() {
		Account source = AccountDatabase.getAccount(sourceAccountNumber);
		Account dest = AccountDatabase.getAccount(destinationAccountNumber);
		
		if(source.adjustBalance(-amount)) {
			dest.adjustBalance(amount);
			return true;
		}
		else {
			System.out.println("ERROR: Insufficient funds for Account #" + sourceAccountNumber + " (Balance: € " + source.getBalance() + ")");
			return false;
		}
	}
}
		
		
		
		