package ru.aaromanov1985.javaexperiments.javaexperiments.collections;

/**
 * Created by raan on 31.05.2016.
 */
public class QuickIntListTest {

    public void test() {
        System.out.println("Результат add1000Test(): "+addTest(1000) + " мс");
        System.out.println("Результат add10000Test(): "+addTest(10000) + " мс");
        System.out.println("Результат add20000Test(): "+addTest(20000) + " мс");
        System.out.println("Результат add30000Test(): "+addTest(30000) + " мс");
    }

    private long addTest(int value) {
        long result = 0;
        int measuring = 10;

        for (int i = 0; i < measuring; i++) {
            IntList data = new QuickIntList();
            long start = System.currentTimeMillis();

            for (int k = 0; k < value; k++) {
                data.add((int) (Math.random() * 100));
            }
            long stop = System.currentTimeMillis();
            result += stop-start;
        }
        return result / measuring;
    }
}
