package tp2;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Iterator;

public final class Interview {

    /** TODO
     * This function returns if the two texts are similar based on if they have a similar entropy of the HashMap
     * @return boolean based on if the entropy is similar
     */
    public static Double compareEntropies(String filename1, String filename2) throws IOException {
        return calculateEntropy(getFrequencyHashTable(readFile(filename2))) -
                calculateEntropy(getFrequencyHashTable(readFile(filename1)));
    }

    /** TODO
     * This function returns the difference in frequencies of two HashMaps which corresponds
     * to the sum of the differences of frequencies for each letter.
     * @return the difference in frequencies of two HashMaps
     */
    public static Integer compareFrequencies(String filename1, String filename2) throws IOException {
        HashMap<Character, Integer> hashMap1 = getFrequencyHashTable(readFile(filename1)),
                                    hashMap2 = getFrequencyHashTable(readFile(filename2));

        Integer totalFrequency = 0;

        for (Character key : hashMap2.keySet()) {
            if (hashMap1.containsKey(key))
                totalFrequency += Math.abs(hashMap2.get(key) - hashMap1.get(key));
            else
                totalFrequency += hashMap2.get(key);
        }

        for (Character key : hashMap1.keySet()) {
            if (!hashMap2.containsKey(key))
                totalFrequency += hashMap1.get(key);
        }

        return totalFrequency;
    }

    /** TODO
     * @return This function returns the entropy of the HashMap
     */
    public static Double calculateEntropy(HashMap<Character, Integer> map){
        double entropy = 0.0;
        Double totalOccurences = 0.0;

        for (Integer data : map.values())
            totalOccurences += data;

        for (Integer data : map.values())
            entropy += data / totalOccurences * (Math.log(data / totalOccurences) / Math.log(2));

        return -entropy;
    }

    /**
     * This function reads a text file {filename} and returns the appended string of all lines
     * in the text file
     */
    public static String readFile(String filename) throws IOException {
        /* Source: cette fonction s'inspire fortement de https://www.w3schools.com/java/java_files_read.asp */
        String fileContent = "";

        try {
            File file = new File(filename);
            Scanner myFileReader = new Scanner(file);

            while (myFileReader.hasNextLine())
                fileContent += myFileReader.nextLine();

            myFileReader.close();

            return fileContent;

        } catch (FileNotFoundException e) {
            return fileContent;
        }
    }

    /** TODO
     * This function takes a string as a parameter and creates and returns a HashTable
     * of character frequencies
     */
    public static HashMap<Character, Integer> getFrequencyHashTable(String text) {
        HashMap<Character, Integer> myHashMap =
                new HashMap<>(31); // Prime adjacent to 26 (n letters in the alphabet)

        for (char character : text.toCharArray()) {
            if (isAlphabetic(character)) {
                if (myHashMap.get(character) != null)
                    myHashMap.put(character, myHashMap.get(character) + 1);
                else
                    myHashMap.put(character, 1);
            }
        }

        return myHashMap;
    }

    /** TODO
     * This function takes a character as a parameter and returns if it is a letter in the alphabet
     */
    public static Boolean isAlphabetic(Character c) { return Character.isLetter(c); }
}
