import java.util.List;

public class BinaryNode<T extends Comparable<? super T> > {
    private T data;
    private BinaryNode<T> right;
    private BinaryNode<T> left;

    //? Pour la hauteur de l'arbre, initialisés à -1 pour tenir compte que le noeud racine (root) est de niveau 0
    private int leftHeight  = -1;
    private int rightHeight = -1;

    // O(1)
    public BinaryNode(T data) {
        this.data = data;
        this.right = null;
        this.left = null;
    }

    // O(1)
    public T getData() { return data; }

    // O(log(n))
    public void insert(T item) {
        BinaryNode<T>       iteratedChild   = this;
        final BinaryNode<T> newChild        = new BinaryNode<>(item);

        while (true) {
            if (item.compareTo(iteratedChild.data) <= 0) {
                if (iteratedChild.left == null) {
                    iteratedChild.left = newChild;
                    break;
                }
                iteratedChild = iteratedChild.left;
            } else if (item.compareTo(iteratedChild.data) > 0) {
                if (iteratedChild.right == null) {
                    iteratedChild.right = newChild;
                    break;
                }
                iteratedChild = iteratedChild.right;
            }
        }
    }

    // O(log(n))
    public boolean contains(T item) {
        BinaryNode<T> currentNode = this;

        while (currentNode != null){
            if (item.equals(currentNode.data))
                return true;
            else if (item.compareTo(currentNode.data) < 0)
                currentNode = currentNode.left;
            else
                currentNode = currentNode.right;
        }

        return false;
    }

    // O(n)
    /* Source:  Fonction inspirée des notes de cours d'Ettore Merlo (ch. 5), de
                https://www.javatpoint.com/java-program-to-find-the-maximum-depth-or-height-of-a-tree et de
                https://www.geeksforgeeks.org/write-a-c-program-to-find-the-maximum-depth-or-height-of-a-tree/ */

    public int getHeight() {
        if (right != null)
            rightHeight = right.getHeight();

        if (left != null)
            leftHeight = left.getHeight();

        if (rightHeight > leftHeight)
            return rightHeight + 1;
        else
            return leftHeight + 1;
    }

    // O(n)
    /* Source:  Fonction inspirée des notes de cours d'Ettore Merlo (ch. 5), de
                https://www.geeksforgeeks.org/arraylist-iterator-method-in-java-with-examples/ et de
                https://practice.geeksforgeeks.org/problems/inorder-traversal */
    public void fillListInOrder(List<BinaryNode<T>> result) {
        if (left != null)
            left.fillListInOrder(result);

        result.add(this);

        if (right != null)
            right.fillListInOrder(result);
    }
}
