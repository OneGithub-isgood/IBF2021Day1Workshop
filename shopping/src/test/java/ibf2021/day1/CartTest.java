package ibf2021.day1;

import org.junit.Test;

public class CartTest {
    @Test

    public void testNumFruit() {
        FruitBasket testQtyInBasket = new FruitBasket();
        testQtyInBasket.addFruit("durian");
        testQtyInBasket.addFruit("apple");
        testQtyInBasket.addFruit("orange");
        assert(testQtyInBasket.numFruitInCart() == 3);
        System.out.println("Correct count 3 item(s)");
    }

    public void testAdd() {
        FruitBasket testAddBasket = new FruitBasket();
        testAddBasket.addFruit("durian");
        testAddBasket.addFruit("apple");
        assert(testAddBasket.haveFruit("durian") == true);
        System.out.println("Successfully added 'durian'");
    }

    public void testDelete() {
        FruitBasket testDeleteBasket = new FruitBasket();
        testDeleteBasket.addFruit("durian");
        testDeleteBasket.addFruit("apple");
        testDeleteBasket.deleteFruit(1);
        assert(testDeleteBasket.haveFruit("apple") == false);
        System.out.println("Successfully remove 2nd item which is 'apple'");
    }
}
