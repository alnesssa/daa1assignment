// MergeSort implementation (baseline + cutoff)

package com.example.dnc;

import com.example.dnc.util.Stats;
import com.example.dnc.util.ArraysUtil;

public class MergeSort {
    private static final int CUTOFF = 16;

    public static void sort(int[] a) {
        if (a == null || a.length < 2) return;
        int[] buf = new int[a.length];
        Stats.addAllocation(a.length);
        Stats.pushDepth();
        mergesort(a, buf, 0, a.length - 1);
        Stats.popDepth();
    }

    private static void mergesort(int[] a, int[] buf, int lo, int hi) {
        if (hi - lo + 1 <= CUTOFF) {
            ArraysUtil.insertionSort(a, lo, hi);
            return;
        }
        int mid = (lo + hi) >>> 1;
        Stats.pushDepth();
        mergesort(a, buf, lo, mid);
        mergesort(a, buf, mid + 1, hi);
        Stats.popDepth();
        merge(a, buf, lo, mid, hi);
    }

    private static void merge(int[] a, int[] buf, int lo, int mid, int hi) {
        int i = lo, j = mid + 1, k = lo;
        while (i <= mid && j <= hi) {
            Stats.addComparison(1);
            if (a[i] <= a[j]) buf[k++] = a[i++];
            else buf[k++] = a[j++];
        }
        while (i <= mid) buf[k++] = a[i++];
        while (j <= hi) buf[k++] = a[j++];
        System.arraycopy(buf, lo, a, lo, hi - lo + 1);
        Stats.addAllocation(hi - lo + 1);
    }
}