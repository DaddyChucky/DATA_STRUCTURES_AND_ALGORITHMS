import java.util.*;

public class Interview {
    private static final int[][] DIRECTIONS = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    private static final int[] DIRECTIONS_GRID = {1, 2, 3, 4};

    public int minCost(Cell[][] grid) {
        /* Ne pas modifier ce code */
        int m = grid.length, n = grid[0].length;
        int maxPosX = m - 1,maxPosY = n - 1 ;

        Cell[][] costs = new Cell[m][n];
        int yIndex = 0;
        for (Cell[] c : costs){
            for(int i = 0; i < c.length; i++){
                c[i] = new Cell(i, yIndex, Integer.MAX_VALUE);
            }
            ++yIndex;
        }

        costs[0][0].cost = 0;
        final int N_DIRECTIONS = 4;

        PriorityQueue<Cell> heap = new PriorityQueue<>();
        heap.offer(new Cell(0,0,0));
        /* Ne pas modifier ce code */

        while (!heap.isEmpty()) {
            Cell curr = heap.poll();
            int x = curr.xPos, y = curr.yPos, cost = curr.cost;

            if (curr.xPos == maxPosX && curr.yPos == maxPosY) return cost;

            for (int i = 0; i < N_DIRECTIONS; i++) {
                int[] dir = DIRECTIONS[i];
                int newX = x + dir[1], newY = y + dir[0];
                if (newX < 0 || newY < 0 || newX > maxPosX || newY > maxPosY) continue;

                int newCost = DIRECTIONS_GRID[i] == grid[x][y].cost ? 0 : 1;
                if (costs[newY][newX].cost > newCost) {
                    costs[newY][newX].cost = newCost + costs[y][x].cost;
                    heap.add(costs[newY][newX]);
                }
            }
        }
        return -1;
    }
}
