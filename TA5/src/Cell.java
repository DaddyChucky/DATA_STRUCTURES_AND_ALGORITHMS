public class Cell implements Comparable<Cell>{
    public int xPos;
    public int yPos;
    public int cost;

    Cell(int xPos, int yPos,int cost){
        this.xPos = xPos;
        this.yPos = yPos;
        this.cost = cost;
    }

    @Override
    public int compareTo(Cell o) {
        return Integer.compare(this.cost,o.cost);
    }
}
