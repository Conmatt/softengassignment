package um.edu.mt;

/**
 * Created by Connor on 28/05/15.
 */
public class AtomicTransaction extends Transaction {
    private int sourceAccountNumber, destinationAccountNumber;
    private long amount;

    public AtomicTransaction(int source, int destination, long amount) {
        this.sourceAccountNumber = source;
        this.destinationAccountNumber = destination;
        this.amount = amount;
        this.validated = false;
    }

    public int getSource() {
        return sourceAccountNumber;
    }

    public int getDestination() {
        return destinationAccountNumber;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public boolean process() {
        if(!validated) {
            System.out.println(ClientStrings.TRANS_VALIDATION);
            return false;
        }

        AccountDatabase db = AccountDatabase.getInstance();

        Account source = db.getAccount(sourceAccountNumber);
        Account destination = db.getAccount(destinationAccountNumber);

        source.adjustBalance(-this.amount);
        destination.adjustBalance(this.amount);

        long time = System.currentTimeMillis();
        source.setLastTransaction(time);
        destination.setLastTransaction(time);

        db.commit(source);
        db.commit(destination);

        return true;
    }

    //Checks for any invalid account numbers and amounts
    @Override
    public void validate() throws TransactionException {
        AccountDatabase db = AccountDatabase.getInstance();
        long sourceBalance = db.getAccount(sourceAccountNumber).getBalance();

        if(!(db.contains(sourceAccountNumber) && db.contains(destinationAccountNumber))) {
            throw new TransactionException(String.format(ClientStrings.TRANS_INVALIDACC, sourceAccountNumber, destinationAccountNumber));
        }
        else if(amount < 0) {
            throw new TransactionException(String.format(ClientStrings.TRANS_INVALIDFUNDS, sourceAccountNumber));
        }
        else if(sourceBalance < amount) {
            throw new TransactionException(String.format(ClientStrings.TRANS_NOFUNDS, sourceAccountNumber, sourceBalance));
        }
        else {
            validated = true;
        }
    }
}