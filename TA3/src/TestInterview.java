import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Toute modification a ce fichier ne sera pas comptabilisée
public class TestInterview {
    public void test() {
        TestHelper.printHeader("Debut des tests de l'arbre de companies");
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

    public boolean compareTrees(TreeNode p, TreeNode q) {
        if (p == q) return true; // null == null
        if (p == null || q == null) return false;
        return p.val == q.val &&
                compareTrees(p.right , q.right) &&
                compareTrees(p.left, q.left);
    }

    private void testTwoEmptyTrees() {
        TreeNode expected = null;
        TreeNode result = Interview.solution(null, null);

        boolean testPassed = compareTrees(expected, result);
        TestHelper.printTest(testPassed);
    }

    private void testPDFExample() {
        TreeNode leftTree = new TreeNode(1,
                new TreeNode(3, new TreeNode(5), null),
                new TreeNode(2));

        TreeNode rightTree = new TreeNode(2,
                new TreeNode(1, null, new TreeNode(4)),
                new TreeNode(3, null, new TreeNode(7)));

        TreeNode expected = new TreeNode(1,
                new TreeNode(1, new TreeNode(5), new TreeNode(4)),
                new TreeNode(2, null, new TreeNode(7)));

        TreeNode result = Interview.solution(leftTree, rightTree);
        boolean testPassed = compareTrees(expected, result);
        TestHelper.printTest(testPassed);
    }

    private void testEmptyFull() {
        TreeNode leftTree = new TreeNode(1,
                new TreeNode(3, new TreeNode(5), null),
                new TreeNode(2));

        TreeNode rightTree = null;

        TreeNode expected = new TreeNode(1,
                new TreeNode(3, new TreeNode(5), null),
                new TreeNode(2));

        TreeNode result = Interview.solution(leftTree, rightTree);
        boolean testPassed = compareTrees(expected, result);
        TestHelper.printTest(testPassed);
    }

    private void testLeftRight() {
        TreeNode leftTree = new TreeNode(1,
                null,
                 new TreeNode(5, null, new TreeNode(3)));

        TreeNode rightTree = new TreeNode(2,
                new TreeNode(5, new TreeNode(3), null),
                null);

        TreeNode expected = new TreeNode(1,
                new TreeNode(5, new TreeNode(3), null),
                new TreeNode(5, null, new TreeNode(3))
                );

        TreeNode result = Interview.solution(leftTree, rightTree);
        boolean testPassed = compareTrees(expected, result);
        TestHelper.printTest(testPassed);
    }

}
