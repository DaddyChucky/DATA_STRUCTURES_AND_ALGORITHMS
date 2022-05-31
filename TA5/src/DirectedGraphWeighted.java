import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class DirectedGraphWeighted {
    public HashSet<Vertex>[] neighbours;
    public int vertexCapacity;
    public int edgeQuantity;

    public void initialize(int numNodes) {
        // Cours #9 T. Ould Bachir, regarder si le nombre de sommets est valide avant d'initialiser le graphe
        if (numNodes < 0) throw new InvalidParameterException();
        neighbours = new HashSet[numNodes];
        for (int n = 0; n < numNodes; ++n) neighbours[n] = new HashSet<>();
        vertexCapacity = numNodes;
        edgeQuantity = 0;
    }

    public void connect(int v1, Vertex vertex) {
        if (v1 < 0 || v1 >= vertexCapacity || neighbours[v1].contains(vertex) ||
                vertex == null || vertex.index < 0 || vertex.index >= vertexCapacity) return;
        neighbours[v1].add(vertex);
        ++edgeQuantity;
    }

    public String toString() {
        StringBuilder o = new StringBuilder();
        String ln = System.getProperty("line.separator");
        o.append(vertexCapacity).append(ln).append(edgeQuantity).append(ln);
        for(int n = 0; n < vertexCapacity; ++n)
            for(Vertex v : neighbours[n])
                o.append(n).append("->").append(v.index).append(" | cost: ").append(v.cost).append(ln);
        return o.toString();
    }

    public HashSet<Vertex> adj(int v) { return neighbours[v]; }

    public DirectedGraphWeighted(int numNodes){
        initialize(numNodes);
    }

    /* Partie 3 : Analyse de l’implémentation de findLowestCost
    * ----------------------------------------------------------------------------------------------------------------
    * 1. Quel sera le nombre d’itération maximale et minimale pour la boucle suivante que vous avez implémenté dans
    * le deuxième TODO?
    *   Le nombre d'itérations minimales de la boucle sera de 0 dans le cas où le sommet v ne possède aucun voisin, ce
    *   qui serait le cas dans un graphe avec un seul noeud.
    *   Le nombre d'itérations maximales de la boucle sera égal au nombre de sommets total du graphe moins 1, soit :
    *   vertexCapacity - 1. En effet, ce serait le cas si nous sommes en présence d'un graphe où le sommet v est connecté
    *   avec tous les autres sommets. Ainsi, dans la boucle for, étant donné que les voisins de v seront les n -1 autres
    *   sommets, nous allons devoir effectuer le nombre maximal d'itérations.
    *
    * 2. Dans le pire cas, quel sera le nombre de modification du coût pour un sommet? Le pire cas étant le cas qui
    * cause le nombre de modifications au cout le plus élevé.
    *   Si nous considérons un seul sommet, le nombre maximal de modification du coût sera égal au nombre maximal
    *   d'itérations de la boucle. En d'autres mots, cela signifie que le coût entre le noeud et son voisin est toujours
    *   inférieur à celui précédemment inscrit dans le heap et qu'il faudra effectuer la modification à chaque itération.
    *   Ainsi, le pire cas du nombre de modifications est égal au nombre de sommets - 1 (vertexCapacity - 1), soit une
    *   situation où le noeud comparé est connecté avec tous les autres noeuds du graphe.
    *   Si nous considérons l'algorithme au complet, le nombre maximal de modifications du coût serait égal au nombre
    *   d'arêtes dans le graphe. En effet, la boucle permet de regarder les distances entre le sommet X et tous ses
    *   sommets adjacents pour savoir si les distances obtenues sont moindres que celles précédemment sauvegardées.
    *   Ainsi, il serait possible que pour chacun des appels de la boucle, nous devions changer le coût, ce qui serait
    *   égal au nombre d'arêtes du graphe, soit le nombre de relation entre un sommet X et ses voisins.
    *
    * 3. Quel sera le nombre d’itération pour la boucle que vous avez implémenté dans le troisième TODO selon
    * le nombre de sommet suivant:
    *   La boucle for(int n = 1; n <= vertexCapacity; ++n) permet de parcourir tous les sommets présents dans le heap
    *   afin d'additionner les coûts pour trouver le coût total. Nous commençons à 1 puisque les index du heap commencent
    *   à 1 au lieu de 0. Ainsi, nous allons faire la boucle : (vertexCapacity) fois, soit le nombre total de sommets.
    *       a. 10 sommets   : 10 itérations de la boucle
    *       b. 100 sommets  : 100 itérations de la boucle
    *       c. 1000 sommets : 1000 itérations de la boucle
    *
    * */
    public int findLowestCost() {
        final int OFFSET_HEAP = 1;
        int totalCost = 0;
        Heap vertices = new Heap(vertexCapacity + 1);
        int newDistance;

        for (int n = OFFSET_HEAP; n < vertexCapacity; ++n)
            vertices.add(new Vertex(Integer.MAX_VALUE, n));

        while(true){
            Vertex v = vertices.findSmallestUnknown();
            if(v == null) break;
            v.known = true;

            for(Vertex w: adj(v.index)){
                if (!w.known){
                    newDistance = w.cost + v.cost;
                    if(newDistance < vertices.Heap[w.index + OFFSET_HEAP].cost) {
                        vertices.decreaseKey(w, newDistance);
                        vertices.Heap[w.index + OFFSET_HEAP].path = v;
                    }
                }
            }
        }

        for(int n = OFFSET_HEAP; n <= vertexCapacity; ++n)
            totalCost += vertices.Heap[n].cost;

        return totalCost;
    }
}
