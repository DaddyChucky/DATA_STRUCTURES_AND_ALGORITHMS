// Toute modification a ce fichier ne sera pas comptabilisée
public class TestHelper {
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String WHITE = "\u001B[37m";

    public static void printHeader(String header) {
        System.out.println(WHITE + header);
    }

    public static void printError(String content) {
        System.out.println(RED + content);
    }

    public static void printTest(Boolean isGood) {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println((isGood ? GREEN : RED) + " > " + methodName);
    }
}
