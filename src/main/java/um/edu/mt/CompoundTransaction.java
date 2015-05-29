package um.edu.mt;

import java.util.ArrayList;

public class CompoundTransaction extends Transaction {
    private ArrayList<Transaction> transList = new ArrayList<Transaction>();

    public CompoundTransaction(Transaction trans) {
        transList.add(trans);
    }

    public void add(Transaction trans) {
        transList.add(trans);
    }

    @Override
    public boolean process() {
        boolean success = true;

        for(Transaction trans : transList) {
            if(trans instanceof AtomicTransaction) {
                try {
                    trans.validate();
                }
                catch (TransactionException t) {
                    System.out.println(t.getMessage());
                    success = false;
                    break;
                }

                trans.process();
            }
            else if(trans instanceof CompoundTransaction) {
                trans.process();
            }
        }

        return success;
    }

    @Override
    public void validate() throws TransactionException {
    }
}
