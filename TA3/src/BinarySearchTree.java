import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<? super T> > {

    private BinaryNode<T> root;

    public BinarySearchTree() { }

    public BinarySearchTree(T item) { root = new BinaryNode<>(item); }

    // O(log(n))
    public void insert(T item) { root.insert(item); }

    // O(log(n))
    public boolean contains(T item) { return root.contains(item); }

    // O(n)
    public int getHeight() {
        return root.getHeight();
    }

    // O(n)
    public List<BinaryNode<T>> getItemsInOrder() {
        List<BinaryNode<T>> orderedNodes = new ArrayList<>();
        root.fillListInOrder(orderedNodes);
        return orderedNodes;
    }

    // O(n)
    public String toStringInOrder() {
        final List<BinaryNode<T>> orderedNodes = getItemsInOrder();

        if (orderedNodes.size() == 0)
            return "[]";

        final ArrayList<T> nodesToPrint = new ArrayList<>(); //? Pour l'ajout en fin de liste en O(1)

        for (BinaryNode<T> node : orderedNodes)
            nodesToPrint.add(node.getData());

        return nodesToPrint.toString();
    }
}
