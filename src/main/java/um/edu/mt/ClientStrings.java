package um.edu.mt;

/**
 * Created by Connor on 28/05/15.
 */
public class ClientStrings {
    public static final String TRANS_LOCK = "One or more accounts are locked. Please wait before attempting another transaction!";
    public static final String TRANS_NOFUNDS = "Insufficient funds for Account #%d (Balance: €%d)";
    public static final String TRANS_INVALIDFUNDS = "Amount of funds to be transferred must be greater than or equal to zero! (SRC: %d)";
    public static final String TRANS_INVALIDACC = "Invalid account number(s) specified! (SRC: %d, DEST: %d)";
    public static final String TRANS_VALIDATION = "This transaction has not been validated!";
    public static final String TRANS_INVALIDTYPE = "Unrecognised transaction format!";
    public static final String TRANS_EMPTYLIST = "Cannot process a null compound transaction!";
    public static final String TRANS_SUCCESS = "Transaction completed successfully!";
    public static final String TRANS_FAILURE = "Transaction failed!";
    public static final String ACCOUNT_IDTAKEN = "This account number is unavailable! (#%d)";

}
