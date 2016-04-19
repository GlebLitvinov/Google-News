package com.home.textutils.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class TextUtilsTest {

    @Test
    public void testNumberOfOccurrences() throws Exception {
        String word = "cAt";
        String text = "Cat eats cat but why should cat eat catcat. Cat cat.";
        assertEquals(7, TextUtils.numberOfOccurrences(text, word));
        assertEquals("cAt", word);
    }

    @Test
    public void testNumberOfOccurencesAsWord() throws Exception {
        String word = "cAt";
        String text = "Cat eats cat but why should cat eat catcat. Cat eats cat.";
        assertEquals(5, TextUtils.numberOfOccurrencesAsWord(text, word));
        assertEquals("cAt", word);

    }

    @Test
    public void testNumberOfOccurrencesException(){
        String word = "cat cat";
        String text = "Cat eats cat but why should cat eat catcat";
        try {
            TextUtils.numberOfOccurrencesAsWord(word, text);
        } catch (TextUtilsException e){
            assertEquals("Given string is not a word", e.getMessage());
        }
    }
}