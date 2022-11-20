package bst;

import org.w3c.dom.Element;

import java.util.Iterator;

public class BinarySearchTree<Key extends Comparable<Key>, Value> implements Iterable<Key> {
    private Node root;
    private int size;
    protected Key deleteReturn;

    /**
     * Empty binary search tree.
     */
    public BinarySearchTree() {
        root = null;
        size = 0;
    }



    public void findAndPrint(Key target){
        if(find(target) != null) {
            System.out.println(find(target));
        } else{
            System.out.println("That room doesn't exist, remember it's case sensitive :)");
        }
    }
    public Value find(Key target) {
        return find(root, target);
    }
    private Value find(Node currentNode, Key target){
        if(target == null) return null;

        if(currentNode ==null) return null;

        int compareResult = target.compareTo(currentNode.key);
        if (compareResult == 0) return currentNode.data;
        else if (compareResult < 0)
            return find(currentNode.left, target);
        else return find(currentNode.right, target);
    }



    public boolean add(Key key, Value item){
        int oldSize = size;
        root = add(root, key, item);
        return oldSize != size;
    }

    private Node add(Node currentNode, Key key, Value item){
        if (currentNode == null) {
            size++;
            return new Node(key, item);
        }
        if (key.compareTo(currentNode.key) == 0){
            return currentNode;
        }
        if (key.compareTo(currentNode.key) < 0){
            // Go left, and store the left node in the left.
            currentNode.left = add(currentNode.left, key, item); //Check here for positioning issues in case of bugs
            return currentNode;
        }
        //else (item.compareTo(currentNode.data) > 0) {
        currentNode.right = add(currentNode.right, key, item);
        return currentNode;
        //}
    }

    public Key remove(Key target){
        root = remove(root, target);
        return root.key;
        // return deleteReturn;
    }
    private Node remove(Node currentNode, Key key) {
        if (currentNode == null) {
            deleteReturn = null;
            return currentNode;
        }
        int compareResult = key.compareTo(currentNode.key);
        if (compareResult < 0) {
            currentNode.left = remove(currentNode.left, key);
            return currentNode;

        } else if (compareResult > 0) {
            currentNode.right = remove(currentNode.right, key);
            return currentNode;

        } else {
            //deleteReturn = currentNode.data;
            deleteReturn = currentNode.key;

            if (currentNode.left == null) {
                return currentNode.right;

            } else if (currentNode.right == null) {
                return currentNode.left;
            } else {
                if (currentNode.left.right == null){
                    currentNode.data = currentNode.left.data;
                    currentNode.left = currentNode.left.left;
                    return currentNode;
                } else{
                    currentNode.data = findLargestChild(currentNode.left);
                    return currentNode;
                }
            }
        }
    }

    private Value findLargestChild(Node parent) {
        if(parent.right.right == null){
            Value returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        } else {
            return findLargestChild(parent.right);
        }
    }

    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node) {
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.println("\n" + node.data);
        inOrder(node.right);
    }

    public String toString() {
        if (root == null) return "null";
        return root.toString();
    }

    public int getSize(){
        return size;
    }

    @Override
    public Iterator<Key> iterator() {
        Iterator<Key> it = new Iterator<>() {
            private Node next;

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public Key next() {
                if (!hasNext()){
                    return null; //Trouble shoot here
                }
                //Utilizing the parent node we can traverse similar to a doubly linked list...
                Node r = next;

                if (next.right != null) {
                    next = next.right;
                    while (next.left != null)
                        next = next.left;
                    return r.key;
                }

                while (true) {
                    if (next.parent == null) {
                        next = null;
                        return r.key;
                    }
                    if (next.parent.left == next) {
                        next = next.parent;
                        return r.key;
                    }
                    next = next.parent;
                }
            }
        };
        return it;
    }



    protected class Node {
        Value data; // The data stored in the node
        Node left; // The root of the left subtree
        Node right; // The root of the right subtree
        Node parent; //By keeping track of the parent we can doubly link the nodes for the iterator
        Key key;

        Element value;
        int size; // The number of nodes in subtree

        public Node(Key key, Value data){
            this.key = key;
            this.data = data;
            left = right = parent = null;

        }


        @Override
        public String toString() {
                return "(" + left.key + " - " + key + " - " + right.key + ")";
                //return data.toString();

        }
    }
}
