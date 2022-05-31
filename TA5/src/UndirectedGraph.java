import java.util.HashSet;

public class UndirectedGraph implements Graph {
    public HashSet<Integer>[] neighbours;
    public int nodeQuantity;
    public int graphEdges;

    @Override
    public void initialize(int numNodes) {
        this.nodeQuantity = numNodes;
        graphEdges = 0;
        neighbours = new HashSet[numNodes];
        for(int i = 0; i < numNodes; i++) neighbours[i] = new HashSet<>();
    }

    @Override
    public void connect(int v1, int v2){
        /*
        Justification des conditions de connexion :

        1) On s'assure que les sommets v1 et v2 se trouvent dans le graphe. Pour ce faire, v1 et v2 doivent être entre
        [0, nodeQuantity). Nous ne pouvons pas ajouter une arête entre des noeuds non existants.

        2) On s'assure qu'il n'y a pas déjà d'arêtes entre v1 et v2. Pour ce faire, nous vérifions que v2 n'est pas dans
        les voisins de v1. Nous ne voulons pas ajouter des duplicats d'arêtes. Nous vérifions seulement si v2 se trouve
        dans les voisins de v1 et non l'inverse étant donné que nous sommes en présence d'un graphe non dirigé et que
        nécessairement les arêtes sont bidirectionelles. Ainsi, si v1 se trouve dans les voisins de v2, alors v2 se
        trouve dans les voisins de v1.
        */
        if (v1 < 0 || v1 >= nodeQuantity || v2 < 0 || v2 >= nodeQuantity || neighbours[v1].contains(v2)) return;
        neighbours[v1].add(v2);
        neighbours[v2].add(v1);
        ++graphEdges;
    }

    public String toString(){
        StringBuilder o = new StringBuilder();
        String ln = System.getProperty("line.separator");
        o.append(nodeQuantity).append(ln).append(graphEdges).append(ln);
        for(int v = 0; v< nodeQuantity; v++)
            for(Object w : neighbours[v])
                o.append(v).append("-").append(w).append(ln);
        return o.toString();
    }

    @Override
    public HashSet<Integer> adj(int v) {
        return v < 0 || v >= nodeQuantity ? null : neighbours[v];
    }

    public UndirectedGraph(int numNodes){
        initialize(numNodes);
    }
}
