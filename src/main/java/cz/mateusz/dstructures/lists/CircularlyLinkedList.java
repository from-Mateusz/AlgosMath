package cz.mateusz.dstructures.lists;

import java.util.Objects;

public class CircularlyLinkedList<ContentType> {

    private static class Node<ContentType> {
        private ContentType content;

        private Node next;

        public Node(ContentType content, Node next) {
            this(content);
            this.next = next;
        }

        public Node(ContentType content) {
            this.content = content;
        }

        public ContentType getContent() {
            return content;
        }

        public void setNext(Node<ContentType> next) {
            this.next = next;
        }

        public Node<ContentType> getNext() {
            return next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(content, node.content);
        }

        @Override
        public int hashCode() {
            return Objects.hash(content);
        }
    }

    private Node<ContentType> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {}

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void rotate() {
        if(!isEmpty()) {
            tail = tail.getNext();
        }
    }

    public ContentType getFirst() {
        if(isEmpty()) return null;
        return tail.getNext().content;
    }

    public ContentType getLast() {
        if(isEmpty()) return null;
        return tail.content;
    }

    public void addFirst(ContentType content) {
        Node<ContentType> newNode = new Node<>(content, null);
        if(isEmpty()) {
            tail = newNode;
            tail.setNext(tail);
        }
        else {
            newNode.setNext(tail.getNext());
            tail.setNext(newNode);
        }
        size++;
    }

    public void addLast(ContentType content) {
        addFirst(content);
        rotate();
    }

    public ContentType removeFirst() {
        if(!isEmpty()) {
            Node<ContentType> head = tail.getNext();
            if(head == tail) {
                //There is only one element on a list
                tail = null;
            }
            else {
                tail.setNext(head.getNext());
            }
            size--;
            return head.getContent();
        }
        return null;
    }
}
