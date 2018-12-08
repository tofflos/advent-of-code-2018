package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

        var edges = Files.readAllLines(Paths.get("7.in")).stream()
                .map(Edge::parse)
                .collect(Collectors.toList());

        Predicate<Edge> isAvailable = edge -> edges.stream().noneMatch(e -> edge.from.equals(e.to));
        Comparator<Edge> alphabetical = (a, b) -> a.from.compareTo(b.from) != 0 ? a.from.compareTo(b.from) : a.to.compareTo(b.to);

        var steps = new ArrayList<String>();

        while (!edges.isEmpty()) {
            var next = edges.stream()
                    .filter(isAvailable)
                    .sorted(alphabetical)
                    .findFirst()
                    .get();

            edges.remove(next);

            var remaining = Stream.concat(edges.stream().map(e -> e.from), edges.stream().map(e -> e.to))
                    .collect(Collectors.toList());

            if (!steps.contains(next.from)) {
                steps.add(next.from);
            }

            if (!steps.contains(next.to) && !remaining.contains(next.to)) {
                steps.add(next.to);
            }
        }

        System.out.println("Part one: " + steps.stream().collect(Collectors.joining()));
    }
}

class Edge {

    String from;
    String to;

    public Edge(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return from + ":" + to;
    }

    static Edge parse(String s) {
        return new Edge(s.substring(5, 6), s.substring(36, 37));
    }
}