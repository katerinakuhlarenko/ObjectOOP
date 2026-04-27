package com.example.collections;

public class ArrayData {

    static final int DATA_CAPACITY = 4;

    int[]       data;
    int         count;      // how many slots are currently used
    ArrayData  next;
    ArrayData  prev;

    public ArrayData() {
        this.data  = new int[DATA_CAPACITY];
        this.count = 0;
    }

    public boolean isFull()  { return count == DATA_CAPACITY; }
    public boolean isEmpty() { return count == 0; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < count; i++) {
            sb.append(data[i]);
            if (i < count - 1) sb.append(", ");
        }
        return sb.append("}").toString();
    }
}
