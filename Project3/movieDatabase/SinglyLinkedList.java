package movieDatabase;

public class SinglyLinkedList<T> {
    
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

    public boolean search(T data) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
}
