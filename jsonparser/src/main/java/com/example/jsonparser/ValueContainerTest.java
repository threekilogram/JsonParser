package com.example.jsonparser;

/**
 * @author wuxio 2018-05-22:10:17
 */
class ValueContainerTest {

    public static void main(String[] args) {

        ValueContainer valueContainer = new ValueContainer();

        for (int i = 0; i < 20; i++) {
            int j = valueContainer.saveDoubleValue(i);
            System.out.println(j);
        }

        for (int i = 0; i < 20; i++) {
            double value = valueContainer.getDoubleValue(i);
            System.out.println(value);
        }
    }
}
