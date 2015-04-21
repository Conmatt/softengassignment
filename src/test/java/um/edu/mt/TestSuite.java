package um.edu.mt;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

public class TestSuite {

	@Before
	Account acc1 = new Account(0, "John Smith", 1000);
	
	@Test
	public void addAccount() {
		AccountDatabase.addAccount(acc1);
		
		Account test = AccountDatabase.getAccount(0);
		
		Assert.assertEquals(test.getNumber(), 0);
		Assert.assertEquals(test.getName(), "John Smith");
		Assert.assertEquals(test.getBalance(), 1000);
	}
}
