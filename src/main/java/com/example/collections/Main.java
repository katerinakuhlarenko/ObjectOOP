package com.example.collections;

import com.example.collections.exceptions.EmptyListException;
import com.example.collections.exceptions.InvalidIndexException;
import com.example.collections.exceptions.InvalidInitialSizeException;
import com.example.collections.exceptions.NegativeCapacityException;

public class Main {
    public static void main(String[] args) throws Exception {
        MyArrayList list = new MyArrayList();

        list.addEnd(10);
        list.addEnd(20);
        list.addEnd(30);
        list.addStart(5);
        list.add(2, 15);

        System.out.print("List: "); list.print();
        System.out.print("Data: "); list.printChunks();
        System.out.println("Size: " + list.getSize());
        System.out.println("Capacity: " + list.getCapacity());

        System.out.println("get(0): " + list.get(0));
        System.out.println("get(2): " + list.get(2));
        System.out.println("get(4): " + list.get(4));

        list.remove(2);
        System.out.print("After remove(2): "); list.print();

        MyArrayList big = new MyArrayList();
        for (int i = 1; i <= 9; i++) big.addEnd(i * 10);
        System.out.print("9 elements: "); big.print();
        System.out.print("Data: "); big.printChunks();
        big.add(4, 99);
        System.out.print("After add(4,99): "); big.print();
        System.out.print("Data: "); big.printChunks();

        list.clear();
        System.out.println("\nAfter clear: size=" + list.getSize());

        System.out.println("\nExceptions");

        try {
            list.get(0);
        } catch (EmptyListException e) {
            System.out.println(e.getMessage());
        }

        try { list.remove(0); }
        catch (EmptyListException e) {
            System.out.println(e.getMessage()); }

        list.addEnd(1);
        try { list.get(-1); }
        catch (InvalidIndexException e) {
            System.out.println(e.getMessage()); }

        try { list.get(99); }
        catch (InvalidIndexException e) {
            System.out.println(e.getMessage()); }

        try { new MyArrayList(-1); }
        catch (NegativeCapacityException e) {
            System.out.println(e.getMessage()); }

        try { new MyArrayList(0); }
        catch (InvalidInitialSizeException e) {
            System.out.println(e.getMessage()); }
    }
}
