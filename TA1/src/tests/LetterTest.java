package tests;

import net.sourceforge.tess4j.Tesseract;
import tp1.LetterPlacer;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import tp1.Point2d;
import tp1.PointOperator;

import static org.junit.jupiter.api.Assertions.*;

public class LetterTest {

    @Test
    void all() throws Exception {
        Tesseract tesseract = new Tesseract();
        LetterPlacer letterPlacer = new LetterPlacer();
        letterPlacer.placeNext('l');
        letterPlacer.placeNext('a');
        letterPlacer.placeNextln('B');
        letterPlacer.placeNext('e');
        letterPlacer.placeNext('l');
        letterPlacer.placeNext('l');
        letterPlacer.placeNext('e');
        letterPlacer.placeNextln('V');
        letterPlacer.placeNext('i');
        letterPlacer.placeNext('e');
        letterPlacer.saveImage("all", true);
        File tmpFile = new File("all.jpg");
        String output = tesseract.doOCR(tmpFile)
                .replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String input = "labellevie";
        Desktop dt = Desktop.getDesktop();
        boolean foundEnoughChars = findMatchingChars(output, input).doubleValue() > 4;
        if (!foundEnoughChars) {
            dt.open(tmpFile);
            assertTrue(foundEnoughChars, "Nous ne pouvons pas reconnaitre assez de characteres");
        }
        else {
            File imgFile = letterPlacer.saveImage("image", false);
            dt.open(imgFile);
        }
    }

    private static Integer findMatchingChars(String str1, String str2) {
        Set<String> chars1 = new HashSet<String>(Arrays.asList(str1.split("(?!^)")));
        Set<String> chars2 = new HashSet<String>(Arrays.asList(str2.split("(?!^)")));
        chars1.retainAll(chars2);
        return chars1.size();
    }
}
