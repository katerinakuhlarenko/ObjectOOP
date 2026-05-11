package com.example.collections;

import com.example.collections.exceptions.EmptyListException;
import com.example.collections.exceptions.InvalidIndexException;
import com.example.collections.exceptions.InvalidInitialSizeException;
import com.example.collections.exceptions.NegativeCapacityException;

import java.util.Comparator;

public class MyArrayList<T extends Comparable<T>>
        implements Comparable<MyArrayList<T>> {

    private ArrayData<T> head;
    private ArrayData<T> tail;
    private int size;

    public MyArrayList() {}

    public MyArrayList(int initialCapacity) throws InvalidInitialSizeException {
        if (initialCapacity < 0)
            throw new NegativeCapacityException("Value: " + initialCapacity);
        if (initialCapacity == 0)
            throw new InvalidInitialSizeException("Initial capacity cannot be zero");
    }

    public int getSize() { return size; }

    public int getCapacity() {
        int cap = 0;
        ArrayData<T> c = head;
        while (c != null) { cap += ArrayData.CHUNK_CAPACITY; c = c.next; }
        return cap;
    }

    @Override
    public int compareTo(MyArrayList<T> other) {
        return Integer.compare(this.size, other.size);
    }

    public void sort() {
        sort(Comparable::compareTo);
    }

    public void sort(Comparator<T> comparator) {
        if (size <= 1) return;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < size - 1; i++) {
                T a = get(i), b = get(i + 1);
                if (comparator.compare(a, b) > 0) {
                    setAt(i,     b);
                    setAt(i + 1, a);
                    swapped = true;
                }
            }
        } while (swapped);
    }

    public void addEnd(T value) {
        ensureTailHasSpace();
        tail.set(tail.count++, value);
        size++;
    }

    public void addStart(T value) {
        if (head == null) {
            head = tail = new ArrayData<>();
        } else if (head.isFull()) {
            ArrayData<T> chunk = new ArrayData<>();
            chunk.next = head;
            head.prev  = chunk;
            head       = chunk;
        }
        for (int i = head.count; i > 0; i--)
            head.set(i, head.get(i - 1));
        head.set(0, value);
        head.count++;
        size++;
    }

    public void add(int index, T value) {
        if (index < 0 || index > size)
            throw new InvalidIndexException(index + ", size=" + size);
        if (index == 0)    { addStart(value); return; }
        if (index == size) { addEnd(value);   return; }

        ArrayData<T> chunk = head;
        int local = index;
        while (local >= chunk.count) {
            local -= chunk.count;
            chunk = chunk.next;
        }

        insertAt(chunk, local, value);
        size++;
    }

    public T get(int index) {
        if (size == 0) throw new EmptyListException();
        if (index < 0 || index >= size)
            throw new InvalidIndexException(index + ", size=" + size);

        ArrayData<T> chunk = head;
        int local = index;
        while (local >= chunk.count) {
            local -= chunk.count;
            chunk = chunk.next;
        }
        return chunk.get(local);
    }

    private void setAt(int index, T value) {
        ArrayData<T> chunk = head;
        int local = index;
        while (local >= chunk.count) {
            local -= chunk.count;
            chunk = chunk.next;
        }
        chunk.set(local, value);
    }

    public void remove(int index) {
        if (size == 0) throw new EmptyListException();
        if (index < 0 || index >= size)
            throw new InvalidIndexException(index + ", size=" + size);

        ArrayData<T> chunk = head;
        int local = index;
        while (local >= chunk.count) {
            local -= chunk.count;
            chunk = chunk.next;
        }

        for (int i = local; i < chunk.count - 1; i++)
            chunk.set(i, chunk.get(i + 1));
        chunk.data[--chunk.count] = null;
        size--;

        if (chunk.isEmpty()) unlinkChunk(chunk);
    }

    public void clear() { head = tail = null; size = 0; }

    public void print() {
        StringBuilder sb = new StringBuilder("[");
        ArrayData<T> c = head;
        boolean first = true;
        while (c != null) {
            for (int i = 0; i < c.count; i++) {
                if (!first) sb.append(", ");
                sb.append(c.get(i));
                first = false;
            }
            c = c.next;
        }
        System.out.println(sb.append("]"));
    }

    private void ensureTailHasSpace() {
        if (tail == null) {
            head = tail = new ArrayData<>();
        } else if (tail.isFull()) {
            ArrayData<T> chunk = new ArrayData<>();
            chunk.prev = tail;
            tail.next  = chunk;
            tail       = chunk;
        }
    }

    private void insertAt(ArrayData<T> chunk, int localIndex, T value) {
        if (chunk.isFull()) {
            splitChunk(chunk);
            int half = ArrayData.CHUNK_CAPACITY / 2;
            if (localIndex >= half) { chunk = chunk.next; localIndex -= half; }
        }
        for (int i = chunk.count; i > localIndex; i--)
            chunk.set(i, chunk.get(i - 1));
        chunk.set(localIndex, value);
        chunk.count++;
    }

    private void splitChunk(ArrayData<T> chunk) {
        ArrayData<T> newChunk = new ArrayData<>();
        int half = ArrayData.CHUNK_CAPACITY / 2;
        for (int i = half; i < chunk.count; i++)
            newChunk.set(newChunk.count++, chunk.get(i));
        for (int i = half; i < ArrayData.CHUNK_CAPACITY; i++)
            chunk.data[i] = null;
        chunk.count   = half;
        newChunk.prev = chunk;
        newChunk.next = chunk.next;
        if (chunk.next != null) chunk.next.prev = newChunk;
        else                    tail = newChunk;
        chunk.next = newChunk;
    }

    private void unlinkChunk(ArrayData<T> chunk) {
        if (chunk.prev != null) chunk.prev.next = chunk.next;
        else                    head = chunk.next;
        if (chunk.next != null) chunk.next.prev = chunk.prev;
        else                    tail = chunk.prev;
    }
}