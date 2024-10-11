package movieDatabase;


//imports
import java.util.Iterator;

public class SinglyLinkedList<T extends Movie> implements Iterable<T> {
    
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

    public SinglyLinkedList<Movie> searchListAttribute(Movie searchMovie, String attribute) {
        SinglyLinkedList<Movie> matchingMovies = new SinglyLinkedList<>();
        Node current = head;

        while (current != null) {
            Movie currentMovie = (Movie) current.data;

            int comparisonResult = 0;

            switch(attribute.toLowerCase()){
                case "title":
                    comparisonResult = currentMovie.compareTo(searchMovie);                    
                    break;
                case "genre":
                    comparisonResult = currentMovie.getGenre().trim().toLowerCase()
                            .compareTo(searchMovie.getGenre().trim().toLowerCase()); 
                    break;
                case "director":
                    comparisonResult = currentMovie.getDirector().trim().toLowerCase()
                            .compareTo(searchMovie.getDirector().trim().toLowerCase()); 
                    break;
                case "year":
                    comparisonResult = Integer.compare(currentMovie.getYear(), searchMovie.getYear());
                    //comparisonResult = currentMovie.compareByYear(searchMovie); 
                    break;
                default:
                    System.out.println("Invalid attribute.");
                    return matchingMovies;
            }

            if (comparisonResult == 0) {
                matchingMovies.insert(currentMovie);
            }
            current = current.next;
        }
        return matchingMovies;
    }

    public Node getHead() {
        return head;
    }

    public boolean isEmpty() {
        return head == null;
    }
    //include iterator, but CoPilot might be lying to me
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}
