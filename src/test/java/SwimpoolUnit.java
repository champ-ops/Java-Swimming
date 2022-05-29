import org.junit.Assert;
import org.junit.Test;

public class SwimpoolUnit {
    @Test
    public void zeroGroup2CustomersTest() {
        Manager manager = new Manager(5);
        manager.registerGroup(2);

        int[] expectTwoSeats = {0, 0, -1, -1, -1};

        Assert.assertArrayEquals(expectTwoSeats, manager.state());
    }


    @Test
    public void threeGroupCustomersTest() {
        Manager manager = new Manager(5);
        manager.registerGroup(2);
        manager.registerGroup(1);
        manager.registerGroup(1);

        int[] expectTwoSeats = {0, 0, 1, 2, -1};

        Assert.assertArrayEquals(expectTwoSeats, manager.state());
    }

    @Test
    public void negativeCountTest() {
        Manager manager = new Manager(5);

        Assert.assertThrows(IllegalArgumentException.class, () -> { manager.registerGroup(-2); });
    }
}
