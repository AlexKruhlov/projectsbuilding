package com.company.app;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by sigmund69 on 11.04.2016.
 */
public class ListTestingTest {

    @Test
    public void testAdd() throws Exception {
        int[] indexes = new int[100];
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            indexes[i] = random.nextInt(19);
        }

        int listSize = 20;
        ArrayList arrayList = new ArrayList<>();
        ListTesting listTesting = new ListTesting(arrayList, listSize);

        listTesting.add(indexes);

    }
}