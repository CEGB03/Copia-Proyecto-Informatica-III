package utils;

public class ProductList {

    private ProductNode root = null;

    /**
     * Add element and stock to List or to modify the stock of a Product
     * Checking if element is already on the List
     * to add stock to it or adding element with stock
     * @param element is the name of the Product to add
     * @param stock is the stock to set or add to element
     */
    public void push(String element, int stock) {
        if(isEmpty()){
            ProductNode newNodo = new ProductNode(element, stock);
            newNodo.setNext(root);
            root = newNodo;
        } else if( !(findNode(element).name.compareTo(element) == 0) ) {
            ProductNode newNodo = new ProductNode(element, stock);
            //sort(root, newNodo);
            newNodo.setNext(root);
            root = newNodo;
            sort(root, searchLastNode());
        }
        else
            adjustStock(element, stock);
    }

    /**
     * Add or substract stock to existing element
     * this method is only used when the Product already exist
     * @param name of the Product to modify its stock
     * @param stock amount of stock to modify
     * @return if product has been deleted
     */
    public boolean adjustStock(String name, int stock){
        Product actual = findNode(name);
        if (actual.name.equals(name)){//found the element
            if (actual.getStock()+stock <= 0) { // stock ends up less or equal to 0
                System.out.println("Deleted " + pop(name) + " due to stock adjustment");
                return true;
            }
            else
                actual.setStock(actual.getStock() + stock);
        }
        return false;
    }

    /**
     * Search a Node with that name
     * @param name of Product to search for
     * @return Product found or last Product of the List if not found
     */
    public Product searchNode(String name){
        ProductNode actual = this.root;
        while (actual.getNext() != null && actual.elem.getName().compareTo(name) != 0)
            actual = actual.getNext();
        return actual.elem;
    }

    /**
     * Find a Node with that name
     * Searchs for a Product
     * and check if that is the Product we wanted
     * @param name of Product to find
     * @return the found Product or an empty Product
     */
    public Product findNode(String name){
        Product p = searchNode(name);
        if (p.getName().equals(name))//if finds the node
            return p;
        else
            return new Product();
    }

    /**
     * Searches for the last Node of the List
     * @return last Product of the List
     */
    public ProductNode searchLastNode(){
        ProductNode actual = this.root;
        while (actual.getNext() != null)
            actual = actual.getNext();
        return actual;
    }

    /**
     * Delete Node and return
     * @param name name of the Product to delete
     * @return deleted element's name or null if not found
     */
    public String pop(String name) {
        ProductNode temp = this.root;
        ProductNode prev = new ProductNode();
        if (temp != null && temp.elem.name.equals(name)) {
            root = temp.next;
            return temp.elem.name;
        }
        // Find the key to be deleted
        while (temp != null && !temp.elem.name.equals(name)) {
            prev = temp;
            temp = temp.next;
        }
        // If the key is not present
        if (temp == null) return "Product to delete not found";
        // Remove the node
        prev.next = temp.next;
        return temp.elem.name;
    }

    /**
     * @return if the List is empty
     */
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Prints the List
     */
    public void print() {
        if( !(isEmpty()) ) {
            ProductNode current = root;
            System.out.println("[Product, Stock]:");
            while (current.next != null) {
                current.elem.printProduct();
                System.out.print(" -->\n-->");
                current = current.getNext();
            }
            current.elem.printProduct();
            System.out.println();
        } else
            System.err.println("Lista de Productos Vacia.");
    }

    /**
     * Sorts the list based on the name
     * with QuickSort methood
     * @param start the root
     * @param ProductNode the last node to sort
     */
    void sort(ProductNode start, ProductNode ProductNode) {
        if (start.equals(new ProductNode()) || start.equals(ProductNode) || start.equals(ProductNode.next) || start == null)
            return;

        // Split list and partition recurse
        ProductNode pivot_prev = partitionLast(start, ProductNode);
        sort(start, pivot_prev);

        // If pivot is picked and moved to the start,
        // that means start and pivot is same
        // so pick from next of pivot
        if (pivot_prev.equals(new ProductNode()) && pivot_prev.equals(start))
            sort(pivot_prev.next, ProductNode);

        // If pivot is in between of the list,
        // start from next of pivot,
        //since we have pivot_prev, so we move two nodes
        else if (!pivot_prev.equals(new ProductNode()) && !pivot_prev.next.equals(new ProductNode()) && pivot_prev.next.next!=null)
            sort(pivot_prev.next.next, ProductNode);
    }
    // Takes first and last node,
    // but do not break any links in
    // the whole linked list
    ProductNode partitionLast(ProductNode start, ProductNode end) {
        if (start.equals(end) || start.equals(new ProductNode()) || end.equals(new ProductNode()))
            return start;

        ProductNode pivot_prev = start;
        ProductNode curr = start;
        Product pivot = end.elem;

        // iterate till one before the end,
        // no need to iterate till the end
        // because end is pivot
        while (start != end) {
            if (start.elem.name.compareTo(pivot.name) < 0) {
                // keep tracks of last modified item
                pivot_prev = curr;
                Product temp = curr.elem;
                curr.elem = start.elem;
                start.elem = temp;
                curr = curr.next;
            }
            start = start.next;
        }
        // Swap the position of curr i.e.
        // next suitable index and pivot
        Product temp = curr.elem;
        curr.elem = pivot;
        end.elem = temp;

        // Return one previous to current
        // because current is now pointing to pivot
        return pivot_prev;
    }
}