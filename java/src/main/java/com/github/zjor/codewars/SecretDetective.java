package com.github.zjor.codewars;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SecretDetective {

    private void visit(Character start, Map<Character, Set<Character>> index, Set<Character> visited, List<Character> result) {
        if (visited.contains(start)) {
            return;
        }
        index.get(start).stream()
                .filter(child -> !visited.contains(child))
                .forEach(child -> visit(child, index, visited, result));

        result.add(start);
        visited.add(start);
    }

    public String recoverSecret(char[][] triplets) {
        Map<Character, Set<Character>> index = new HashMap<>();
        for (char[] triplet: triplets) {
            for (int i = 0; i < triplet.length - 1; i++) {
                if (!index.containsKey(triplet[i])) {
                    index.put(triplet[i], new HashSet<>());
                }
                index.get(triplet[i]).add(triplet[i + 1]);
            }
            if (!index.containsKey(triplet[2])) {
                index.put(triplet[2], new HashSet<>());
            }
        }
        List<Character> result = new LinkedList<>();
        Set<Character> visited = new HashSet<>();
        for (Character key: index.keySet()) {
            visit(key, index, visited, result);
        }

        return result.stream().collect(
                () -> new StringBuilder(),
                (sb, c) -> sb.insert(0, c),
                (a, b) -> a.append(b))
                .toString();

    }

}
