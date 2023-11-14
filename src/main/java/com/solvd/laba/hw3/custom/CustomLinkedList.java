package com.solvd.laba.hw3.custom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomLinkedList<T> {
    private static final Logger LOGGER = LogManager.getLogger(CustomLinkedList.class);
    private Node<T> head;
    private int size;

    public CustomLinkedList() {
        this.head = null;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public void delete(T data) {
        if (head == null) {
            return; // If the list is empty, do nothing
        }

        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return;
        }

        Node<T> current = head;
        Node<T> previous = null;

        while (current != null && !current.data.equals(data)) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            return; // Element not found in the list
        }

        // Remove the element by updating the pointers
        previous.next = current.next;
        size--;
    }

    public void display() {
        Node<T> current = head;
        while (current != null) {
            LOGGER.info(current.data + " ");
            current = current.next;
        }
    }

    public int size() {
        return size;
    }
}
