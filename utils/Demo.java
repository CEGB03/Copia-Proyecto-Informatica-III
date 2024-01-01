package utils;

import java.util.Scanner;

public class Demo {
    ProductList list = new ProductList();
    BinarySearchTree<Product> tree = new BinarySearchTree<Product>();
    Inventory inventory = new Inventory(tree, list);
    private char yes = 'y';
    private  final Scanner scanner = new Scanner(System.in);
    public Demo(){
        System.out.println("DEMONSTRATION");
        setDemo();
        inventory.printProduct();
        do {
            Options();
            System.out.println("Keep demonstration going? Y/N" );
            yes = scanner.nextLine().charAt(0);
        }while (yes == 'y');
    }
    public void Options(){
        String name;
        int stock = 0;
        System.out.println("Menu: ");
        System.out.println("1. Add Product");
        System.out.println("2. Search Product");
        System.out.println("3. Delete Product");
        System.out.println("4. Print Inventary");
        int op = Integer.parseInt(scanner.nextLine());
        switch (op) {
            case 1 -> {
                System.out.println("ADD");
                System.out.println("Enter the name of the product to add:");
                name = scanner.nextLine();
                System.out.println("Enter " + name + "'s stock quantity: ");
                stock = Integer.parseInt(scanner.nextLine());
                inventory.addProduct(name, stock);
                inventory.printProduct();
            }
            case 2 -> {
                System.out.println("SEARCH");
                System.out.println("Enter the name of the product to search: ");
                name = scanner.nextLine();
                Product search = inventory.searchProduct(name);
                if (!search.equals(new Product())) {
                    System.out.println("Product, Stock:");
                    search.printProduct();
                    System.out.println();
                    System.out.println("Do you want to modify " + search.name + "'s stock? Y/N");
                    yes = scanner.nextLine().charAt(0);
                    if (yes == 'y') {
                        System.out.println("Enter the stock quantity to modify");
                        int amount = Integer.parseInt(scanner.nextLine());
                        inventory.modProduct(search.getName(), amount);
                        System.out.println("[Product, Stock]:");
                        search.printProduct();
                        list.print();
                        System.out.println();
                    }
                }
                yes = 'y';
            }
            case 3 -> {
                System.out.println("DELETE");
                System.out.println("Enter the name of the product to delete: ");
                name = scanner.nextLine();
                String delete = inventory.deleteProduct(new Product(name, 0));
                if (!delete.equals("Product to delete not found")) {
                    System.out.println("Deleted Product: " + delete);
                    inventory.printProduct();
                }
            }
            case 4 -> {
                System.out.println("PRINT");
                inventory.printProduct();
            }
            default -> System.err.println("Wrong Option");
        }
    }
    public void setDemo(){
        inventory.addProduct("mate", 7);
        inventory.addProduct("cafe", 2);
        inventory.addProduct("harina", 5);
        inventory.addProduct("palmitos", 3);
        inventory.addProduct("yerba", 8);
        inventory.addProduct("mermelada", 4);
        inventory.addProduct("cacao", 6);
        inventory.addProduct("picadillo", 3);
        inventory.addProduct("pate", 5);
        inventory.addProduct("caballa", 2);
        inventory.addProduct("arroz", 3);
        inventory.addProduct("arvejas", 5);
        inventory.addProduct("sardinas", 1);
        inventory.addProduct("atun", 6);
        inventory.addProduct("choclo", 10);
        inventory.addProduct("lentejas", 9);
    }
}