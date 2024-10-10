package movieDatabase;

public class BinarySearchTree<T extends Comparable<T>> {
    
    private class Node {
        T data;
        Node left, right;
        
        public Node(T data) {
            this.data = data;
            left = right = null;
        }
    }

    private Node root;
    
    public BinarySearchTree() {
        root = null;
    }
    
    public void insert(T data) {
        root = insertRec(root, data);
    }
    
    private Node insertRec(Node root, T data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }
        
        if (data.compareTo(root.data) < 0) {
            root.left = insertRec(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = insertRec(root.right, data);
        }
        return root;
    }
    
    public boolean searchByAttribute(Movie movie, String attribute) {
        return searchRec(root, movie, attribute);
    }

    private boolean searchRec(Node root, Movie movie, String attribute) {
        if (root == null) {
            System.out.println("Reached a leaf node, no movie found.");
            return false;
        }
        
        int comparisonResult = 0;
        
        switch(attribute.toLowerCase()){
            case "title":
                comparisonResult = ((Movie) root.data).compareTo(movie);
                break;
            case "genre":
                comparisonResult = ((Movie) root.data).compareByGenre(movie);
                break;
            case "director":
                comparisonResult = ((Movie) root.data).compareByDirector(movie);
                break;
            case "year":
                comparisonResult = ((Movie) root.data).compareByYear(movie);
                break;
            default:
                System.err.println("Invalid attribute.");
                return false;
        }


        if (comparisonResult == 0) {
            return true;
        } else if (comparisonResult > 0) {
            return searchRec(root.left, movie, attribute);
        } else {
            return searchRec(root.right, movie, attribute);
        }
    }
    
    public void inOrder() {
        inOrder(root);
    }
    
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }
    
}