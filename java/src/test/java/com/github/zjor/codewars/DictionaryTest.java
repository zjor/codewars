package com.github.zjor.codewars;


import org.junit.Assert;
import org.junit.Test;

public class DictionaryTest {

    @Test
    public void testA() {
        Dictionary dictionary = new Dictionary(new String[]{"cherry", "pineapple", "melon", "strawberry", "raspberry"});
        Assert.assertEquals("strawberry", dictionary.findMostSimilar("strawbery"));
        Assert.assertEquals("cherry", dictionary.findMostSimilar("berry"));
    }

    @Test
    public void testB() {
        Dictionary dictionary = new Dictionary(new String[]{"javascript", "java", "ruby", "php", "python", "coffeescript"});
        Assert.assertEquals("java", dictionary.findMostSimilar("heaven"));
        Assert.assertEquals("javascript", dictionary.findMostSimilar("javascript"));
    }

}
