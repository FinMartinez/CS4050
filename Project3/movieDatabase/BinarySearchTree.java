package movieDatabase;

import java.util.ArrayList;
import java.util.List;

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

    public List<Movie> searchByAttribute(String attribute, String query) {
        List<Movie> searchResults = new ArrayList<>();
        searchRecAttribute(root, attribute, query, searchResults);
        return searchResults;
    }
    
    private void searchRecAttribute(Node root, String attribute, String query, List<Movie> searchResults) {
        if (root == null) {
            //System.out.println("Reached a leaf node, no movie found.");
            return;
        }
        
        Movie currentMovie = (Movie) root.data;
        boolean match = false;

        switch(attribute.toLowerCase()){
            case "title":
                match = currentMovie.getTitle().equalsIgnoreCase(query);
                break;
            case "genre":
                match = currentMovie.getGenre().equalsIgnoreCase(query);
                break;
            case "director":
                match = currentMovie.getDirector().equalsIgnoreCase(query);
                break;
            case "year":
                try {
                    int yearQuery = Integer.parseInt(query);
                    match = currentMovie.getYear() == yearQuery;
                } catch (NumberFormatException e) {
                    System.err.println("Invalid year format: " + query);
                    return;
                }
                break;
            default:
                System.err.println("Invalid attribute.");
                return;
        }

        if (match) {
            searchResults.add(currentMovie);
        }

        searchRecAttribute(root.left, attribute, query, searchResults);
        searchRecAttribute(root.right, attribute, query, searchResults);

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