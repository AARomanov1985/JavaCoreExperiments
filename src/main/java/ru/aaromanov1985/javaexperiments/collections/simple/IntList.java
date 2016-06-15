package ru.aaromanov1985.javaexperiments.collections.simple;

public interface IntList {

    int size();

    boolean add(int value);

    boolean delete(int index);

    void clear();

    int get(int index);

    int set(int index, int value);

    void add(int index, int value);

}