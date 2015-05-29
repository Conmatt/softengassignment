package um.edu.mt;

public abstract class Transaction {

	protected boolean validated;

	public abstract boolean process();

	public abstract void validate() throws TransactionException;
}
