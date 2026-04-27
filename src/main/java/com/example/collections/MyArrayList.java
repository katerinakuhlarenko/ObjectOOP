package com.example.collections;

import com.example.collections.exceptions.EmptyListException;
import com.example.collections.exceptions.InvalidIndexException;
import com.example.collections.exceptions.InvalidInitialSizeException;
import com.example.collections.exceptions.ListCapacityLimitException;
import com.example.collections.exceptions.NegativeCapacityException;

public class MyArrayList {

    private static class DataPos {
        ArrayData Data;
        int localIndex;
        DataPos(ArrayData c, int i) { Data = c; localIndex = i; }
    }

    private ArrayData head;
    private ArrayData tail;
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
        ArrayData c = head;
        while (c != null) { cap += ArrayData.DATA_CAPACITY; c = c.next; }
        return cap;
    }

    public void addEnd(int value) {
        ensureTailHasSpace();
        tail.data[tail.count++] = value;
        size++;
    }

    public void addStart(int value) {
        if (head == null) {
            head = tail = new ArrayData();
        } else if (head.isFull()) {
            ArrayData newData = new ArrayData();
            newData.next = head;
            head.prev = newData;
            head = newData;
        }
        for (int i = head.count; i > 0; i--)
            head.data[i] = head.data[i - 1];
        head.data[0] = value;
        head.count++;
        size++;
    }

   public void add(int index, int value) {
        if (index < 0 || index > size)
            throw new InvalidIndexException(index + ", size=" + size);
        if (index == 0)    { addStart(value); return; }
        if (index == size) { addEnd(value);   return; }

        DataPos pos = findPosition(index);
        insertAt(pos.Data, pos.localIndex, value);
        size++;
    }

    public int get(int index) {
        if (size == 0) throw new EmptyListException();
        if (index < 0 || index >= size)
            throw new InvalidIndexException(index + ", size=" + size);
        DataPos pos = findPosition(index);
        return pos.Data.data[pos.localIndex];
    }

    public void remove(int index) {
        if (size == 0) throw new EmptyListException();
        if (index < 0 || index >= size)
            throw new InvalidIndexException(index + ", size=" + size);

        DataPos pos = findPosition(index);
        ArrayData Data = pos.Data;
        int local = pos.localIndex;

        for (int i = local; i < Data.count - 1; i++)
            Data.data[i] = Data.data[i + 1];
        Data.count--;
        size--;

        if (Data.isEmpty()) unlinkData(Data);
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

   public void print() {
        StringBuilder sb = new StringBuilder("[");
        ArrayData c = head;
        boolean first = true;
        while (c != null) {
            for (int i = 0; i < c.count; i++) {
                if (!first) sb.append(", ");
                sb.append(c.data[i]);
                first = false;
            }
            c = c.next;
        }
        System.out.println(sb.append("]"));
    }

    public void printChunks() {
        ArrayData c = head;
        StringBuilder sb = new StringBuilder();
        while (c != null) {
            sb.append(c);
            if (c.next != null) sb.append(" <-> ");
            c = c.next;
        }
        System.out.println(sb.length() == 0 ? "[]" : sb);
    }

   private void ensureTailHasSpace() {
        if (tail == null) {
            head = tail = new ArrayData();
        } else if (tail.isFull()) {
            ArrayData chunk = new ArrayData();
            chunk.prev = tail;
            tail.next  = chunk;
            tail       = chunk;
        }
    }

    private void insertAt(ArrayData chunk, int localIndex, int value) {
        if (chunk.isFull()) {
            splitData(chunk);
            int half = ArrayData.DATA_CAPACITY / 2;
            if (localIndex >= half) {
                chunk      = chunk.next;
                localIndex -= half;
            }
        }
        for (int i = chunk.count; i > localIndex; i--)
            chunk.data[i] = chunk.data[i - 1];
        chunk.data[localIndex] = value;
        chunk.count++;
    }

    private void splitData(ArrayData chunk) {
        ArrayData newChunk = new ArrayData();
        int half = ArrayData.DATA_CAPACITY / 2;

        for (int i = half; i < chunk.count; i++)
            newChunk.data[newChunk.count++] = chunk.data[i];
        chunk.count = half;

        newChunk.prev = chunk;
        newChunk.next = chunk.next;
        if (chunk.next != null) chunk.next.prev = newChunk;
        else tail = newChunk;
        chunk.next = newChunk;
    }

    private void unlinkData(ArrayData Data) {
        if (Data.prev != null) Data.prev.next = Data.next;
        else head = Data.next;
        if (Data.next != null) Data.next.prev = Data.prev;
        else tail = Data.prev;
    }

    private DataPos findPosition(int globalIndex) {
        ArrayData current = head;
        int remaining = globalIndex;
        while (current != null) {
            if (remaining < current.count)
                return new DataPos(current, remaining);
            remaining -= current.count;
            current = current.next;
        }
        throw new InvalidIndexException("Global index out of range: " + globalIndex);
    }
}
