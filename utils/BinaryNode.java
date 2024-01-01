package utils;


public class BinaryNode<AnyType> implements BinaryTreePrinter2.PrintableNode {
    public AnyType elem;
    public BinaryNode<AnyType> left;
    public BinaryNode<AnyType> right;
    private int balance;
    private int height;

    /**
     * Constructs a new BinaryNode with the provided element.
     * @param theElement The element to be stored in the BinaryNode.
     */
    public BinaryNode(AnyType theElement) {
        elem = theElement;
        left = right = null;
        balance = height = 0;
    }

    /**
     * Return the size of the binary tree rooted at t
     * @param t The root of the binary tree for which to calculate the size
     * @return The size of the binary tree rooted at the provided node
     */
    public static <AnyType> int size(BinaryNode<AnyType> t){
        if (t == null)
            return 0;
        else
            return 1 + size(t.left) + size(t.right);
    }

    /**
     * Return the height of the binary tree rooted at t
     * @param t The root of the binary tree for which to calculate the height.
     * @return The height of the binary tree rooted at the provided node, or -1 if the tree is empty.
     */
    public static <AnyType> int height(BinaryNode<AnyType> t){
        if (t == null)
            return -1;
        else
            return 1 + Math.max(height(t.left), height(t.right));
    }

    /**
     * Return a reference to a node that is the root of a
     * duplicate of the binary tree rooted at the current node
     * @return A reference to the root of a duplicated binary tree.
     */
    public BinaryNode<AnyType> duplicate(){
        BinaryNode<AnyType> root = new BinaryNode<AnyType>(elem);
        if (left != null)//if there's a left subtree
            root.left = left.duplicate();//duplicate attach
        if (right != null)//if there's a right subtree
            root.right = right.duplicate();//duplicate attach
        return root;//return resulting tree
    }

    /**
     * Updates the height of the node based on the heights of its left and right subtrees.
     */
    public void updateHeight() {
        int leftHeight = (left != null) ? left.getHeight() : -1;
        int rightHeight = (right != null) ? right.getHeight() : -1;
        height = 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Print tree rooted at current node using postorder traversal
     */
    void printPostOrder(){
        if (left != null)//left
            left.printPostOrder();
        if (right != null)//right
            right.printPostOrder();
        System.out.println(elem);//node
    }

    /**
     * Print tree rooted at current node using inorder traversal
     */
    void printInOrder(){
        if (left != null)//left
            left.printInOrder();
        System.out.println(elem);//node
        if (right != null)//right
            right.printInOrder();
    }

    /**
     * Print tree rooted at current node using preorder traversal
     */
    void printPreOrder(){
        System.out.println(elem);//Node
        if (left != null)
            left.printPreOrder();//Left
        if (right != null)
            right.printPreOrder();//right
    }

    /**
     * Retrieves the element stored in this node.
     * @return The element stored in the node.
     */
    public AnyType getElem() {
        return elem;
    }

    /**
     * Returns a reference to the left child node of this node.
     * @return The left child node of this node.
     */
    public BinaryNode<AnyType> getLeft() {return left;}

    /**
     * Returns a reference to the right child node of this node.
     * @return The right child node of this node.
     */
    public BinaryNode<AnyType> getRight() {return right;}

    /**
     * Returns the balance factor of this node.
     * @return The balance factor of this node.
     */
    public int getBalance() {return balance;}

    /**
     * Returns the height of this node in the binary tree.
     * @return The height of this node.
     */
    public int getHeight() {return height;}

    /**
     * Sets the left child node of this node.
     * @param left The left child node to set.
     */
    public void setLeft(BinaryNode<AnyType> left) {
        this.left = left;
    }

    /**
     * Sets the right child node of this node.
     * @param right The right child node to set.
     */
    public void setRight(BinaryNode<AnyType> right) {
        this.right = right;
    }

    /**
     * Sets the balance factor of this node.
     * @param balance The balance factor to set.
     */
    public void setBalance(int balance) {this.balance = balance;}

    /**
     * Get the left child node as a printable node.
     * @return The left child node as a printable node.
     */
    @Override
    public BinaryTreePrinter2.PrintableNode getLeftP() {
        return left;
    }

    /**
     * Get the right child node as a printable node
     * @return The right child node as a printable node.
     */
    @Override
    public BinaryTreePrinter2.PrintableNode getRightP() {
        return right;
    }

    /**
     * Get the text representation of the node's element.
     * @return The text representation of the node's element.
     */
    public String getText() {return elem.toString();}
}
