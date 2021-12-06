package ibf2021.day1;

import java.io.Console;

public class FruitVending {
    public static void main(String[] args) {
        System.out.println("Fruit Vending Bot: Welcome to your shopping cart");
        Console cons = System.console();

        FruitBasket newBasket = new FruitBasket();
        
        while (true) {
            String userInput = "";
            userInput = cons.readLine("> ");
            String inputExtract =  userInput.trim().toLowerCase();

            if (userInput.toLowerCase().contains("list")) {
                if (newBasket.numFruitInCart() == 0) {
                    System.out.println("Fruit Vending Bot: Your cart is empty");
                } else {
                    newBasket.listCart();
                }
            } else if (inputExtract.contains("add")) {
                if (inputExtract.matches("add [a-z, ]*")) {
                    String removeAdd = inputExtract.replace("add ","");
                    String[] fruitList = removeAdd.split(",");
                    for (int numNewFruit= 0; numNewFruit < fruitList.length; numNewFruit++) {
                        String fruitName = fruitList[numNewFruit].trim();
                        if (newBasket.haveFruit(fruitName) == true) {
                            System.out.println("You have " + fruitName + " in your cart");
                        } else {
                            newBasket.addFruit(fruitName);
                        }
                    }
                } else {
                    System.out.println("Fruit Vending Bot: You have to type the fruit name you want to add, example 'add apple'");
                }
            } else if (inputExtract.contains("delete")) {
                String removeDelete = inputExtract.replace("delete ","");
                try {
                    int userChoice = (Integer.valueOf(removeDelete))-1;
                    newBasket.deleteFruit(userChoice);
                } catch (NumberFormatException eNF) {
                    System.out.println("Fruit Vending Bot: You have to type number for delete fruit choice, example 'delete 1'");
                }
            } else {
                System.out.println("Need Help? Please key 'list' to view cart, 'add' to add fruit to cart, 'delete' to remove fruit from cart");
            }
        }
        
    }
}
