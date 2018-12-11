package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        String license = Files.readString(Paths.get("8.in"));

        var numbers = Stream.of(license.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        var root = parse(numbers.iterator());

        System.out.println("Part one: " + sum(root));
        System.out.println("Part two: " + complicatedSum(root));
    }

    static Node parse(Iterator<Integer> iterator) {
        var node = new Node(iterator.next(), iterator.next());

        for (int i = 0; i < node.children.length; i++) {
            node.children[i] = parse(iterator);
        }

        for (int i = 0; i < node.entries.length; i++) {
            node.entries[i] = iterator.next();
        }

        return node;
    }

    static int sum(Node node) {
        var sum = 0;

        for (var child : node.children) {
            sum = sum + sum(child);
        }

        for (var entry : node.entries) {
            sum = sum + entry;
        }

        return sum;
    }
    
    static int complicatedSum(Node node) {
        if(node.children.length == 0) {
            return Arrays.stream(node.entries).reduce(Integer::sum).getAsInt();
        }
        
        var sum = 0;
        
        
        for (var entry : node.entries) {
            if(entry - 1 < node.children.length) {
                sum = sum + complicatedSum(node.children[entry - 1]);
            }
        }
        
        return sum;
    }
}

class Node {

    Node[] children;
    int[] entries;

    public Node(int children, int entries) {
        this.children = new Node[children];
        this.entries = new int[entries];
    }

    @Override
    public String toString() {
        return "Node{" + "children=" + Arrays.toString(children) + ", entries=" + Arrays.toString(entries) + '}';
    }
}