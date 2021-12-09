package ibf2021.day1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDB {
    FruitBasket newBasket = new FruitBasket();
    List<String> accountCart = new ArrayList<>();
    String loginName = "";

    public int numFruitInDb(){
        return accountCart.size();
    }

    public List<String> loginCart() {
        return accountCart;
    }
    
    public void userLogin(String name) throws IOException {
        String userDbFile = "cartdb/" + name + ".db.txt";
        File dbFile = new File(userDbFile);
        Path dbFilePath = Paths.get(userDbFile);
        if (!loginName.equals(name)) {
            accountCart.clear();
        }
        try (Reader newReader = new FileReader(dbFile)) {
            viewCartInDb(name);
        } catch (FileNotFoundException fnf) {
            Path newFilePath = Files.createFile(dbFilePath);
            File newDbFile = newFilePath.toFile();
            try (BufferedWriter newBW = new BufferedWriter(new FileWriter(newDbFile, true))) {
                newBW.write("cartdb/" + name + ".db");
                newBW.newLine();
                System.out.println("Fruit Vending Bot: Your new account has been created, you can type login " + name + " to view your saved cart");
            }
            System.out.println("Fruit Vending Bot: Created new DB file to store your cart items in your account");
        }
        loginName = name;
    }

    public void viewCartInDb(String name) throws IOException {
        String userDbFile = "cartdb/" + name + ".db.txt";
        String dbLine;
        File dbFile = new File(userDbFile);
        try (Reader newReader = new FileReader(dbFile)) {
            BufferedReader newBR = new BufferedReader(newReader);
            while (null != (dbLine = newBR.readLine())) {
                if (dbLine != null && dbLine.contains(".db")) {
                    continue;
                } else {
                    accountCart.add(dbLine);
                }
            }
        } catch (FileNotFoundException fnf2) {}
        if (accountCart.size() == 0) {
            System.out.println("Fruit Vending Bot: " + name + ", your account cart is empty");
        } else {
            System.out.println("Fruit Vending Bot: " + name + ", your account cart contains the following items");
            for (int fruitPosition= 0; fruitPosition < accountCart.size(); fruitPosition++) {
                Integer numFruit = fruitPosition+1;
                System.out.println("Fruit Vending Bot: " + numFruit + ". " + accountCart.get(fruitPosition));
            }
        }
    }

    public void saveCart(String name, List basket) throws IOException {
        String userDbFile = "cartdb/" + name + ".db.txt";
        File dbFile = new File(userDbFile);
        this.accountCart = basket;
        System.out.println(basket.toString());
        try (BufferedWriter newBW = new BufferedWriter(new FileWriter(dbFile))) {
            newBW.write("cartdb/" + name + ".db");
            newBW.newLine();
            for (int fruitPosition= 0; fruitPosition < basket.size(); fruitPosition++) {
                newBW.write(basket.get(fruitPosition).toString());
                newBW.newLine();
            }
            System.out.println("Fruit Vending Bot: Your account cart has been saved");
        }
    }

    public void userList() {
        File folderDb = Paths.get("cartdb").toFile();
        System.out.println("The following users are registered");
        Integer numLine = 1;
        for (File dbFile: folderDb.listFiles()) {
            String fileName = dbFile.getName();
            String userName = fileName.replace(".db.txt","");
            System.out.println("Fruit Vending Bot: " + numLine + ". " + userName);
            numLine += 1;
        }
    }
}
