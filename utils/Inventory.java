package utils;

public class Inventory {
    public BinarySearchTree<Product> tree;
    public ProductList list;

    /**
     * Constructor of the inventory
     * @param tree Tree that keeps track of product names
     * @param list List that keeps track of Product names and stock
     */
    public Inventory(BinarySearchTree<Product> tree, ProductList list){
        this.tree = tree;
        this.list = list;
    }

    /**
     * Adds product to the Inventory
     * @param name name of Product to add
     * @param stock amount of stock to assign to that Product
     */
    public void addProduct(String name, int stock){
        Product product = new Product(name, stock);
        list.push(product.name, stock);
        tree.insert(product);
    }

    /**
     * Deletes a Product from the Inventory
     * @param product Product to delete
     * @return deleted Product if not found returns an empty Product
     */
    public String deleteProduct(Product product){
        String deleted = list.pop(product.getName());
        tree.dtree(tree.getRoot(), product);
        return deleted;
    }

    /**
     * Modifies the stock of a Product
     * @param name the Product to modify its stock
     * @param stock the amount of stock to add or subtract
     */
    public void modProduct(String name, int stock){
        if (list.adjustStock(name, stock))
            deleteProduct(new Product(name, stock));
    }

    /**
     * Searches for a Product on the Inventory
     * @param product the Product to search for
     * @return the found Product or an empty Product if not found
     */
    public Product searchProduct(String product){
        Product productSearch = list.findNode(product);
        if (productSearch.equals(new Product()))
            System.err.println("Product Not Found");
        return productSearch;
    }

    /**
     * Prints the Inventory in Tree form and List form
     */
    public void printProduct(){
        //BinaryTreePrinter<Product> print1 = new BinaryTreePrinter<Product>(tree,1);
        BinaryTreePrinter<Product> print2 = new BinaryTreePrinter<Product>(tree, 2);
        BinaryTreePrinter<Product> print3 = new BinaryTreePrinter<Product>(tree, 3);
        System.out.println("List: ");
        list.print();
    }
}
