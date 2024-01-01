package utils;

import java.util.Scanner;

public class Menu {
    ProductList listStruct = new ProductList();
    BinarySearchTree<Product> tree = new BinarySearchTree<Product>();
    Inventory inventory = new Inventory(tree, listStruct);
    private final Scanner scanner = new Scanner(System.in);
    private String answer;
    private final char yesCapital = 'Y';
    private final char yesLower = 'y';
    private char repeat = ' ';
    public Menu(){
        System.out.println("Do you want to enter the Stock Control System? Y/N");
        do {
            answer = scanner.nextLine();
            repeat = answer.charAt(0);
            if (repeat == yesCapital || repeat == yesLower)
                systemStock();
            System.out.println("Do you want to continue in the Stock Control System? Y/N");
        } while (repeat == yesCapital || repeat == yesLower);

    }
    private void systemStock(){
        String nameProduct;
        int stockProduct;
        System.out.println("Menu:");
        System.out.println("1. Add Product");
        System.out.println("2. Search Product");
        System.out.println("3. Delete Product");
        System.out.println("4. Print Inventary");
        int option = Integer.parseInt(scanner.nextLine());
        switch (option) {
            case 1 -> {
                System.out.println("Option 1");
                System.out.println("ADD");
                System.out.println("Enter the name of the product to add:");
                nameProduct = scanner.nextLine();
                System.out.println("Enter " + nameProduct + "'s stock quantity:");
                stockProduct = Integer.parseInt(scanner.nextLine());
                inventory.addProduct(nameProduct, stockProduct);
            }
            case 2 -> {
                System.out.println("Option 2");
                System.out.println("SEARCH");
                System.out.println("Enter the name of the product to search:");
                nameProduct = scanner.nextLine();
                Product productSearch = inventory.searchProduct(nameProduct);
                if (!productSearch.equals(new Product())) {
                    System.out.println("[Product, Stock]:");
                    productSearch.printProduct();
                    System.out.println();
                    System.out.println("Do you want to modify " + productSearch.getName() + "'s stock? Y/N");
                    answer = scanner.nextLine();
                    repeat = answer.charAt(0);
                    if (repeat == yesCapital || repeat == yesLower) {
                        int amount;
                        System.out.println("Enter the stock quantity to modify");
                        amount = Integer.parseInt(scanner.nextLine());
                        inventory.modProduct(productSearch.getName(), amount);
                    }
                }
                repeat = 'y';
            }
            case 3 -> {
                System.out.println("Option 3");
                System.out.println("ELIMINATION");
                System.out.println("Enter the name of the product to delete:");
                nameProduct = scanner.nextLine();
                String deletedProduct = inventory.deleteProduct(new Product(nameProduct, 0));
                if (!deletedProduct.equals("Product to delete not found"))
                    System.out.println("Deleted Product: " + deletedProduct);
            }
            case 4 -> {
                System.out.println("Option 4");
                System.out.println("PRINT");
                inventory.printProduct();
            }
            default -> System.err.println("Wrong Option");
        }
    }
}
