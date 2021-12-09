package ibf2021.day1;

import java.io.Console;
import java.io.File;
import java.io.IOException;

public class FruitVending {
    public static void main(String[] args) throws IOException {

        if (null != args && args.length > 0) {
            File dbFile = new File(args[0]);
            if (!dbFile.exists()) {
                dbFile.mkdirs();
            }
        } else {
            //create directory folder 'db'
            String dir = "db";
            File dbFile = new File(dir);
            if (!dbFile.exists()) {
                dbFile.mkdirs();
            }
        }

        System.out.println("Fruit Vending Bot: Welcome to your shopping cart");
        Console cons = System.console();

        FruitBasket newBasket = new FruitBasket();
        ShoppingCartDB newCartDB = new ShoppingCartDB();
        
        while (true) {
            String userInput;
            userInput = cons.readLine("> ");
            String inputExtract =  userInput.trim().toLowerCase();

            if (newCartDB.loginName.isEmpty() && !userInput.toLowerCase().contains("login")) {
                System.out.println("Fruit Vending Bot: You have not logged in, please login existing or signup new account with your name, example 'login tom'");
            } else if (userInput.toLowerCase().contains("login")) {
                if (inputExtract.matches("login [a-z ]*")) {
                    String userName = inputExtract.replace("login ","");
                    newCartDB.userLogin(userName);
                    //newCartDB.loginName = userName;
                    //newCartDB.viewCartInDb(userName);
                    newBasket.addFruitDB(newCartDB.loginCart());
                } else {
                    System.out.println("Fruit Vending Bot: Login existing or signup new account, example 'login tom', your name should only contain alphabets");
                }
            } else {
                if (userInput.toLowerCase().contains("save")) {
                    newCartDB.saveCart(newCartDB.loginName, newBasket.itemCart());
                } else if (userInput.toLowerCase().contains("user")) {
                    newCartDB.userList();
                } else if (userInput.toLowerCase().contains("list")) {
                    if (newBasket.numFruitInCart() == 0 && newCartDB.numFruitInDb() == 0) {
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
                    System.out.println("Need Help? Please key 'login name' to login your existing account, 'list' to view cart, 'add fruit' to add fruit to cart, 'delete number' to remove fruit from cart");
                }
            }
        }
    }
}
