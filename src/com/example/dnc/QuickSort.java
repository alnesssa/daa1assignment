// Quicksort with smaller-first recursion and randomized pivot
package com.example.dnc;

import com.example.dnc.util.Stats;
import com.example.dnc.util.ArraysUtil;

import java.util.Random;

public class QuickSort {
    private static final Random R = new Random();

    public static void sort(int[] a) {
        if (a == null || a.length < 2) return;
        ArraysUtil.shuffle(a);
        quicksort(a, 0, a.length-1);
    }

    private static void quicksort(int[] a, int lo, int hi) {
        int curLo = lo, curHi = hi;
        while (curLo < curHi) {
            int p = partition(a, curLo, curHi);
            int leftSize = p - curLo;
            int rightSize = curHi - p;
            if (leftSize < rightSize) {
                if (leftSize > 0) { Stats.pushDepth(); quicksort(a, curLo, p-1); Stats.popDepth(); }
                curLo = p+1;
            } else {
                if (rightSize > 0) { Stats.pushDepth(); quicksort(a, p+1, curHi); Stats.popDepth(); }
                curHi = p-1;
            }
        }
    }

    private static int partition(int[] a, int lo, int hi) {
        int pivotIndex = lo + R.nextInt(hi-lo+1);
        int pivot = a[pivotIndex];
        ArraysUtil.swap(a, pivotIndex, hi);
        int store = lo;
        for (int i = lo; i < hi; ++i) {
            Stats.addComparison(1);
            if (a[i] < pivot) { ArraysUtil.swap(a, i, store); store++; }
        }
        ArraysUtil.swap(a, store, hi);
        return store;
    }
}
