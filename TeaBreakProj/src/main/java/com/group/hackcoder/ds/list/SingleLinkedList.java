package com.group.hackcoder.ds.list;

public class SingleLinkedList {

    private Node head = null;
    private int size;

    public static void main(String[] args) {
        SingleLinkedList sl = new SingleLinkedList();
        sl.add(12);
        sl.add(3);
        sl.add(9);
        sl.add(2);
        sl.add(15);

        System.out.println("sl list : " + sl);
    }

    public void addFirst(int dataItem) {
        head = new Node(dataItem, head);
        size++;
    }

    private void addAfter(Node node, int dataItem) {
        node.next = new Node(dataItem, node.next);
        size++;
    }

    private int removeAfter(Node node) {
        Node temp = node.next;
        if (temp != null) {
            node.next = temp.next;
            size--;
            return temp.data;
        } else {
            return 0;
        }
    }

    private int removeFirst() {
        Node temp = head;
        if (head != null) {
            head = head.next;
        }
        if (temp != null) {
            size--;
            return temp.data;
        } else {
            return 0;
        }
    }

    private Node getNode(int index) {
        Node node = head;
        for (int i = 0; i < index && node != null; i++) {
            node = node.next;
        }
        return node;
    }

    private Node getSortedNode(int index, int dataItem) {
        Node node = head;
        for (int i = 0; i <= index && node != null; i++) {
            if (dataItem < node.data) {
                Node temp = new Node(dataItem,null);
                temp.next = node;
                head = temp;
            }
            node = node.next;
        }
        return node;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
//            throw new IndexOutOfBoundsException(Integer.toString(index));
            return 0;
        }
        Node node = getNode(index);
        return node.data;
    }

    public int set(int index, int newValue) {
        if (index < 0 || index >= size) {
//            throw new IndexOutOfBoundsException(Integer.toString(index));
            return 0;
        }
        Node node = getNode(index);
        int result = node.data;
        node.data = newValue;
        return result;
    }

    public void add(int index, int item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        if (index == 0) {
            addFirst(item);
        } else {
//            Node node = getNode(index - 1);
            Node node = getSortedNode(index, item);
            if (index - 1 != 0) {
                addAfter(node, item);
            }
        }
    }

    public boolean add(int item) {
        add(size, item);
        return true;
    }

    @Override
    public String toString() {
        Node nodeRef = head;
        StringBuilder sb = new StringBuilder();
        while (nodeRef != null) {
            sb.append(nodeRef.data);
            if (nodeRef.next != null) {
                sb.append(" ==>");
            }
            nodeRef = nodeRef.next;
        }
        return sb.toString();
    }

    private static class Node {

        private int data;
        private Node next;

        private Node(int dataItem) {
            this.data = dataItem;
            this.next = null;
        }

        private Node(int dataItem, Node nodeRef) {
            this.data = dataItem;
            this.next = nodeRef;
        }
    }
}
