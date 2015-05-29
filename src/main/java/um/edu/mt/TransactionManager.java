package um.edu.mt;

public class TransactionManager {

	private int numTransactionsProcessed;
	private AccountDatabase db;
	
	public TransactionManager() {
		numTransactionsProcessed = 0;
		db = AccountDatabase.getInstance();
	}
	
	//This method was kept intact so as not to break any legacy code
	public boolean processTransaction(int sourceAccountNumber, int destinationAccountNumber, long amount) {

		Transaction trans = new AtomicTransaction(sourceAccountNumber, destinationAccountNumber, amount);

		try {
			trans.validate();
		}
		catch (TransactionException t) {
			System.out.println(t.getMessage());
			return false;
		}

		Account source = db.getAccount(sourceAccountNumber);
		Account destination = db.getAccount(destinationAccountNumber);

		long time = System.currentTimeMillis();

		if((time < source.getLastTransaction() + 15000) || (time < destination.getLastTransaction() + 15000)) {
			System.out.println(ClientStrings.TRANS_LOCK);
			return false;
		}

		if(trans.process()) {
			System.out.println(ClientStrings.TRANS_SUCCESS);
			numTransactionsProcessed++;
			return true;
		}
		else {
			System.out.println(ClientStrings.TRANS_FAILURE);
			return false;
		}
	}

	public boolean processTransaction(Transaction trans) {
		try {
			trans.validate();
		}
		catch (TransactionException t) {
			System.out.println(t.getMessage());
			return false;
		}

		if(trans instanceof AtomicTransaction) {

			Account source = db.getAccount(((AtomicTransaction) trans).getSource());
			Account destination = db.getAccount(((AtomicTransaction) trans).getDestination());

			long time = System.currentTimeMillis();

			if((time < source.getLastTransaction() + 15000) || (time < destination.getLastTransaction() + 15000)) {
				System.out.println(ClientStrings.TRANS_LOCK);
				return false;
			}

			if(trans.process()) {
				System.out.println(ClientStrings.TRANS_SUCCESS);
				numTransactionsProcessed++;
				return true;
			}
			else {
				System.out.println(ClientStrings.TRANS_FAILURE);
				return false;
			}
		}
		else if(trans instanceof CompoundTransaction) {
			if(trans.process()) {
				System.out.println(ClientStrings.TRANS_SUCCESS);
				numTransactionsProcessed++;
				return true;
			}
			else {
				System.out.println(ClientStrings.TRANS_FAILURE);
				return false;
			}
		}
		else {
			System.out.print(ClientStrings.TRANS_INVALIDTYPE);
			return false;
		}
	}

	public int getCount() {
		return numTransactionsProcessed;
	}
}
