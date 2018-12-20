package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Part one: " + part1());
        System.out.println("Part two: " + part2());
    }

    static String part1() throws IOException {
        var edges = Files.readAllLines(Paths.get("7.in")).stream()
                .map(Edge::parse)
                .collect(Collectors.toList());

        Predicate<Edge> isAvailable = edge -> edges.stream().noneMatch(e -> edge.from == e.to);
        Comparator<Edge> alphabetical = (a, b) -> Character.compare(a.from, b.from) != 0 ? Character.compare(a.from, b.from) : Character.compare(a.to, b.to);

        var completed = new ArrayList<Character>();

        while (!edges.isEmpty()) {
            var next = edges.stream()
                    .filter(isAvailable)
                    .sorted(alphabetical)
                    .findFirst()
                    .get();

            edges.remove(next);

            var remaining = Stream.concat(
                    edges.stream().map(e -> e.from),
                    edges.stream().map(e -> e.to)
            ).collect(Collectors.toList());

            if (!completed.contains(next.from)) {
                completed.add(next.from);
            }

            if (!completed.contains(next.to) && !remaining.contains(next.to)) {
                completed.add(next.to);
            }
        }

        var collector = Collector.of(StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append,
                StringBuilder::toString);

        return completed.stream().collect(collector);

    }

    static int part2() throws IOException {
        var edges = Files.readAllLines(Paths.get("7.in")).stream()
                .map(Edge::parse)
                .collect(Collectors.toList());

        var completed = new ArrayList<Character>();
               
        var dependencies = edges.stream().collect(Collectors.groupingBy(e -> e.to, Collectors.mapping(e -> e.from, Collectors.toList())));
        var roots = edges.stream().map(e -> e.from).filter(c -> !dependencies.keySet().contains(c));
        roots.forEach(r -> dependencies.put(r, Collections.EMPTY_LIST));
        
        var workers = new Step[5];
        Queue<Step> queue = new LinkedList<>();

        Predicate<Character> isAvailable = c
                -> completed.containsAll(dependencies.get(c))
                && !completed.contains(c)
                && queue.stream().noneMatch(s -> s.c == c)
                && Arrays.stream(workers).noneMatch(s -> s != null ? s.c == c : false);

        var n = 0;

        for (; !completed.containsAll(dependencies.keySet()); n++) {
            var available = dependencies.entrySet().stream()
                    .map(e -> e.getKey())
                    .filter(isAvailable)
                    .sorted(Character::compareTo)
                    .collect(Collectors.toList());

            queue.addAll(available.stream().map(c -> new Step(c, c - 'A' + 1 + 60)).collect(Collectors.toList()));

            for (int i = 0; i < workers.length && !queue.isEmpty(); i++) {
                if (workers[i] == null) {
                    workers[i] = queue.remove();
                }
            }

            for (int i = 0; i < workers.length; i++) {
                if (workers[i] != null) {
                    workers[i].remainingSeconds--;

                    if (workers[i].remainingSeconds == 0) {
                        completed.add(workers[i].c);
                        workers[i] = null;
                    }
                }
            }

        }

        return n;
    }
}

class Edge {

    char from;
    char to;

    public Edge(char from, char to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return from + ":" + to;
    }

    static Edge parse(String s) {
        return new Edge(s.charAt(5), s.charAt(36));
    }
}

class Step {

    char c;
    int remainingSeconds;

    public Step(char c, int remainingSeconds) {
        this.c = c;
        this.remainingSeconds = remainingSeconds;
    }
}
