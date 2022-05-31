import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

// Toute modification a ce fichier ne sera pas comptabilisée
public class TestBinary {
    public void test() {
        TestHelper.printHeader("Debut des tests de l'arbre de recherche binaire");
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method m : methods) {
            if (!m.getName().equals("test")) {
                try {
                    m.invoke(this);
                }
                catch (InvocationTargetException e) {
                    TestHelper.printError(e.getTargetException().getCause().getMessage());
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void testRandom() throws Exception {
        Integer max = 1000;
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(max / 2);
        List<Integer> list = new ArrayList<>();
        list.add(max / 2);
        Random rand = new Random();
        for (int i = 0; i < max; ++i) {
            Integer next = rand.nextInt(max);
            tree.insert(next);
            list.add(next);
        }
        Collections.sort(list);
        String inOrder = tree.toStringInOrder();
        String sortedArray = Arrays.toString(list.toArray());
        TestHelper.printTest(inOrder.equals(sortedArray));
    }

    private void testRandomContains() {
        Integer max = 1000;
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(max / 2);
        List<Integer> list = new ArrayList<>();
        list.add(max / 2);
        Random rand = new Random();
        for (int i = 0; i < max; ++i) {
            Integer next = rand.nextInt(max);
            tree.insert(next);
            list.add(next);
        }
        Integer randInt = list.get(rand.nextInt(max));
        TestHelper.printTest(tree.contains(randInt));
    }

    private void testRandomNotContains() {
        Integer max = 1000;
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(max / 2);
        Random rand = new Random();
        for (int i = 0; i < max; ++i) {
            Integer next = rand.nextInt(max);
            tree.insert(next);
        }
        TestHelper.printTest(!tree.contains(max));
    }

    private void testWithOneValue() {
        Random rand = new Random();
        Integer next = rand.nextInt(1000);
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(next);
        List<Integer> list = new ArrayList<>();
        list.add(next);
        String inOrder = tree.toStringInOrder();
        String sortedArray = Arrays.toString(list.toArray());
        TestHelper.printTest(inOrder.equals(sortedArray));
    }

    private void testWithRightValues() {
        Integer max = 1000;
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(0);
        List<Integer> list = new ArrayList<>();
        list.add(0);
        for (int i = 1; i < max; ++i) {
            tree.insert(i);
            list.add(i);
        }
        Collections.sort(list);
        String inOrder = tree.toStringInOrder();
        String sortedArray = Arrays.toString(list.toArray());
        TestHelper.printTest(inOrder.equals(sortedArray));
    }

    private void testWithLeftValues() {
        Integer max = 1000;
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(1000);
        List<Integer> list = new ArrayList<>();
        list.add(1000);
        for (int i = 1000 - 1; i >= 0; --i) {
            tree.insert(i);
            list.add(i);
        }
        Collections.sort(list);
        String inOrder = tree.toStringInOrder();
        String sortedArray = Arrays.toString(list.toArray());
        TestHelper.printTest(inOrder.equals(sortedArray));
    }

    private void testHeightZero() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(10);
        TestHelper.printTest(0 == tree.getHeight());
    }

    private void testHeightRandom() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(10);
        tree.insert(8);
        tree.insert(8);
        tree.insert(9);
        tree.insert(12);
        tree.insert(15);
        tree.insert(14);
        tree.insert(14);
        tree.insert(15);
        tree.insert(15);
        TestHelper.printTest(5 == tree.getHeight());
    }

    private void testHeightOneSide() {
        Integer max = 1000;
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(0);
        for (int i = 1; i < max; ++i) {
            tree.insert(i);
        }
        TestHelper.printTest(max - 1 == tree.getHeight());
    }

    private void testComplexity() {
        long startTime = System.nanoTime();
        int max = 2000;
        boolean isGood = true;
        for (int j = 0; j < max; ++j) {
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(max / 2);
            List<Integer> list = new ArrayList<>();
            list.add(max / 2);
            Random rand = new Random();
            for (int i = 0; i < max; ++i) {
                Integer next = rand.nextInt(max);
                tree.insert(next);
                list.add(next);
            }
            Collections.sort(list);
            String inOrder = tree.toStringInOrder();
            String sortedArray = Arrays.toString(list.toArray());
            isGood &= inOrder.equals(sortedArray);
        }
        long duration = (System.nanoTime() - startTime);
        TestHelper.printTest(isGood && duration < 1600000000);
    }

    private void testContainsComplexity() {
        long startTime = System.nanoTime();
        int max = 2000;
        boolean isGood = true;
        for (int j = 0; j < max; ++j) {
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(max / 2);
            List<Integer> list = new ArrayList<>();
            list.add(max / 2);
            Random rand = new Random();
            for (int i = 0; i < max; ++i) {
                Integer next = rand.nextInt(max);
                tree.insert(next);
                list.add(next);
            }
            Integer randInt = list.get(rand.nextInt(max));
            isGood &= tree.contains(randInt);
        }
        long duration = (System.nanoTime() - startTime);
        TestHelper.printTest(isGood && duration < 500000000);
    }

    private void testHeightComplexity() {
        long startTime = System.nanoTime();
        int max = 1000;
        boolean isGood = true;
        for (int j = 0; j < max; ++j) {
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(0);
            for (int i = 1; i < max; ++i) {
                tree.insert(i);
            }
            isGood &= max - 1 == tree.getHeight();
        }
        long duration = (System.nanoTime() - startTime);
        //System.out.println("Our duration: " + duration + " diff: " + (duration - 1600000000));
        TestHelper.printTest(isGood && duration < 1600000000);
    }
}
