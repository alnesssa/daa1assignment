# Divide and Conquer Algorithms – Report

## Architecture Notes
Each algorithm is implemented in its own class:

- `MergeSort`
- `QuickSort`
- `SelectMoM5` (deterministic select)
- `ClosestPair`

Reusable utility classes:
- `ArraysUtil` (swap, shuffle, insertion sort)
- `Stats` (comparisons, swaps, allocations, recursion depth)

`Stats` controls metrics:
- Depth tracked with `pushDepth()` before recursion and `popDepth()` after return.
- Allocations counted when buffers or copies are created (e.g., merge buffer).

This architecture isolates algorithm logic from metric collection, making performance comparisons easy.

## Recurrence Analysis

### MergeSort
The array is split recursively until size is below a cutoff. Below cutoff, insertion sort is used. Merging takes linear time.  
Recurrence:  
\(T(n)=2T(n/2)+\Theta(n)\). By the Master Theorem, \(T(n)=\Theta(n\log n)\).  
Depth: \(\Theta(\log n)\). Allocations: proportional to \(n\) for the buffer.

### QuickSort
Array is randomized and partitioned around a pivot. Smaller partition processed first to reduce stack depth.  
Average recurrence: \(T(n)=T(pn)+T((1-p)n)+\Theta(n)\). Expected time: \(\Theta(n\log n)\).  
Worst case \(\Theta(n^2)\) but randomized pivot reduces risk.  
Depth: expected \(\Theta(\log n)\). Allocations: constant per step.

### Deterministic Select (Median of Medians)
Array divided into groups of 5; medians selected recursively; pivot chosen deterministically.  
Recurrence: \(T(n)=T(n/5)+T(7n/10)+\Theta(n)\) (Akra–Bazzi can also be applied).  
Time complexity: \(\Theta(n)\), depth \(\Theta(\log n)\), negligible extra memory.

### Closest Pair of Points
Points sorted by x and y; divided at mid x-coordinate. Recursively solve left and right; check strip of width \(2d\).  
Recurrence: \(T(n)=2T(n/2)+\Theta(n)\) → \(T(n)=\Theta(n\log n)\).  
Depth: \(\Theta(\log n)\). Allocations: from temporary arrays of left/right subsets.

## Plots and Constant-Factor Effects
Time vs. n and depth vs. n plots (not included here) show the expected asymptotic shapes.  
For small n, constant factors dominate:
- MergeSort’s buffer increases allocations.
- QuickSort benefits from cache locality but has variable depth.
- Median-of-Medians has high constants despite linear complexity.

Java’s garbage collector can add timing spikes for large arrays. Cache effects become visible when arrays exceed L2/L3 cache sizes.

## Summary
Measurements broadly align with theoretical predictions:
- MergeSort and QuickSort scale as \(n\log n\); MergeSort depth fixed, QuickSort depth more variable.
- Deterministic Select runs in linear time but with larger constant factors than random QuickSelect.
- Closest Pair scales as \(n\log n\) with efficient strip checks.

Mismatches stem from constant-factor costs (allocations, Java GC, cache misses) rather than algorithmic errors.  
Asymptotic analysis predicts growth but not exact runtime; empirical measurements are essential for comparing real implementations.
