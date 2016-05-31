package ru.aaromanov1985.javaexperiments.javaexperiments.collections;

/**
 * Created by raan on 31.05.2016.
 */
public interface IntList {

    int size();

    boolean add(int value);

    boolean delete(int index);

    void clear();

    int get(int index);

    int set(int index, int value);

    void add(int index, int value);

}
