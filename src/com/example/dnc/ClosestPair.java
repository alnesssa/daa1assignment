package com.example.dnc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

public class ClosestPair {
    public static class Point { public final double x,y; public Point(double x,double y){this.x=x;this.y=y;} }

    public static double closest(Point[] pts) {
        if (pts==null || pts.length<2) return Double.POSITIVE_INFINITY;
        Point[] byX = pts.clone();
        Arrays.sort(byX, Comparator.comparingDouble(p->p.x));
        Point[] byY = pts.clone();
        Arrays.sort(byY, Comparator.comparingDouble(p->p.y));
        return recur(byX, byY, 0, pts.length-1);
    }

    private static double recur(Point[] byX, Point[] byY, int lo, int hi) {
        int n = hi-lo+1;
        if (n <= 3) {
            double best = Double.POSITIVE_INFINITY;
            for (int i=lo;i<=hi;++i) for (int j=i+1;j<=hi;++j) best = Math.min(best, dist(byX[i], byX[j]));
            return best;
        }
        int mid = (lo+hi)/2;
        double midx = byX[mid].x;

        List<Point> leftY = new ArrayList<>(), rightY = new ArrayList<>();
        for (Point p: byY) {
            if (p.x <= midx) leftY.add(p); else rightY.add(p);
        }

        double dl = recur(byX, leftY.toArray(new Point[0]), 0, leftY.size()-1);
        double dr = recur(Arrays.copyOfRange(byX, mid+1, hi+1), rightY.toArray(new Point[0]), 0, rightY.size()-1);
        double d = Math.min(dl, dr);

        List<Point> strip = new ArrayList<>();
        for (Point p: byY) if (Math.abs(p.x - midx) < d) strip.add(p);
        double best = d;
        for (int i = 0; i < strip.size(); ++i) {
            for (int j = i+1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < best; ++j) {
                best = Math.min(best, dist(strip.get(i), strip.get(j)));
            }
        }
        return best;
    }

    private static double dist(Point a, Point b) {
        double dx = a.x-b.x, dy = a.y-b.y; return Math.sqrt(dx*dx + dy*dy);
    }
}
