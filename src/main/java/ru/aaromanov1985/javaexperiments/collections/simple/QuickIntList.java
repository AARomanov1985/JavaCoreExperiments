package ru.aaromanov1985.javaexperiments.collections.simple;

import java.util.Arrays;

/**
 * Created by raan on 31.05.2016.
 */
public class QuickIntList implements IntList {

    private int[] data;

    private static final int INITIAL_CAPACITY = 10;

    private int size;

    public QuickIntList() {
        data = new int[INITIAL_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(int value) {
        if (checkGrow(size+1)) {
            grow();
        }
        size++;
        data[size-1] = value;
        return true;
    }

    private boolean checkGrow(int index) {
        return index > data.length / 2;
    }

    private void grow() {
        int[] newData = Arrays.copyOf(data, data.length * 2);
        data = newData;
    }

    @Override
    public boolean delete(int index) {
        checkRange(index);
        int[] newData = new int[data.length - 1];
        for (int i = 0; i < index; i++) {
            newData[i] = data[i];
        }
        for (int i = index; i < newData.length; i++) {
            newData[i] = data[i + 1];
        }
        data = newData;
        size--;
        return true;
    }

    private void checkRange(int index) {
        if (index > size - 1 || index < 0) {
            throw new NullPointerException();
        }
    }

    @Override
    public void clear() {
        data = new int[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int get(int index) {
        checkRange(index);
        return data[index];
    }

    @Override
    public int set(int index, int value) {
        checkRange(index);
        int oldValue = data[index];
        data[index] = value;
        return oldValue;
    }

    @Override
    public void add(int index, int value) {
        checkRange(index);
        int[] newData = new int[data.length + 1];
        for (int i = 0; i < index; i++) {
            newData[i] = data[i];
        }
        newData[index] = value;
        for (int i = index + 1; i < newData.length; i++) {
            newData[i] = data[i - 1];
        }
        data = newData;
        size++;
    }
}
