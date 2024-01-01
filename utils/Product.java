package utils;

public class Product implements Comparable<Product> {
    public String name;
    public int stock;
    public Product(String name, int stock){
        this.name = name;
        this.stock = stock;
    }
    public Product(){
        name = "null";
        stock = 0;
    }
    public int getStock() {
        return stock;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public void printProduct(){
        System.out.print("[" + name + ", " + stock + "]");
    }

    @Override
    public String toString(){
        return name;
    }

    public boolean equals(Product product){
        return this.name.equals(product.name);
    }
    /**
     * Compares Products by name
     * @param product the object to be compared.
     * @return the value 0 if name ==  product;
     * a value less than 0 if name < product;
     * and a value greater than 0 if name > prouct
     */
    @Override
    public int compareTo(Product product) {
        return Integer.compare(this.name.compareTo(product.name), 0);
    }
}
