package um.edu.mt;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

public class TestSuite {

	Account acc1 = new Account(0, "John Smith", 1000);
	Account acc2 = new Account(0, "John Doe", 500);
	Account acc3 = new Account(1, "Jane Doe", 10000); 
	
	//Adding an account and checking its fields
	@Test
	public void addAccount() {
		boolean ret = AccountDatabase.addAccount(acc1);
		
		Account test = AccountDatabase.getAccount(0);
		
		Assert.assertEquals(test.getNumber(), 0);
		Assert.assertEquals(test.getName(), "John Smith");
		Assert.assertEquals(test.getBalance(), 1000);
		
		Assert.assertEquals(ret, true);
	}
	
	//Forcing key collision in the hash table
	@Test
	public void keyCollision() {
		boolean ret = AccountDatabase.addAccount(acc2);
		
		Assert.assertEquals(ret, false);
	}
	
	//Adding a second account
	@Test
	public void addSecondAccount() {
		boolean ret = AccountDatabase.addAccount(acc3);
		
		Account test = AccountDatabase.getAccount(1);
		
		Assert.assertEquals(test.getNumber(), 1);
		Assert.assertEquals(test.getName(), "Jane Doe");
		Assert.assertEquals(test.getBalance(), 10000);
		
		Assert.assertEquals(ret, true);
	}
	
	//Retrieving an inexistent account number
	@Test
	public void getInvalidNumber() {
		Account ret = AccountDatabase.getAccount(70);
		
		Assert.assertEquals(ret, null);
	}
	
	//Transaction success
	@Test
	public void transactionSuccess() {
		boolean ret = TransactionManager.processTransaction(1, 0, 5000);
		
		Assert.assertEquals(acc1.getBalance(), 6000);
		Assert.assertEquals(acc3.getBalance(), 5000);
		Assert.assertEquals(TransactionManager.getCount(), 1);
		
		Assert.assertEquals(ret, true);
	}
	
	//Transaction with insufficient balance
	@Test
	public void transactionInsufficientBalance() {
		boolean ret = TransactionManager.processTransaction(0, 1, 10000);
		
		//Check that balances remain unchanged
		Assert.assertEquals(acc1.getBalance(), 6000);
		Assert.assertEquals(acc3.getBalance(), 5000);
		Assert.assertEquals(TransactionManager.getCount(), 1);
		
		Assert.assertEquals(ret, false);
	}
	
	//Transaction with invalid account number specified
	@Test
	public void transactionInvalid() {
		boolean ret = TransactionManager.processTransaction(0, 70, 10000);
		
		Assert.assertEquals(acc1.getBalance(), 6000);
		Assert.assertEquals(TransactionManager.getCount(), 1);
		
		Assert.assertEquals(ret, false);
	}
	
	//Transaction with negative amount
	@Test
	public void transactionNegative() {
		boolean ret = TransactionManager.processTransaction(0, 1, -10000);
		
		Assert.assertEquals(acc1.getBalance(), 6000);
		Assert.assertEquals(acc3.getBalance(), 5000);
		Assert.assertEquals(TransactionManager.getCount(), 1);
		
		Assert.assertEquals(ret, false);
	}
	
	//Another successful transaction
	@Test
	public void transactionCountTest() {
		boolean ret = TransactionManager.processTransaction(0, 1, 1000);
		
		Assert.assertEquals(acc1.getBalance(), 5000);
		Assert.assertEquals(acc3.getBalance(), 6000);
		Assert.assertEquals(TransactionManager.getCount(), 2);
		
		Assert.assertEquals(ret, true);
	}
}
