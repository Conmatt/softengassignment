public class TransactionManager {

	private static int numTransactionsProcessed = 0;
	
	public static boolean processTransaction(int src, int dest, long amount) {
		
		Transaction trans = new Transaction(src, dest, amount);
		
		if(trans.process()) {
			System.out.println("Transaction completed successfully");
			numTransactionsProcessed++;
		}
		else {
			System.out.println("Transaction failed");
		}
	}
}
