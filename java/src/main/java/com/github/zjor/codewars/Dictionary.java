package com.github.zjor.codewars;

import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * I'm sure, you know Google's "Did you mean ...?", when you entered a search term and mistyped a word. In this kata we want to implement something similar.

 You'll get an entered term (lowercase string) and an array of known words (also lowercase strings). Your task is to find out, which word from the dictionary is most similar to the entered one. The similarity is described by the minimum number of letters you have to add, remove or replace in order to get from the entered word to one of the dictionary. The lower the number of required changes, the higher the similarity between each two words.

 Same words are obviously the most similar ones. A word that needs one letter to be changed is more similar to another word that needs 2 (or more) letters to be changed. E.g. the mistyped term berr is more similar to beer (1 letter to be replaced) than to barrel (3 letters to be changed in total).

 Extend the dictionary in a way, that it is able to return you the most similar word from the list of known words.
 */
public class Dictionary {
    private final String[] words;

    public Dictionary(String[] words) {
        this.words = words;
    }

    private int min(int... a) {
        if (a.length == 0) {
            throw new NoSuchElementException();
        }
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            min = Math.min(min, a[i]);
        }
        return min;
    }

    private int levinshteinDistance(String a, String b) {
        int[] prev = new int[a.length() + 1];
        int[] curr = new int[prev.length];

        for (int i = 0; i < prev.length; i++) {
            prev[i] = i;
        }

        for (int i = 0; i < b.length(); i++) {
            curr[0] = i + 1;
            for (int j = 1; j < curr.length; j++) {
                int cost = a.charAt(j - 1) == b.charAt(i) ? 0 : 1;
                curr[j] = min(curr[j - 1] + 1, prev[j] + 1, prev[j - 1] + cost);
            }
            System.arraycopy(curr, 0, prev, 0, curr.length);
        }
        return curr[curr.length - 1];
    }

    public String findMostSimilar(String to) {
        TreeMap<Integer, String> distances = new TreeMap<>();
        for (String word: words) {
            distances.put(levinshteinDistance(word, to), word);
        }
        return distances.firstEntry().getValue();
    }

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary(new String[]{"cherry", "pineapple", "melon", "strawberry", "raspberry"});
        System.out.println(dictionary.findMostSimilar("strawbery"));
        System.out.println(dictionary.findMostSimilar("berry"));

        dictionary = new Dictionary(new String[]{"javascript", "java", "ruby", "php", "python", "coffeescript"});
        System.out.println(dictionary.findMostSimilar("heaven"));
        System.out.println(dictionary.findMostSimilar("javascript"));
    }
}
