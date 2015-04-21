/**
 * Created by Connor on 09/04/15.
 */

packagae um.edu.mt;

import org.junit.Assert;
import org.junit.Test;

public class MathlibTest {

    Mathlib calc = new Mathlib();
    
    @Test
    public void mathTest() {
        int add = calc.add(2,2);
        int mul = calc.mul(9, 2);
        long fact = calc.fact(10);

        Assert.assertEquals(4, add);
        Assert.assertEquals(18, mul);
        Assert.assertEquals(3628800, fact);
    }
}
