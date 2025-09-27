package com.example.dnc.util;

public class Stats {
    private static long comparisons = 0;
    private static long swaps = 0;
    private static long allocations = 0;
    private static int depth = 0;
    private static int maxDepth = 0;

    public static void reset() {
        comparisons = swaps = allocations = 0;
        depth = maxDepth = 0;
    }

    public static void addComparison(long v) { comparisons += v; }
    public static void addSwap(long v) { swaps += v; }
    public static void addAllocation(long v) { allocations += v; }

    public static void pushDepth() {
        depth++;
        if (depth > maxDepth) maxDepth = depth;
    }
    public static void popDepth() { if (depth>0) depth--; }

    public static int getMaxDepth() { return maxDepth; }
    public static long getComparisons() { return comparisons; }
    public static long getSwaps() { return swaps; }
    public static long getAllocations() { return allocations; }

    public static String csvHeader() {
        return "algo,n,startTimeMs,durationMs,comparisons,swaps,allocations,maxDepth";
    }
    public static String csvLine(String algo, int n, long start, long duration) {
        return String.format("%s,%d,%d,%d,%d,%d,%d,%d", algo, n, start, duration, comparisons, swaps, allocations, maxDepth);
    }
}
