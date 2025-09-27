// CLI: parse args, run algorithms, emit CSV
package com.example.dnc;

import com.example.dnc.util.Stats;
import java.util.Random;

public class Main {
    private static final Random R = new Random(0L);

    public Main() {
    }

    public static void main(String[] args) {
        int n = 10000;
        String algo = "mergesort";
        if (args.length > 0) {
            algo = args[0];
        }

        if (args.length > 1) {
            n = Integer.parseInt(args[1]);
        }

        int[] a = randomArray(n);
        Stats.reset();
        long start = System.currentTimeMillis();
        if ("mergesort".equalsIgnoreCase(algo)) {
            com.example.dnc.MergeSort.sort(a);
        } else if ("quicksort".equalsIgnoreCase(algo)) {
            com.example.dnc.QuickSort.sort(a);
        } else if ("select".equalsIgnoreCase(algo)) {
            int k = n / 2;
            int v = com.example.dnc.SelectMoM5.select(a, k);
            System.out.println("select result: " + v);
        } else if ("closest".equalsIgnoreCase(algo)) {
            com.example.dnc.ClosestPair.Point[] pts = new com.example.dnc.ClosestPair.Point[n];

            for(int i = 0; i < n; ++i) {
                pts[i] = new com.example.dnc.ClosestPair.Point(R.nextDouble(), R.nextDouble());
            }

            double d = com.example.dnc.ClosestPair.closest(pts);
            System.out.println("closest distance: " + d);
        }

        long dur = System.currentTimeMillis() - start;
        System.out.println(Stats.csvLine(algo, n, start, dur));
    }

    private static int[] randomArray(int n) {
        int[] a = new int[n];

        for(int i = 0; i < n; ++i) {
            a[i] = R.nextInt(n * 10);
        }

        return a;
    }
}
