package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        var points = Files.readAllLines(Paths.get("6.in")).stream()
                .map(Point::parse)
                .collect(Collectors.toList());

        int maxX = points.stream().map(p -> p.x).max((a, b) -> Integer.compare(a, b)).get();
        int maxY = points.stream().map(p -> p.y).max((a, b) -> Integer.compare(a, b)).get();

        int[][] grid1 = new int[maxY + 1][maxX + 1];

        for (int i = 0; i < grid1.length; i++) {
            for (int j = 0; j < grid1[i].length; j++) {

                var distances = new HashMap<Point, Integer>();

                for (var point : points) {
                    distances.put(point, distance(point, new Point(j, i)));
                }

                var nearest = distances.entrySet().stream()
                        .min((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue())).get();
                
                var nearest_count = distances.entrySet().stream()
                        .filter(e -> nearest.getValue().equals(e.getValue()))
                        .count();
                
                grid1[i][j] = nearest_count == 1 ? points.indexOf(nearest.getKey()) : -1;
            }
        }

        var infinites = new ArrayList<Integer>();

        for (int i = 0; i < grid1.length; i++) {
            for (int j = 0; j < grid1[i].length; j++) {
                if (i == 0 || j == 0 || i == grid1.length - 1 || j == grid1[i].length - 1) {
                    infinites.add(grid1[i][j]);
                }
            }
        }

        var finites = IntStream.range(0, points.size()).boxed().filter(p -> !infinites.contains(p)).collect(Collectors.toList());
        var occurrences = finites.stream().collect(Collectors.toMap(Function.identity(), e -> occurrences(e, grid1)));
        var largestAreaSize = occurrences.entrySet().stream().max((i1, i2) -> Integer.compare(i1.getValue(), i2.getValue())).get().getValue();

        System.out.println("Part one: " + largestAreaSize);

        int[][] grid2 = new int[maxX + 1][maxY + 1];

        for (int i = 0; i < grid2.length; i++) {
            for (int j = 0; j < grid2[i].length; j++) {
                var total_distance = 0;

                for (var point : points) {
                    total_distance += distance(point, new Point(j, i));
                }

                grid2[i][j] = total_distance < 10000 ? total_distance : -1;
            }
        }

        var regionSize = Arrays.stream(grid2)
                .flatMapToInt(Arrays::stream)
                .filter(n -> n != -1)
                .count();

        System.out.println("Part two: " + regionSize);
    }

    static int distance(Point p, Point q) {
        return Math.abs(p.x - q.x) + Math.abs(p.y - q.y);
    }

    static int occurrences(int n, int[][] arr) {
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (n == arr[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }
}

class Point {

    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "{" + "x=" + x + ", y=" + y + '}';
    }

    static Point parse(String s) {
        var arr = s.split(", ");

        return new Point(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
    }
}