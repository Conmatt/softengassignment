package um.edu.mt;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

public class TestSuite {

	Account acc1, acc2, acc3;
	AccountDatabase db;
	TransactionManager tsm;
	
	@Before
	public void initialise() {
		acc1 = new Account(0, "John Smith", 1000);
		acc2 = new Account(0, "John Doe", 500);
		acc3 = new Account(1, "Jane Doe", 10000); 
		
		 db = AccountDatabase.initialise();
		 tsm = new TransactionManager();
	}
	
	//Adding an account and checking its fields
	@Test
	public void addAccount() {
		boolean ret = db.addAccount(acc1);
		
		Account test = db.getAccount(0);
		
		Assert.assertEquals(0, test.getNumber());
		Assert.assertEquals("John Smith", test.getName());
		Assert.assertEquals(1000, test.getBalance());
		
		Assert.assertEquals(true, ret);
	}
	
	//Forcing key collision in the hash table
	@Test
	public void keyCollision() {
		boolean ret = db.addAccount(acc2);
		
		Assert.assertEquals(false, ret);
	}
	
	//Adding a second account
	@Test
	public void addSecondAccount() {
		boolean ret = db.addAccount(acc3);
		
		Account test = db.getAccount(1);
		
		Assert.assertEquals(1, test.getNumber());
		Assert.assertEquals("Jane Doe", test.getName());
		Assert.assertEquals(10000, test.getBalance());
		
		Assert.assertEquals(true, ret);
	}
	
	//Retrieving an inexistent account number
	@Test
	public void getInvalidNumber() {
		Account ret = db.getAccount(70);
		
		Assert.assertEquals(null, ret);
	}
	
	//Transaction success
	@Test
	public void transactionSuccess() {
		boolean ret = tsm.processTransaction(1, 0, 5000);
		
		Assert.assertEquals(6000, db.getAccount(0).getBalance());
		Assert.assertEquals(5000, db.getAccount(1).getBalance());
		Assert.assertEquals(1, tsm.getCount());
		
		Assert.assertEquals(true, ret);
	}
	
	//Transaction with insufficient balance
	@Test
	public void transactionInsufficientBalance() {
		boolean ret = tsm.processTransaction(0, 1, 10000);
		
		//Check that balances remain unchanged
		Assert.assertEquals(6000, db.getAccount(0).getBalance());
		Assert.assertEquals(5000, db.getAccount(1).getBalance());
		Assert.assertEquals(1, tsm.getCount());
		
		Assert.assertEquals(false, ret);
	}
	
	//Transaction with invalid account number specified
	@Test
	public void transactionInvalid() {
		boolean ret = tsm.processTransaction(0, 70, 10000);
		
		Assert.assertEquals(6000, db.getAccount(0).getBalance());
		Assert.assertEquals(1, tsm.getCount());
		
		Assert.assertEquals(false, ret);
	}
	
	//Transaction with negative amount
	@Test
	public void transactionNegative() {
		boolean ret = tsm.processTransaction(0, 1, -10000);
		
		refreshVars();
		Assert.assertEquals(6000, db.getAccount(0).getBalance());
		Assert.assertEquals(5000, db.getAccount(1).getBalance());
		Assert.assertEquals(1, tsm.getCount());
		
		Assert.assertEquals(false, ret);
	}
	
	//Another successful transaction
	@Test
	public void transactionCountTest() {
		boolean ret = tsm.processTransaction(0, 1, 1000);
		
		Assert.assertEquals(5000, db.getAccount(0).getBalance());
		Assert.assertEquals(6000, db.getAccount(1).getBalance());
		Assert.assertEquals(2, tsm.getCount());
		
		Assert.assertEquals(true, ret);
	}
}
