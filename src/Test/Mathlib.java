/**
 * Created by Connor on 20/04/15.
 */
public class Mathlib {

    public Mathlib() {
    }

    public int add(int arg1, int arg2) {
        return arg1 + arg2;
    }

    public int mul(int arg1, int arg2) {
        return arg1 * arg2;
    }

    public long fact(int arg) {

        if(arg == 0) {
            return 0;
        }
        else {
            return fact(arg) * fact(arg-1);
        }
    }
}
