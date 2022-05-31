package tp2;

import tests.Corrector;

import java.io.IOException;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Bienvenue au deuxieme labo de INF2010!");
        Corrector.executeTester("AllTesters", Corrector::start, 16.0);
    }

}
