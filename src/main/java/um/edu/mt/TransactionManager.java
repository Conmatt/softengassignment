package um.edu.mt;

public class TransactionManager {

	private int numTransactionsProcessed;
	
	public TransactionManager() {
		numTransactionsProcessed = 0;
	}
	
	public boolean processTransaction(int src, int dest, long amount) {
		
		if(amount < 0) {
			System.out.println("ERROR: Amount of money to be transferred must be greater than or equal to zero!");
			return false;
		}
		
		Transaction trans = new Transaction(src, dest, amount);
		
		if(trans.process()) {
			System.out.println("Transaction completed successfully!");
			numTransactionsProcessed++;
			return true;
		}
		else {
			System.out.println("Transaction failed!");
			return false;
		}
	}
	
	public int getCount() {
		return numTransactionsProcessed;
	}
}
