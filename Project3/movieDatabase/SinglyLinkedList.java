package movieDatabase;

public class SinglyLinkedList<T extends Movie> {
    
    private class Node {
        T data;
        Node next;
        
        public Node(T data) {
            this.data = data;
            next = null;
        }
    }

    private Node head;

    public SinglyLinkedList() {
        head = null;
    }

    public void insert(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public boolean search(Movie movie, String attribute) {
        Node current = head;
        while (current != null) {
            Movie currentMovie = (Movie) current.data;
            boolean found = false;
            switch(attribute.toLowerCase()){
                case "title":
                    found = currentMovie.getTitle().equalsIgnoreCase(movie.getTitle());                    
                    break;
                case "genre":
                    found = currentMovie.getGenre().equalsIgnoreCase(movie.getGenre());
                    break;
                case "director":
                    found = currentMovie.getDirector().equalsIgnoreCase(movie.getDirector());
                    break;
                case "year":
                    found = currentMovie.getYear() == movie.getYear();
                    break;
                default:
                    System.out.println("Invalid attribute.");
                    return false;
            }
            if (found) {
                System.out.println("Movie found: " + current.data);
                return true;
            }
            current = current.next;
        }
        return false;
    }
}
