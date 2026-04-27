package com.example.collections;

public class ArrayData<T> {

    static final int CHUNK_CAPACITY = 4;

    Object[]       data;
    int            count;
    ArrayData<T>  next;
    ArrayData<T>  prev;

    public ArrayData() {
        this.data  = new Object[CHUNK_CAPACITY];
        this.count = 0;
    }

    @SuppressWarnings("unchecked")
    public T get(int i) { return (T) data[i]; }

    public void set(int i, T value) { data[i] = value; }

    public boolean isFull()  { return count == CHUNK_CAPACITY; }
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