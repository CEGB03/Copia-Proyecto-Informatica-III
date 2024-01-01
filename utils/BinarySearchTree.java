package utils;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>{
    private BinaryNode<AnyType> root;
    public BinarySearchTree(){
        root = null;
    }
    public void insert(AnyType x){
        try {
            root = insert(x, root);
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
    public void remove(AnyType x){
        try {
            root = remove(x, root);
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
    public void removeMin(){
        try {
            root = removeMin(root);
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
    public AnyType findMin(){
        return elementAt(findMin(root));
    }
    public AnyType findMax(){
        return elementAt(findMax(root));
    }
    public AnyType find(AnyType x){
        return elementAt(find(x, root));
    }
    /**
     * Internal method to get element field
     * @param t the node
     * @return the element field or null if t is null
     */
    private AnyType elementAt(BinaryNode<AnyType> t){
        return t == null ? null : t.elem;
    }
    /**
     * Internal method to find an item in a subtree
     * @param x is item to search for
     * @param t the node that roots the tree
     * @return node containing the matched item
      */
    private BinaryNode<AnyType> find(AnyType x, BinaryNode<AnyType> t){
        while (t != null){
            if (x.compareTo(t.elem) < 0)
                t = t.left;
            else if (x.compareTo(t.elem) > 0)
                t = t.right;
            else
                return t;//match
        }
        return null;//not found
    }
    /**
     * Internal method to find the smallest item in a subtree
     * @param t the node that roots the tree
     * @return node containing the smallest item
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t){
        if (t != null)
            while (t.left != null)
                t = t.left;
        return t;
    }
    /**
     * Internal method to find the largest item in a subtree
     * @param t the node that roots the tree
     * @return node containing the largest item
     */
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t){
        if (t != null)
            while (t.right != null)
                t = t.right;
        return t;
    }
    /**
     * Internal method to insert into a subtree
     * @param x the item to insert
     * @param t the node that roots the tree
     * @return the new root
     */
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t){
        if (t == null)
            t = new BinaryNode<AnyType>(x);
        else if (x.compareTo(t.elem) < 0)
            t.left = insert(x, t.left);
        else if (x.compareTo(t.elem) > 0)
            t.right = insert(x, t.right);
        else
            // Lets the element be duplicated
            System.err.println("Duplicate element: " + x.toString());
        // Update balance and perform rotations
        t.setBalance(Math.max(t.height(t.getLeft()), t.height(t.getRight())) + 1);
        return balance(t);
    }

    /**
     * Makes sure the tree is balanced
     * @param node root of the tree
     * @return the root of the balaced tree
     */
    private BinaryNode<AnyType> balance(BinaryNode<AnyType> node) {
        int balance = getBalance(node);
        if (balance > 1) {
            if (getBalance(node.getLeft()) < 0)
                node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }
        if (balance < -1) {
            if (getBalance(node.getRight()) > 0)
                node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }
        return node;
    }

    private int getBalance(BinaryNode<AnyType> node) {
        if (node == null)
            return 0;
        return BinaryNode.height(node.getLeft()) - BinaryNode.height(node.getRight());
    }

    private BinaryNode<AnyType> rightRotate(BinaryNode<AnyType> y) {
        BinaryNode<AnyType> x = y.getLeft();
        BinaryNode<AnyType> T2 = x.getRight();

        x.setRight(y);
        y.setLeft(T2);

        y.setBalance(Math.max(BinaryNode.height(y.getLeft()), BinaryNode.height(y.getRight())) + 1);
        x.setBalance(Math.max(BinaryNode.height(x.getLeft()), BinaryNode.height(x.getRight())) + 1);

        return x;
    }

    private BinaryNode<AnyType> leftRotate(BinaryNode<AnyType> x) {
        BinaryNode<AnyType> y = x.getRight();
        BinaryNode<AnyType> T2 = y.getLeft();

        y.setLeft(x);
        x.setRight(T2);

        x.setBalance(Math.max(BinaryNode.height(x.getLeft()), BinaryNode.height(x.getRight())) + 1);
        y.setBalance(Math.max(BinaryNode.height(y.getLeft()), BinaryNode.height(y.getRight())) + 1);

        return y;
    }
    /**
     * Internal method to remove minimum item from subtree
     * @param t the node that roots the tree
     * @return the new root
     * @throws ItemNotFoundException if t is empty*/
    private BinaryNode<AnyType> removeMin(BinaryNode<AnyType> t) throws ItemNotFoundException {
        if (t == null)
            throw new ItemNotFoundException();
        else if (t.left != null){
            t.left = removeMin(t.left);
            return t;
        }else
            return t.right;
    }
    /**
     * Internal method to remove from subtree
     * @param x the item to remove
     * @param t the node that roots the tree
     * @return the new root
     * @throws ItemNotFoundException if x is not found
     */
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) throws ItemNotFoundException {
        if (t == null)
            throw new ItemNotFoundException();
        if (x.compareTo(t.elem) < 0)
            t.left = remove(x, t.left);
        else if (x.compareTo(t.elem) > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null){//two children
            t.elem = findMin(t.right).elem;
            t.right = removeMin(t.right);
        }else
            t = (t.left != null) ? t.left : t.right;
        return t;
    }
    public BinaryNode<AnyType> getRoot() {
        return root;
    }
    public int size(){
        return BinaryNode.size(root);
    }
    /**
     * root's height
     */
    public int height(){
        return BinaryNode.height(root);
    }
    public int depth(){
        return height()+1;
    }
    public void printPreOrder(){
        if (root != null)
            root.printPreOrder();
    }
    public void printInOrder(){
        if (root != null) {
            System.out.println("Root: "+root.elem);
            root.printInOrder();
        }
    }
    public void printPostOrder(){
        if (root != null)
            root.printPostOrder();
    }
    public void makeEmpty(){
        root = null;
    }
    public boolean isEmpty(){
        return root == null;
    }
    /**
     * Searches for the node to delete
     * if not found prints a message
     * @param root root of the tree
     * @param delete node to delete
     * @return deleted node
     */
    public BinaryNode<AnyType> dtree(BinaryNode<AnyType> root, AnyType delete){
        BinaryNode<AnyType> temp;
        if (root == null){
            System.err.println("Delete Item Not Found");
            return root;
        }
        if (root.elem.compareTo(delete) > 0)
            root.left = dtree(root.left, delete);
        else if (root.elem.compareTo(delete) < 0)
            root.right = dtree(root.right, delete);

        // found the node
        else {
            //if one of the branches is null
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            //if the node has both branches
            //find the next in order
            root.elem = findMin(root.right).elem;
            //delete branch in order
            root.right = dtree(root.right, root.elem);
        }
        return root;
    }


}
