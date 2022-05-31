import java.util.List;


public class tests {
    Heap<Integer> minHeap;
    Heap<Integer> maxHeap;
    Interview question;
    int[] referenceMaxHeap = {15,11,14,9,10,13,7,8,4,2,5,12,6,3,1};
    int[] referenceMinHeap = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

    tests(Heap<Integer> minHeap, Heap<Integer> maxHeap, Interview question){
        this.minHeap = minHeap;
        this.maxHeap = maxHeap;
        this.question = question;
    }


    public void run(){
        buildHeapTest();
        getIndexTest();
        isLeafTest();
        getElementsTests();
        interviewQuestionTests();
    }

    public void buildHeapTest(){
       if(!minBuildHeapTest()){
           System.out.println("minBuildHeapTest failed");
       }
       if(!maxBuildHeapTest()){
           System.out.println("maxBuildHeapTest failed");
       }
    }

    public boolean minBuildHeapTest(){
        for(int i = 1; i < minHeap.size(); i++) {
            if (minHeap.elements.get(i) != referenceMinHeap[i-1]) {
                return false;
            }
        }
        System.out.println("minBuildHeapTest passed");
        return true;
    }

    public boolean maxBuildHeapTest(){
        for(int i = 1; i < maxHeap.size(); i++) {
            if(maxHeap.elements.get(i) != referenceMaxHeap[i-1]){
                return false;
            }
        }
        System.out.println("maxBuildHeapTest passed");
        return true;
    }

    public void getIndexTest(){
        getLeftChildMaxHeapTest();
        getRightChildMaxHeapTest();
        getLeftChildMinHeapTest();
        getRightChildMinHeapTest();
        getParentMinHeapTest();
        getParentMaxHeapTest();
    }

    public void getLeftChildMaxHeapTest(){
        if(maxHeap.elements.get(maxHeap.leftChildIndex(1)) != referenceMaxHeap[1]) {
            System.out.println("getLeftChildMaxHeapTest failed");
            return;
        }
        System.out.println("getLeftChildMaxHeapTest passed");
    }

    public void getRightChildMaxHeapTest(){
        if(maxHeap.elements.get(maxHeap.rightChildIndex(1)) != referenceMaxHeap[2]){
            System.out.println("getRightChildMaxHeapTest failed");
            return;
        }
        System.out.println("getRightChildMaxHeapTest passed");
    }

    public void getLeftChildMinHeapTest(){
        if(minHeap.elements.get(minHeap.leftChildIndex(1)) != referenceMinHeap[1]){
            System.out.println("getLeftChildMinHeapTest failed");
            return;
        }
        System.out.println("getLeftChildMinHeapTest passed");
    }

    public void getRightChildMinHeapTest(){
        if(minHeap.elements.get(minHeap.rightChildIndex(1)) != referenceMinHeap[2]){
            System.out.println("getRightChildMinHeapTest failed");
            return;
        }
        System.out.println("getLeftChildMinHeapTest passed");
    }


    public void getParentMinHeapTest(){
        if(minHeap.elements.get(minHeap.parentIndex(2)) != referenceMinHeap[0]){
            System.out.println("getParentMinHeapTest failed");
            return;
        }
        System.out.println("getParentMinHeapTest passed");
    }

    public void getParentMaxHeapTest(){
        if(maxHeap.elements.get(maxHeap.parentIndex(3)) != referenceMaxHeap[0]){
            System.out.println("getParentMaxHeapTest failed");
            return;
        }
        System.out.println("getParentMaxHeapTest passed");
    }

    public void isLeafTest(){
        if (minHeap.isLeaf(minHeap.size()) && !minHeap.isLeaf(1) && minHeap.isLeaf(6)){
            System.out.println("isLeafTest failed");
            return;
        }
        System.out.println("isLeafTest passed");
    }

    public void getElementsTests(){
        getLeftElementsTest();
        getRightElementsTest();
        getParentElementsTest();
        getLeavesTest();
    }
    public void getLeftElementsTest(){
        List<Integer> leftElements = maxHeap.getLeftElements();

        for(int i = 1; i < maxHeap.size() / 2; i++){
           if(!leftElements.contains(referenceMaxHeap[2*(i) + 1])){
               System.out.println("getLeftElementsTest failed");
               return;
           }
        }
        System.out.println("getLeftElementsTest passed");
    }

    public void getRightElementsTest(){
        List<Integer> rightElements = maxHeap.getRightElements();
        for(int i = 1; i < maxHeap.size() / 2; i++){
            if(!rightElements.contains(referenceMaxHeap[2*(i + 1)])){
                System.out.println("getRightElementsTest failed");
                return;
            }
        }
        System.out.println("getRightElementsTest passed");
    }

    public void getParentElementsTest(){
        List<Integer> parentElements = maxHeap.getParentElements();
        for(int i = maxHeap.size(); i > 1; i = i - 2){
            if(!parentElements.contains(referenceMaxHeap[(i) / 2 - 1])){
                System.out.println("getParentElementsTest failed");
                return;
            }
        }
        System.out.println("getParentElementsTest passed");
    }

    public void getLeavesTest(){
        List<Integer> leaves = maxHeap.getLeaves();
        for(int i = maxHeap.size(); i > maxHeap.size() / 2; i--){
            if(!leaves.contains(referenceMaxHeap[i-1])){
                System.out.println("getLeavesTest failed");
                return;
            }
        }
        System.out.println("getLeavesTest passed");
    }

    public void interviewQuestionTests(){
        interviewTestEmpty();
        interviewTestExample();
        interviewTestSecondExample();
        interviewTestThirdExample();
    }

    public void interviewTestEmpty(){
       if(question.lastBox(new int[]{}) == 0){
           System.out.println("interviewTestEmpty passed");
           return;
       }
       System.out.println("interviewTestEmpty failed");
    }

    public void interviewTestExample(){
        if(question.lastBox(new int[]{3,2,1,4}) == 0){
            System.out.println("interviewTestExample passed");
            return;
        }
        System.out.println("interviewTestExample failed");
    }

    public void interviewTestSecondExample(){
        if(question.lastBox(new int[]{5,6,7,8,10,11}) == 1) {
            System.out.println("interviewTestSecondExample passed");
            return;
        }
        System.out.println("interviewTestSecondExample failed");
    }

    public void interviewTestThirdExample(){
        if(question.lastBox(new int[]{11,7}) == 4){
            System.out.println("interviewTestThirdExample passed");
            return;
        }
        System.out.println("interviewTestThirdExample failed");
    }
}
