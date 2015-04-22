package um.edu.mt;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

public class TestSuite {

	AccountDatabase db = AccountDatabase.initialise();
	TransactionManager tsm;
	
	@Before
	public void initialise() {
		 db.reset();
		 tsm = new TransactionManager();
		 
		 db.addAccount(new Account(0, "John Doe", 1000));
		 db.addAccount(new Account(1, "Jane Doe", 10000));
	}
	
	//Testing account insertion
	@Test
	public void addAccount() {		
		Account test1 = db.getAccount(0);
		Account test2 = db.getAccount(1);
		
		Assert.assertEquals(0, test1.getNumber());
		Assert.assertEquals("John Doe", test1.getName());
		Assert.assertEquals(1000, test1.getBalance());
		
		Assert.assertEquals(1, test2.getNumber());
		Assert.assertEquals("Jane Doe", test2.getName());
		Assert.assertEquals(10000, test2.getBalance());
		
		Assert.assertEquals(2, db.getSize());
	}
	
	//Forcing key collision in the hash table
	@Test
	public void keyCollision() {
		boolean ret = db.addAccount(new Account(0, "John Doe", 1000));
		
		Assert.assertEquals(false, ret);
	}
	
	//Retrieving an inexistent account number
	@Test
	public void getInvalidNumber() {
		Account ret = db.getAccount(70);
		
		Assert.assertEquals(null, ret);
	}
	
	//Transaction with insufficient balance
	@Test
	public void transactionInsufficientBalance() {
		boolean ret = tsm.processTransaction(0, 1, 10000);
		
		//Check that balances remain unchanged
		Assert.assertEquals(1000, db.getAccount(0).getBalance());
		Assert.assertEquals(10000, db.getAccount(1).getBalance());
		Assert.assertEquals(0, tsm.getCount());
		
		Assert.assertEquals(false, ret);
	}
	
	//Transaction with invalid account number specified
	@Test
	public void transactionInvalid() {
		boolean ret = tsm.processTransaction(0, 70, 10000);
		
		Assert.assertEquals(1000, db.getAccount(0).getBalance());
		Assert.assertEquals(0, tsm.getCount());
		
		Assert.assertEquals(false, ret);
	}
	
	//Transaction with negative amount
	@Test
	public void transactionNegative() {
		boolean ret = tsm.processTransaction(0, 1, -10000);
		
		Assert.assertEquals(1000, db.getAccount(0).getBalance());
		Assert.assertEquals(10000, db.getAccount(1).getBalance());
		Assert.assertEquals(0, tsm.getCount());
		
		Assert.assertEquals(false, ret);
	}
	
	@Test
	public void multipleTransactionTestFail() {
		boolean ret1 = tsm.processTransaction(0, 1, 1000);
		
		Assert.assertEquals(0, db.getAccount(0).getBalance());
		Assert.assertEquals(11000, db.getAccount(1).getBalance());
		Assert.assertEquals(1, tsm.getCount());
		
		//This should fail since 15 seconds have not elapsed
		boolean ret2 = tsm.processTransaction(1, 0, 1000);
		
		Assert.assertEquals(0, db.getAccount(0).getBalance());
		Assert.assertEquals(11000, db.getAccount(1).getBalance());
		Assert.assertEquals(1, tsm.getCount());
		
		Assert.assertEquals(true, ret1);
		Assert.assertEquals(false, ret2);
		
		try {
			Thread.sleep(15000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void multipleTransactionTestSuccess() {
		boolean ret1 = tsm.processTransaction(0, 1, 1000);
		
		Assert.assertEquals(0, db.getAccount(0).getBalance());
		Assert.assertEquals(11000, db.getAccount(1).getBalance());
		Assert.assertEquals(1, tsm.getCount());
		
		try {
			Thread.sleep(15000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		boolean ret2 = tsm.processTransaction(1, 0, 1000);
		
		Assert.assertEquals(1000, db.getAccount(0).getBalance());
		Assert.assertEquals(10000, db.getAccount(1).getBalance());
		Assert.assertEquals(2, tsm.getCount());
		
		Assert.assertEquals(true, ret1);
		Assert.assertEquals(true, ret2);
	}
}
