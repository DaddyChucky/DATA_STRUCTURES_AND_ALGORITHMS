// Toute modification a ce fichier ne sera pas comptabilisée
public class Main {

    // Le main fait simplement tester votre logique pour les deux exercices
    public static void main(String[] args) {
        System.out.println("Debut des tests du TP3");
        TestBinary testBinary = new TestBinary();
        testBinary.test();
        System.out.println("Tests BinarySearchTree complets!");
        TestInterview testInterview = new TestInterview();
        testInterview.test();
        System.out.println("Tests Interview complets!");
    }
}
