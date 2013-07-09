/** **
 * **************************************************************************
 ******************************* NOT COMPLETED ******************************
 * **************************************************************************
 */
package com.group.hackerrank.algorithms.search;

import java.util.HashMap;
import java.util.Scanner;

public class Median {

    private Node head = null;
    private int size;

    public Median() {
//        head = new Node();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int noOfLines = in.nextInt();
        String[] label = new String[noOfLines];
        long[] num = new long[noOfLines];
        Median sl = new Median();
        HashMap<Long, String> keyVal = new HashMap<Long, String>();
        for (int i = 0; i < noOfLines; i++) {
            label[i] = in.next();
            num[i] = in.nextLong();
            if (label[i].equals("a")) {
                if (keyVal.containsKey(num[i])) {
                    String temp = keyVal.get(num[i]);
                    temp += "," + (i + 1);
                    keyVal.put(num[i], temp);
                } else {
                    String temp = "" + (i + 1);
                    keyVal.put(num[i], temp);
                }
            }

            if (!keyVal.containsKey(num[i])) {
                System.out.println("Wrong!");
            } else if (!keyVal.containsKey(num[i]) && label[i].equals("r")) {
                System.out.println("Wrong!");
            } else {
                if (label[i].equals("a")) {
                    sl.add(num[i]);
                }
                if (label[i].equals("r")) {
                    if (sl.isEmpty()) {
                        System.out.println("Wrong!");
                    } else {
                        if (keyVal.get(num[i]) != null) {
                            String[] val = keyVal.get(num[i]).split(",");
                            if (val.length == 1) {
                                keyVal.remove(num[i]);
                            } else {
                                String temp = keyVal.get(num[i]).split(",")[0];
                                String newVal = keyVal.get(num[i]).replaceAll(temp + ",", "");
                                keyVal.put(num[i], newVal);
                            }

                        }
                        long temp = num[i];
                        sl.removeData(temp);
                    }
                }

                if (sl.isEmpty()) {
                    System.out.println("Wrong!");;
                } else {
                    int len = sl.size();
                    double median = 0.0f;
                    if ((len % 2) == 0) {
                        int get = (int) len / 2;
                        median = (double) (sl.get(get) + sl.get(get + 1)) / 2;
                        if (String.valueOf(median).endsWith(".0")) {
                            System.out.println(String.valueOf(median).replaceAll("\\.0$", ""));
                        } else {
                            System.out.println(String.format("%.1f", median));
                        }
                    } else {
                        if (len == 1) {
//                            median = num[i];
                            System.out.println(sl.get(len));

                        } else {
                            median = (float) (len - 1) / 2;
                            if (String.valueOf(median).endsWith(".0")) {
                                int medianInt = Integer.parseInt(String.valueOf(median).replaceAll("\\.0$", ""));
                                long val = sl.get(medianInt + 1);
                                System.out.println(String.valueOf(val).replaceAll("\\.0$", ""));
                            } else {
                                System.out.println(String.format("%.1f", median));
                            }
                        }
                    }

                }
            }
        }
    }

    public boolean add(long item) {
        add(size, item);
        return true;
    }

    public void addFirst(long dataItem) {
        head = new Node(head, dataItem);
        size++;
    }

    public void add(int size, long data) {
        if (size == 0) {
            addFirst(data);
        } else {
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
                this.size++;
            }
        }
    }

    @Override
    public String toString() {
        Node node = head;
        String output = "";
        while (node != null) {
            output += "[" + node.getData() + "]";
            node = node.getNext();
        }
        return output;
    }

    public boolean isEmpty() {
        return size == 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    public void add(long data, int index) {
        Node tempNode = new Node(data);
        Node node = head;
        for (int i = 1; i < index && node.getNext() != null; i++) {
            node = node.getNext();
        }
        tempNode.setNext(node.getNext());
        node.setNext(tempNode);
        size++;
    }

    public Long get(int index) {
        if (index <= 0) {
            return null;
        }

        Node node = head;
        for (int i = 1; i < index; i++) {
            if (node.getNext() == null) {
                return null;
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

    public boolean removeData(long deleteItem) {
        if (head == null) {
            return false;
        } else if (head.data == deleteItem) {
            head = head.next;
            size--;
            return true;
        } else {
            Node runner;
            Node previous;
            runner = head.next;
            previous = head;
            while (runner != null && (runner.data > deleteItem)) {
                previous = runner;
                runner = runner.next;
            }
            if (runner != null && (runner.data == deleteItem)) {
                previous.next = runner.next;
                size--;
                return true;
            } else {
                // The item does not exist in the list.
                return false;
            }
        }
    }

    public int size() {
        return size;
    }

    class Node {

        Node next;
        long data;

        public Node() {
            this.next = null;
//            this.data = data;
        }

        public Node(long data) {
            this.next = null;
            this.data = data;
        }

        public Node(Node next, long data) {
            this.next = next;
            this.data = data;
        }

        public long getData() {
            return data;
        }

        public void setData(long data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        private void addFirst(long data) {
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
