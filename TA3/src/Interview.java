public class Interview {

    //? Source: Fonction inspirée de https://leetcode.com/problems/merge-two-binary-trees/
    public static TreeNode solution (TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        else if (t2 == null)
            return t1;

        t1.val      = Math.min(t1.val, t2.val);
        t1.left     = solution(t1.left, t2.left);
        t1.right    = solution(t1.right, t2.right);

        return t1;
    }
}
