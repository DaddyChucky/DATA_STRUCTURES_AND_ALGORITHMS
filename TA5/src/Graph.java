import java.util.HashSet;

public interface Graph {
    void initialize(int numNodes);
    void connect(int v1, int v2);
    HashSet<Integer> adj(int v);
}
