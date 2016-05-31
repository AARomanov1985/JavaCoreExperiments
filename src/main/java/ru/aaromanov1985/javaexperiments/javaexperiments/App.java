package ru.aaromanov1985.javaexperiments.javaexperiments;

import ru.aaromanov1985.javaexperiments.javaexperiments.collections.ArrayListTest;
import ru.aaromanov1985.javaexperiments.javaexperiments.collections.QuickIntListTest;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        QuickIntListTest test = new QuickIntListTest();
        test.test();
        ArrayListTest testList = new ArrayListTest();
        testList.test();
    }
}
