import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

/**
 * Created by sigmund69 on 18.04.2016.
 */
public class TestPhaser {

    @Test
    public void getSquareSumTest() throws ExecutionException, InterruptedException {
        int[] values = { 3, 4, 5, 7, 2, 5 };
        ArrayPhaser arrayPhaser = new ArrayPhaser();

        long expected = 128;

        long actual = arrayPhaser.getSquareSum(values, 3);
        Assert.assertEquals(expected, actual);
        System.out.println("\nТест 1 завершен успешно.\n");

        actual = arrayPhaser.getSquareSum(values,7);
        Assert.assertEquals(expected,actual);
        System.out.println("\nТест 2 завершен успешно.");
    }
}
