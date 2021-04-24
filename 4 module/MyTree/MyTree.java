package tk.sasetz;

import java.util.LinkedList;
import java.util.Queue;

public class MyTree<T extends Comparable<T>> {
    private final Node<T> root;

    public MyTree(T rootData) {
        root = new Node<T>(rootData);
    }

    public void add(T data) {
        this.root.addRecursive(data);
    }

    public int traverseWidth(T value) {
        Queue<Node<T>> nodes = new LinkedList<>();
        int sum = 0;
        nodes.add(root);

        while(!nodes.isEmpty()){
            Node<T> node = nodes.remove();
            if (node.value.compareTo(value) == 0) {
                break;
            }

            System.out.println(node.value);
            sum++;

            if(node.right != null) {
                nodes.add(node.right);
            }

            if(node.left != null) {
                nodes.add(node.left);
            }
        }

        return sum;
    }

    public static class Node<T extends Comparable<T>> {
        private final T value;
        private Node<T> parent;
        private Node<T> left;
        private Node<T> right;

        private Node(T value) {
            this.value = value;
        }

        private void addRecursive(T value) {
            if(this.value.compareTo(value) < 0)
                if(this.left == null)
                    this.left = new Node<T>(value);
                else
                    this.left.addRecursive(value);
            else if(this.right == null)
                this.right = new Node<T>(value);
            else
                this.right.addRecursive(value);
        }
    }
}
