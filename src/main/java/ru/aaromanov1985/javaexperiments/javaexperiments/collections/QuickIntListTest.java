package ru.aaromanov1985.javaexperiments.javaexperiments.collections;

/**
 * Created by raan on 31.05.2016.
 */
public class QuickIntListTest {

    public static void test() {
        IntList data = new QuickIntList();
        for (int i = 0; i < 100; i++) {
            System.out.println("Добавляем " + i);
            data.add((int) (Math.random() * 100));
        }

        for (int i = data.size() - 1; i >= 0; i--) {
            System.out.println("Удаляем " + i);
            data.delete(i);
        }
    }
}
