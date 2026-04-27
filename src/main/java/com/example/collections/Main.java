package com.example.collections;

public class Main {
    public static void main(String[] args) throws Exception {

        MyArrayList<Integer> intList = new MyArrayList<>();
        intList.addEnd(10); intList.addEnd(20); intList.addEnd(30);
        intList.addStart(5);
        intList.add(2, 15);
        System.out.print("Integer list: "); intList.print();
        System.out.print("Chunks:       "); intList.printChunks();
        System.out.println("get(2): " + intList.get(2));
        intList.remove(2);
        System.out.print("After remove(2): "); intList.print();

        System.out.println();
        MyArrayList<String> strList = new MyArrayList<>();
        strList.addEnd("Banana"); strList.addEnd("Cherry");
        strList.addStart("Apple");
        strList.add(2, "Blueberry");
        System.out.print("String list: "); strList.print();
        strList.remove(1);
        System.out.print("After remove(1): "); strList.print();
        System.out.println();
        MyArrayList<Double> dblList = new MyArrayList<>();
        dblList.addEnd(3.14); dblList.addEnd(2.71); dblList.addStart(1.41);
        System.out.print("Double list: "); dblList.print();
        System.out.println("Size: " + dblList.getSize() + ", Capacity: " + dblList.getCapacity());
    }
}