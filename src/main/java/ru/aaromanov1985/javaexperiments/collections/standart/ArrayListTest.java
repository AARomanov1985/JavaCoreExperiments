package ru.aaromanov1985.javaexperiments.collections.standart;


import java.util.List;

public class ArrayListTest {

    private List<Integer> list;

    public static void main(String[] args) {
        ArrayListTest test = new ArrayListTest();
        test.create();
        test.createSize();
        test.createFromCollection();
        for(int i=0; i<9; i++){
            test.add();
        }
    }

    private void create() {
        System.out.println("----------- new ArrayList<>() ------------ ");
        list = new ArrayList<>();
    }

    private void createSize() {
        System.out.println("----------- new ArrayList<>(100) ------------ ");
        list = new ArrayList<>(15);
    }

    private void createFromCollection() {
        System.out.println("----------- new ArrayList<>(collection) ------------ ");
        List<Integer> collection = new java.util.ArrayList<>();
        for (int i = 0; i < 15; i++) {
            collection.add(i);
        }
        list = new ArrayList<>(collection);
    }

    private void add() {
        System.out.println("----------- add(E e) ------------ ");
        list.add((int) (Math.random() * 100));
    }

    private void set() {

    }

    private void remove() {

    }
}
