package com.example;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("Part one: " + play(419, 71052));
        System.out.println("Part two: " + play(419, 100 * 71052));
    }

    static long play(int players, long lastMarble) {
        var initial = new Marble();

        initial.id = 0;
        initial.previous = initial;
        initial.next = initial;

        var current = initial;
        var scores = new long[players];

        for (long i = 1; i <= lastMarble; i++) {
            var next = new Marble();

            var one = current.next;
            var two = current.next.next;

            if (i % 23 == 0) {
                scores[(int) (i - 1) % players] += i;

                var seven = current.previous.previous.previous.previous.previous.previous.previous;
                seven.previous.next = seven.next;
                seven.next.previous = seven.previous;
                scores[(int) (i - 1) % players] += seven.id;
                current = seven.next;
            } else {
                next.id = i;

                one.next = next;
                next.previous = one;

                two.previous = next;
                next.next = two;

                current = next;
            }
        }

        return Arrays.stream(scores).max().getAsLong();
    }

}

class Marble {

    long id;
    Marble previous;
    Marble next;

    @Override
    public String toString() {
        return "Marble{" + "id=" + id + ", previous=" + previous.id + ", next=" + next.id + '}';
    }
}