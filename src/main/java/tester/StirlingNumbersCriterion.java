package tester;

import java.math.BigInteger;
import java.util.InputMismatchException;

public class StirlingNumbersCriterion {

    public static long func(long studentov, long komnat) {
        try {

            BigInteger n = BigInteger.valueOf(studentov);

            BigInteger k = BigInteger.valueOf(komnat);

            BigInteger constant = new BigInteger("0");
            BigInteger result = new BigInteger("1");
            BigInteger result2 = new BigInteger("500");

            int result3 = n.compareTo(result);
            int result4 = n.compareTo(result2);
            int result5 = k.compareTo(result);
            int result6 = k.compareTo(result2);
            int result7 = n.compareTo(constant);
            int result8 = k.compareTo(constant);


            if (result3 < 0 || result4 > 0) {
                System.err.println("studentov must be 1 to 500");
            }
            if (result5 < 0 || result6 > 0) {
                System.err.println("komnat must be 1 to 500");
            } else if (result7 > 0 && result4 < 0 && result8 > 0 && result6 < 0) {
                //System.out.println("The answer is: " + stirlingFunction(n, k));
                return Long.parseLong(stirlingFunction(n,k).toString());
            }
        } catch (InputMismatchException a) {
            System.err.println("The input must be an integer.");
        }
        return 0;
    }


    private static BigInteger stirlingFunction(BigInteger n, BigInteger k) {
        BigInteger d = new BigInteger("0").add(k);
        BigInteger a = BigInteger.ONE;

        if (k.compareTo(BigInteger.ONE) == 0 || k.equals(n)) {
            return BigInteger.ONE;
        } else {
            BigInteger x = (stirlingFunction(n.subtract(a), d));
            BigInteger y = (stirlingFunction(n.subtract(a), k.subtract(a)));

            return k.multiply(x).add(y);
        }
    }
}
