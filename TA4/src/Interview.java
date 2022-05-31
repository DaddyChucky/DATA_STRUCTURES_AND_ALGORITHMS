/* MAT. 2076524 / 2088742 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class Interview {

        // En moyenne : O(nlog(n))
        public int lastBox(int[] boxes){
            PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
            final int nBoxesLeft = 1;

            // En moyenne, O(n) (l'ajout (head.add) s'effectue en temps constant O(1))
            for (int box : boxes)
                heap.add(box);

            // En moyenne, O(nlog(n)), si le nombre de boîtes tend vers l'infini (n) et que le remove s'effectue en
            // moyenne en log(n) et que l'ajout (heap.add) s'effectue en temps constant O(1)
            while (heap.size() > nBoxesLeft) {
                final int removedBox = heap.remove();
                if (removedBox != heap.peek()) // Ne peut pas produire NullPointerException étant donné que heap.size > nBoxesLeft = 1
                    heap.add(removedBox - heap.remove());
                else
                    heap.remove();
            }

            // Temps constant (O(1)), car heap.size() rendu ici est soit 1 ou 0
            return heap.size() == nBoxesLeft ? heap.peek() : 0;
        }
}
