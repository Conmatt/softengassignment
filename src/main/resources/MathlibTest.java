/**
 * Created by Connor on 09/04/15.
 */

import org.junit.Assert;
import org.junit.Test;

public class MathlibTest {

    @Test
    public void main(string[] args) {
        int add = Mathlib.add(2,2);
        int mul = Mathlib.mul(9, 2);
        long fact = Mathlib.fact(10);

        Assert.assertEquals(4, add);
        Assert.assertEquals(18, mul);
        Assert.assertEquals(3628800, fact);
    }

}
