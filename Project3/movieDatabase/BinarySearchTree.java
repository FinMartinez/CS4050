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

    //public Movie searchByAttribute(Movie movie, String attribute)
    public Movie searchByAttribute(String attribute, String query) {
        //return searchRecAttribute(root, movie, attribute);
        return searchByAttribute(root, attribute, query);
    }
    
    //private Movie searchRecAttribute(Node root, Movie movie, String attribute)
    private Movie searchRecAttribute(Node root, String attribute, String query) {
        if (root == null) {
            System.out.println("Reached a leaf node, no movie found.");
            return null;
        }
        
        int comparisonResult = 0;
        Movie currentMovie = (Movie) root.data;

        switch(attribute.toLowerCase()){
            case "title":
                //comparisonResult = ((Movie) root.data).compareTo(movie);
                comparisonResult = currentMovie.getTitle().compareToIgnoreCase(query);
                break;
            case "genre":
                //comparisonResult = ((Movie) root.data).compareByGenre(movie);
                comparisonResult = currentMovie.getGenre().compareToIgnoreCase(query);
                break;
            case "director":
                //comparisonResult = ((Movie) root.data).compareByDirector(movie);
                comparisonResult = currentMovie.getDirector().compareToIgnoreCase(query);
                break;
            case "year":
                //comparisonResult = ((Movie) root.data).compareByYear(movie);
                try {
                    int yearQuery = Integer.parseInt(query);
                    comparisonResult = Integer.compare(currentMovie.getYear(), yearQuery);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid year format: " + query);
                    return null;
                }
                break;
            default:
                System.err.println("Invalid attribute.");
                return null;
        }


        if (comparisonResult == 0) {
            //return (Movie) root.data;
            return currentMovie;
        } else if (comparisonResult > 0) {
            //return searchRecAttribute(root.left, movie, attribute);
            return searchRecAttribute(root.left, attribute, query);
        } else {
            //return searchRecAttribute(root.right, movie, attribute);
            return searchRecAttribute(root.right, attribute, query);
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