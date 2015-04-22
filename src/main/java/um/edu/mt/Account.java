package um.edu.mt;

import java.util.Date;

public class Account {

	private int accountNumber;
	private String accountName;
	private long accountBalance;
	private long lastTransaction;
	
	public Account(int number, String name, long balance) {
		this.accountNumber = number;
		this.accountName = name;
		this.accountBalance = balance;
        this.lastTransaction = -1;
	}
	
	public int getNumber() {
		return accountNumber;
	}
	
	public String getName() {
		return accountName;
	}
	
	public long getBalance() {
		return accountBalance;
	}
    
    public long getLastTransaction() {
        return lastTransaction;
    }
	
	public void setLastTransaction(long time) {
		lastTransaction = time;
	}
	
	/*
	public boolean isLocked() { //Checks whether the account has been involved in a transaction in the past fifteen seconds
	}
     */
	
	public boolean adjustBalance(long amount) {
        long newBalance = this.accountBalance + amount;
        
        if(newBalance < 0) {
            return false;
        }
        else {
            this.accountBalance = newBalance;
            return true;
        }
	}
}
