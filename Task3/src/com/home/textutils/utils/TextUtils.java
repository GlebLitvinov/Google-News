package com.home.textutils.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {

    public static int numberOfOccurrences(String source, String sentence) throws TextUtilsException {
        int occurrences = 0;
        source = source.trim().toLowerCase();
        sentence = sentence.trim().toLowerCase();
        if (source.contains(sentence)) {
            int withSentenceLength    = source.length();
            int withoutSentenceLength = source.replace(sentence, "").length();
            occurrences = (withSentenceLength - withoutSentenceLength) / sentence.length();
        }
        return occurrences;
    }

    public static int numberOfOccurrencesAsWord(String source, String word) throws TextUtilsException {
        if(!isWord(word)){
            throw new TextUtilsException("Given string is not a word");
        }
        int occurrences = 0;
        source = source.trim().toLowerCase();
        word = word.trim().toLowerCase();
        Pattern pattern = Pattern.compile("(^|\\s+)("+word+")(\\s+|$|\\p{Punct})");
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            occurrences++;
        }
        return occurrences;
    }

    private static boolean isWord(String text){
        text = text.trim();
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()){
            return false;
        }
        return true;
    }
}
