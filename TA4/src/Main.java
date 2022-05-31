import java.util.*;

public class Main {
    public static void main(String[] args){
        List<Integer> elements = new ArrayList<>();
        for(int i = 1; i <= 15; i++){
            elements.add(i);
        }

        Heap<Integer> maxHeap = new Heap<>(true, elements);
        Heap<Integer> minHeap = new Heap<>(false, elements);

        Interview question = new Interview();

        tests Test = new tests(minHeap, maxHeap, question);
        Test.run();
    }
}
