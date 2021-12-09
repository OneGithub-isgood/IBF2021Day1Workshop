package ibf2021.day1;

import java.util.ArrayList;
import java.util.List;

public class FruitBasket {
    List<String> fruitCart = new ArrayList<>();

    public void listCart() {
        for (int fruitPosition= 0; fruitPosition < fruitCart.size(); fruitPosition++) {
            Integer numFruit = fruitPosition+1;
            System.out.println("Fruit Vending Bot: " + numFruit + ". " + fruitCart.get(fruitPosition));
        }
    }

    public List<String> itemCart() {
        return fruitCart;
    }

    public Boolean haveFruit(String fruit) {
        if (fruitCart.contains(fruit)) {
            return true;
        } else {
            return false;
        }
    }

    public int numFruitInCart(){
        return fruitCart.size();
    }

    public void addFruit(String fruit) {
        fruitCart.add(fruit);
        System.out.println("Fruit Vending Bot: " + fruit + " added to cart");
    }

    public void addFruitDB(List basket) {
        this.fruitCart = basket;
    }

    public void deleteFruit(int userChoice) {
        try {
            String choiceFruit = fruitCart.get(userChoice);
            fruitCart.remove(userChoice);
            System.out.println("Fruit Vending Bot: " + choiceFruit + " removed from cart");
        } catch (IndexOutOfBoundsException eOOB) {
            System.out.println("Fruit Vending Bot: Incorrect item index");
        }
    }
}
