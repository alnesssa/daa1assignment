// Utility functions: partition, swap, shuffle, guards
package com.example.dnc.util;

import java.util.Random;

public final class ArraysUtil {
    private static final Random R = new Random();

    private ArraysUtil() {}

    public static void swap(int[] a, int i, int j) {
        if (i == j) return;
        int t = a[i]; a[i] = a[j]; a[j] = t;
        Stats.addSwap(1);
    }

    public static void shuffle(int[] a) {
        for (int i = a.length-1; i>0; --i) {
            int j = R.nextInt(i+1);
            swap(a,i,j);
        }
    }

    public static void insertionSort(int[] a, int lo, int hi) {
        for (int i = lo+1; i <= hi; ++i) {
            int key = a[i];
            int j = i-1;
            while (j >= lo) {
                Stats.addComparison(1);
                if (a[j] > key) { a[j+1] = a[j]; Stats.addSwap(1); j--; }
                else break;
            }
            a[j+1] = key;
        }
    }
}
