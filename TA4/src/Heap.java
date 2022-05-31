/* MAT. 2076524 / 2088742 */

import java.util.*;

public class Heap<ValueType extends Comparable<? super ValueType>> implements Iterable<ValueType> {

        public ArrayList<ValueType> elements;
        private final boolean       isMax;

        public int size()       { return elements.size() - 1; }
        public int halfSize()   { return size() / 2; }

        @Override
        public Iterator<ValueType> iterator() { return elements.listIterator(1); }

        public Heap(boolean isMax, Collection<ValueType> elements) {
            this.isMax = isMax;
            this.elements = new ArrayList<>();
            this.elements.add(null);
            this.elements.addAll(elements);
            buildHeap();
        }

        // O(1), temps constant
        protected boolean compare(ValueType first, ValueType second) {
            return isMax ? second.compareTo(first) > 0 : first.compareTo(second) > 0;
        }

        // O(1), temps constant
        public int parentIndex(int index) { return index / 2; }

        // O(1), temps constant
        public int leftChildIndex(int index) { return index * 2; }

        // O(1), temps constant
        public int rightChildIndex(int index) { return index * 2 + 1; }

        // O(1), temps constant
        public boolean isLeaf(int pos) { return leftChildIndex(pos) > elements.size(); }

        // O(n), temps linéaire
        // Tiré des notes de cours au ppt. #7 (p. 27) de E. Merlo, S. Kadoury, M. Gagnon et T. Ould-Bachir
        public void buildHeap() {
            for (int i = halfSize(); i > 0; --i)
                percolateDown(i);
        }

        // O(1), constant
        private void swap(int currentIndex, int parentIndex) {
            final ValueType parentIndexElement = elements.get(parentIndex);
            elements.set(parentIndex, elements.get(currentIndex));
            elements.set(currentIndex, parentIndexElement);
        }

        // Pire cas: O(log(n)), logarithmique (base 2); Moyen et meilleur cas: O(1), constant
        // Tiré des notes de cours au ppt. #7 (p. 13 et 22) de E. Merlo, S. Kadoury, M. Gagnon et T. Ould-Bachir
        public void insert(ValueType value) {
            elements.add(null);
            int hole = size();
            for(; hole > 1 && compare(elements.get(parentIndex(hole)), value); hole = parentIndex(hole))
                swap(hole, parentIndex(hole));
            elements.set(hole, value);
        }

        // Moyen et pire cas: O(log(n)), logarithmique (base 2); Meilleur cas: O(1), constant
        // Tiré des notes de cours au ppt. #7 (p. 21 et 22) de E. Merlo, S. Kadoury, M. Gagnon et T. Ould-Bachir
        private void percolateDown(int index) {
            int child;
            ValueType temp = elements.get(index);
            for(; index * 2 < size(); index = child){
                child = index * 2;
                if(child != size() && compare(elements.get(child), elements.get(child + 1)))
                    child++;
                if(compare(temp, elements.get(child)))
                    elements.set(index, elements.get(child));
                else
                    break;
            }
            elements.set(index, temp);
        }

        // O(n), temps linéaire
        public List<ValueType> getLeftElements(){
            ArrayList<ValueType> leftElements = new ArrayList<>();
            for (int i = 1; i <= halfSize(); ++i)
                leftElements.add(elements.get(leftChildIndex(i)));
            return leftElements;
        }

        // O(n), temps linéaire
        public List<ValueType> getRightElements(){
            ArrayList<ValueType> rightElements = new ArrayList<>();
            for (int i = 1; i <= halfSize(); ++i)
                rightElements.add(elements.get(rightChildIndex(i)));
            return rightElements;
        }

        // O(n), temps linéaire
        public List<ValueType> getParentElements(){
            ArrayList<ValueType> parentElements = new ArrayList<>();
            for (int i = 2; i < elements.size(); i += 2)
                parentElements.add(elements.get(parentIndex(i)));
            return parentElements;
        }

        // O(n), temps linéaire
        public List<ValueType> getLeaves(){
            ArrayList<ValueType> leaves = new ArrayList<>();
            int i = elements.size();
            while (isLeaf(i--))
                leaves.add(elements.get(i));
            return leaves;
        }
}
