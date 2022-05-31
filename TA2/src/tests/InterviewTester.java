package tests;

import tp2.Interview;

import java.io.IOException;
import java.util.*;

public class InterviewTester {
    public static Double start() {
        double subTotal = 0.0;

        subTotal += Corrector.executeUnitTest("testIsAlphabetic", InterviewTester::testIsAlphabetic, 1);
        subTotal += Corrector.executeUnitTest("testGetFrequencyHashTable", InterviewTester::testGetFrequencyHashTable, 1);
        subTotal += Corrector.executeUnitTest("testReadFile", InterviewTester::testReadFile, 1);
        subTotal += Corrector.executeUnitTest("testCalculateEntropy", InterviewTester::testCalculateEntropy, 1);
        subTotal += Corrector.executeUnitTest("testCompareFrequencies", InterviewTester::testCompareFrequencies, 1);
        subTotal += Corrector.executeUnitTest("testCompareEntropies", InterviewTester::testCompareEntropies, 1);


        return subTotal;
    }

    private static Boolean testCompareEntropies() {
        double result1 = 0, result2 = 0;
        double expected1 = 0.11890087653512937, expected2 = 0.09391688670458942;
        try {
            result1 = Interview.compareEntropies("quote1.txt", "quote2.txt");
            result2 = Interview.compareEntropies("quote2.txt", "quote3.txt");
        } catch (IOException e){}

        return Math.abs(result1 - expected1) < 0.02 && Math.abs(result2 - expected2) < 0.02 ;
    }

    private static Boolean testCompareFrequencies() {
        int result1 = 0, result2 = 0;
        int expected1 = 67, expected2 = 64;
        try {
            result1 = Interview.compareFrequencies("quote1.txt", "quote2.txt");
            result2 = Interview.compareFrequencies("quote2.txt", "quote3.txt");
        } catch (IOException e) {}

        return result1 == expected1 && result2 == expected2 ;
    }

    public static boolean testIsAlphabetic() {
        char a = 'a', one = '1', exclamation = '!', capZ = 'Z';
        return Interview.isAlphabetic(a) && !Interview.isAlphabetic(one) &&
                !Interview.isAlphabetic(exclamation) && Interview.isAlphabetic(capZ);
    }

    public static boolean testGetFrequencyHashTable() {
        HashMap<Character, Integer> testMap = new HashMap<Character, Integer>() {{
            put('H', 1);
            put('W', 1);
            put('h', 1);
            put('e', 1);
            put('l', 3);
            put('o', 2);
            put('d', 1);
            put('r', 1);
        }};

        return Interview.getFrequencyHashTable("Hello World!;  h23@@").equals(testMap);
    }

    public static boolean testReadFile() {
        String result = "";
        try {
            result = Interview.readFile("simple_text.txt");
        }
        catch (IOException e){}

        String expected = "Hello World  ;This";

        return result.equals(expected);
    }

    public static boolean testCalculateEntropy(){
        double result = Interview.calculateEntropy(new HashMap<>(){{put('a', 5); put('b', 18); put('v', 34);}});
        double expected = 1.2777667316575494;
        return Math.abs(result - expected) < 0.05;

    }

}
