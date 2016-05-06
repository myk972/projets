package modele;

import org.junit.Assert;
import org.junit.Test;

public class ItemTest {

    private static final String PRODUCT_NAME = "lait";
    private static final String PERISHABLE_PRODUCT_NAME = "lait!";

    @Test
    public void constructProductTest() {
        Item item = new Item(PRODUCT_NAME);
        Assert.assertEquals(PRODUCT_NAME, item.getName());
        Assert.assertFalse(item.isPerishable());
    }

    @Test
    public void constructPerishableProductTest() {
        Item item = new Item(PERISHABLE_PRODUCT_NAME);
        Assert.assertEquals(PERISHABLE_PRODUCT_NAME, item.getName());
        Assert.assertTrue(item.isPerishable());
    }
}