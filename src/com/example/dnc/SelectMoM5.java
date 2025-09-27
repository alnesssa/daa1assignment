package com.example.dnc;

import com.example.dnc.util.Stats;
import com.example.dnc.util.ArraysUtil;

public class SelectMoM5 {

    public static int select(int[] a, int k) {
        if (a == null || k < 0 || k >= a.length) throw new IllegalArgumentException();
        return select(a, 0, a.length-1, k);
    }

    private static int select(int[] a, int lo, int hi, int k) {
        while (true) {
            if (lo == hi) return a[lo];
            int pivot = pivotOfMedians(a, lo, hi);
            int p = partitionAround(a, lo, hi, pivot);
            if (k == p) return a[k];
            else if (k < p) hi = p-1;
            else lo = p+1;
        }
    }

    private static int pivotOfMedians(int[] a, int lo, int hi) {
        int n = hi - lo + 1;
        int numGroups = (n + 4)/5;
        for (int i = 0; i < numGroups; ++i) {
            int groupLo = lo + i*5;
            int groupHi = Math.min(groupLo+4, hi);
            insertionSortRange(a, groupLo, groupHi);
            int medianIndex = groupLo + (groupHi - groupLo)/2;
            ArraysUtil.swap(a, lo + i, medianIndex);
        }
        int mid = lo + (numGroups-1)/2;
        if (numGroups == 1) return a[lo];
        return select(a, lo, lo + numGroups -1, mid);
    }

    private static void insertionSortRange(int[] a, int lo, int hi) {
        for (int i = lo+1; i<=hi; ++i) {
            int key = a[i];
            int j = i-1;
            while (j>=lo) {
                Stats.addComparison(1);
                if (a[j] > key) { a[j+1] = a[j]; j--; }
                else break;
            }
            a[j+1] = key;
        }
    }

    private static int partitionAround(int[] a, int lo, int hi, int pivotVal) {
        int pi = lo;
        while (pi <= hi && a[pi] != pivotVal) pi++;
        if (pi <= hi) ArraysUtil.swap(a, pi, hi);
        int store = lo;
        for (int i = lo; i<hi; ++i) {
            Stats.addComparison(1);
            if (a[i] < pivotVal) ArraysUtil.swap(a,i,store++);
        }
        ArraysUtil.swap(a, store, hi);
        return store;
    }
}
