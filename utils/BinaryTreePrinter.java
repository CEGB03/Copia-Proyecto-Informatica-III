package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryTreePrinter <AnyType extends Comparable<? super AnyType>>{
    /**
     * @param shape is the number of BinaryTreePrinter*/

    public BinaryTreePrinter(BinarySearchTree<AnyType> tree, int shape) {
        if (shape == 1) {
            BinaryTreePrinter1 p1 = new BinaryTreePrinter1();
            System.out.println("\nprint1");
            BinaryTreePrinter1.printNode(tree.getRoot());
        }
        if (shape == 2) {
            BinaryTreePrinter2 p2 = new BinaryTreePrinter2();
            System.out.println("\nprint1");
            p2.print((BinaryTreePrinter2.PrintableNode) tree.getRoot());
        }
        if (shape == 3){
            BinaryTreePrinter3 p3 = new BinaryTreePrinter3();
            System.out.println("\nprint2");
            p3.BinaryTreePrint3(tree);
        }
    }
}

class BinaryTreePrinter1 {
    public static <AnyType extends Comparable<? super AnyType>> void printNode(BinaryNode<AnyType> root) {
        int maxLevel = BinaryTreePrinter1.maxLevel(root);
        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }
    private static <AnyType extends Comparable<? super AnyType>> void printNodeInternal(List<BinaryNode<AnyType>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BinaryTreePrinter1.isAllElementsNull(nodes))
            return;
        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;
        BinaryTreePrinter1.printWhitespaces(firstSpaces);
        List<BinaryNode<AnyType>> newNodes = new ArrayList<BinaryNode<AnyType>>();
        for (BinaryNode<AnyType> node : nodes) {
            if (node != null) {
                System.out.print(node.getElem());
                newNodes.add(node.getLeft());
                newNodes.add(node.getRight());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }
            BinaryTreePrinter1.printWhitespaces(betweenSpaces);
        }
        System.out.println("");
        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BinaryTreePrinter1.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BinaryTreePrinter1.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }
                if (nodes.get(j).getLeft() != null)
                    System.out.print("/");
                else
                    BinaryTreePrinter1.printWhitespaces(1);
                BinaryTreePrinter1.printWhitespaces(i + i - 1);
                if (nodes.get(j).getRight() != null)
                    System.out.print("\\");
                else
                    BinaryTreePrinter1.printWhitespaces(1);
                BinaryTreePrinter1.printWhitespaces(endgeLines + endgeLines - i);
            }
            System.out.println("");
        }
        printNodeInternal(newNodes, level + 1, maxLevel);
    }
    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <AnyType extends Comparable<?>> int maxLevel(BinaryNode<AnyType> node) {
        if (node == null)
            return 0;

        return Math.max(BinaryTreePrinter1.maxLevel(node.getLeft()), BinaryTreePrinter1.maxLevel(node.getRight())) + 1;
    }

    private static <AnyType> boolean isAllElementsNull(List<AnyType> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }
}
class BinaryTreePrinter2 {
    /** Node that can be printed */
    public interface PrintableNode {
        /** Get left child */
        PrintableNode getLeftP();
        /** Get right child */
        PrintableNode getRightP();
        /** Get text to be printed */
        String getText();
    }
    /**
     * Print a tree
     * @param root
     */
    public void print(PrintableNode root)
    {
        List<List<String>> lines = new ArrayList<List<String>>();
        List<PrintableNode> level = new ArrayList<PrintableNode>();
        List<PrintableNode> next = new ArrayList<PrintableNode>();
        level.add(root);
        int nn = 1;
        int widest = 0;
        while (nn != 0) {
            List<String> line = new ArrayList<String>();
            nn = 0;
            for (PrintableNode n : level) {
                if (n == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.getText();
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();
                    next.add(n.getLeftP());
                    next.add(n.getRightP());
                    if (n.getLeftP() != null) nn++;
                    if (n.getRightP() != null) nn++;
                }
            }
            if (widest % 2 == 1) widest++;
            lines.add(line);
            List<PrintableNode> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }
        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;
            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {
                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);
                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {
                        for (int k = 0; k < hpw; k++)
                            System.out.print(j % 2 == 0 ? " " : "─");
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++)
                            System.out.print(j % 2 == 0 ? "─" : " ");
                    }
                }
                System.out.println();
            }
            // print line of numbers
            for (int j = 0; j < line.size(); j++) {
                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);
                // a number
                for (int k = 0; k < gap1; k++)
                    System.out.print(" ");
                System.out.print(f);
                for (int k = 0; k < gap2; k++)
                    System.out.print(" ");
            }
            System.out.println();
            perpiece /= 2;
        }
    }
}
class BinaryTreePrinter3 {
    public <AnyType extends Comparable<? super AnyType>> void BinaryTreePrint3(BinarySearchTree<AnyType> tree){
        System.out.println(toStringBinaryTree(tree.getRoot()));
    }
    private <AnyType extends Comparable<? super AnyType>> String toStringBinaryTree(BinaryNode<AnyType> root) {
        return toStringBinaryNode(new StringBuilder(), true, new StringBuilder(), root).toString();
    }
    private <AnyType extends Comparable<? super AnyType>>StringBuilder toStringBinaryNode(StringBuilder prefix, boolean isTail, StringBuilder sb,
                                                    BinaryNode<AnyType> root) {
        if(root.right!=null) {
            toStringBinaryNode(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb, root.right);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(root.elem.toString()).append("\n");
        if(root.left!=null) {
            toStringBinaryNode(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb, root.left);
        }
        return sb;
    }
}


