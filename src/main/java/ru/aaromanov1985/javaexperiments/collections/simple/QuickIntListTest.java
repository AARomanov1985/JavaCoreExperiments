package ru.aaromanov1985.javaexperiments.collections.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raan on 31.05.2016.
 */
public class QuickIntListTest {

    public void test() {
        addTest(100);
        addTest(1000);
        addTest(10000);
        addTest(100000);
        addTest(1000000);
        addTest(10000000);
    }

    private void addTest(int value) {
        System.out.print("Тест на "+value+" элементов. ");
        long result = 0;
        int measuring = 10;

        for (int i = 0; i < measuring; i++) {
            IntList data = new QuickIntList();
            long start = System.nanoTime();
            for (int k = 0; k < value; k++) {
                data.add((int) (Math.random() * 100));
            }
            long stop = System.nanoTime();
            result += stop-start;
        }
        System.out.print("IntList: "+result / measuring+"   ");

        for (int i = 0; i < measuring; i++) {
            List<Integer> data = new ArrayList<>();
            long start = System.nanoTime();
            for (int k = 0; k < value; k++) {
                data.add((int) (Math.random() * 100));
            }
            long stop = System.nanoTime();
            result += stop-start;
        }
        System.out.println("MutantArrayList: "+result / measuring);

    }

}
