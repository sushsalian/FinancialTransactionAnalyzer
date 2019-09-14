import com.codingchallenge.Result;
import com.codingchallenge.TransactionUtil;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class TransactionUtilTests {


    @Test
    public void test_input_outside_time_range_expect_balance_0() {
        Result result = TransactionUtil.getResult("src/test/resources/test1.txt", "ACC334455",
                "22/10/2018 12:47:55", "25/10/2018 12:47:55");
        assertEquals("", Double.valueOf(0.0), result.getRelativeBalance());
    }

    @Test
    public void test_input_no_transactions_for_account_expect_balance_0() {
        Result result = TransactionUtil.getResult("src/test/resources/test1.txt", "ACC334456",
                "22/10/2018 12:47:55", "25/10/2018 12:47:55");
        assertEquals("", Double.valueOf(0.0), result.getRelativeBalance());
    }



    @Test
    public void test_input_transactions_for_account_expect_negative_balance() {
        Result result = TransactionUtil.getResult("src/test/resources/test1.txt", "ACC334455",
                "20/10/2018 12:00:00", "21/10/2018 19:00:00");
        assertEquals("Balance", Double.valueOf(-32.25), result.getRelativeBalance());
        assertEquals("Transactions", 2, result.getTotalTransactions());
    }

    @Test
    public void test_input_transactions_for_account_expect_positive_balance() {
        Result result = TransactionUtil.getResult("src/test/resources/test1.txt", "ACC778899",
                "20/10/2018 12:00:00", "21/10/2018 19:00:00");
        assertEquals("Balance", Double.valueOf(37.25), result.getRelativeBalance());
        assertEquals("Transactions", 3, result.getTotalTransactions());
    }

    @Test
    public void test_input_transactions_for_account_with_no_reversal_expect_negative_balance() {
        Result result = TransactionUtil.getResult("src/test/resources/test2_no_reversal.txt",
                "ACC334455",
                "20/10/2018 12:00:00", "21/10/2018 19:00:00");
        assertEquals("Balance", "-$53.25", result.getRelativeBalanceAsString());
        assertEquals("Transactions", 4, result.getTotalTransactions());
    }


}
