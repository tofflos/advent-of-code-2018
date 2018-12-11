package com.example;

import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        var square1 = largestSquare(3, 5235);
        var square2 = IntStream.range(1, 301)
                .mapToObj(i -> largestSquare(i, 5235))
                .max((a, b) -> Integer.compare(a.power, b.power)).get();

        System.out.println("Part one: " + square1.x + "," + square1.y);
        System.out.println("Part two: " + square2.x + "," + square2.y + "," + square2.size);
    }

    static int calculatePower(int x, int y, int serial) {
        var rackId = x + 10;
        var power = rackId * y;
        
        power += serial;
        power *= rackId;
        power = power / 100 % 10;
        power -= 5;

        return power;
    }

    static Square largestSquare(int size, int serial) {
        var square = new Square(0, 0, 0, 0);

        for (int x = 1; x <= 300 - size; x++) {
            for (int y = 1; y <= 300 - size; y++) {
                var power = 0;

                for (int i = x; i < x + size; i++) {
                    for (int j = y; j < y + size; j++) {
                        power += calculatePower(i, j, serial);
                    }
                }

                if (power > square.power) {
                    square = new Square(x, y, size, power);
                }
            }
        }

        return square;
    }
}

class Square {

    int x, y, size, power;

    public Square(int x, int y, int size, int power) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.power = power;
    }

    @Override
    public String toString() {
        return "Square{" + "x=" + x + ", y=" + y + ", size=" + size + ", power=" + power + '}';
    }
}
