public class Account {

	private int accountNumber;
	private String accountName;
	private long accountBalance;
	
	public Account(int number, String name, long balance) {
		this.accountNumber = number;
		this.accountName = name;
		this.accountBalance = balance;
	}
	
	public boolean adjustBalance(long amount) {
	}
}