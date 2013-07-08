package com.group.hackcoder.ds.list;

public class SortedLinkedList {

    private Node head;
    private int size;

    public SortedLinkedList() {
        head = new Node();
    }

    public static void main(String[] args) {
        SortedLinkedList lList = new SortedLinkedList();

        lList.add(8);
        lList.add(2);
        lList.add(7);
        lList.add(10);
        lList.add(12);
        
        long t = -2147483648;
        long t2 = -2147483648;
        System.out.println("t1 + t2 "+(t+t2)/2);

        System.out.println("lList - print linkedlist: " + lList);
        System.out.println("lList.size() - print linkedlist size: " + lList.size());
        System.out.println("lList.get(3) - get 3rd element: " + lList.get(3));
        System.out.println("lList.remove(2) - remove 2nd element: " + lList.remove(2));
        System.out.println("lList.get(3) - get 3rd element: " + lList.get(3));
        System.out.println("lList.size() - print linkedlist size: " + lList.size());
        System.out.println("lList - print linkedlist: " + lList);
    }

    public void add(int data) {
        Node temp = new Node(data);
        Node node = head;
        int index = 1;
        boolean checkStatus = true;
        while (node.getNext() != null) {
            node = node.getNext();
            /**
             * Maintain the order
             */
            if (data < node.data) {
                add(data, index);
                checkStatus = false;
                break;
            }

            index++;
        }

        if (checkStatus) {
            node.setNext(temp);
            size++;
        }
    }

    public boolean isEmpty() {
        return size == 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    public void add(int data, int index) {
        Node tempNode = new Node(data);
        Node node = head;
        for (int i = 1; i < index && node.getNext() != null; i++) {
            node = node.getNext();
        }
        tempNode.setNext(node.getNext());
        node.setNext(tempNode);
        size++;
    }

    public int get(int index) {
        if (index <= 0) {
            return 0;
        }

        Node node = head.getNext();
        for (int i = 1; i < index; i++) {
            if (node.getNext() == null) {
                return 0;
            }

            node = node.getNext();
        }
        return node.getData();
    }

    public boolean remove(int index) {
        if (index < 1 || index > size()) {
            return false;
        }

        Node node = head;
        for (int i = 1; i < index; i++) {
            if (node.getNext() == null) {
                return false;
            }

            node = node.getNext();
        }
        node.setNext(node.getNext().getNext());
        size--;
        return true;
    }

    public boolean removeData(int data) {
        Node node = head;
        for (int i = 1; i < size; i++) {
            if (node.getNext() == null) {
                return false;
            }
            node = node.getNext();
            if (data == node.data) {
                break;
            }
        }
        node.setNext(node.getNext().getNext());
        size--;
        return true;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        Node node = head.getNext();
        String output = "";
        while (node != null) {
            output += "[" + node.getData() + "]";
            node = node.getNext();
        }
        return output;
    }

    class Node {

        Node next;
        int data;

        public Node() {
            this.next = null;
//            this.data = data;
        }

        public Node(int data) {
            this.next = null;
            this.data = data;
        }

        public Node(Node next, int data) {
            this.next = next;
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        private void addFirst(int data) {
            head = new Node(head, data);
        }

        private void addLast(int data) {
            if (head == null) {
                addFirst(data);
            } else {
                Node temp = head;
                while (temp.next != null) {
                    temp = temp.next;
                    temp.next = new Node(null, data);
                }
            }
        }
    }
}
