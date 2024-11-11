import java.util.Arrays;

public class TSP {
    private static final int INF = Integer.MAX_VALUE;
    private int n; // Number of cities
    private int[][] cost; // Cost matrix
    private boolean[] visited; // To keep track of visited cities
    private int minCost = INF; // To store the minimum tour cost
    private int[] bestPath; // To store the best path

    public TSP(int[][] cost) {
        this.n = cost.length;
        this.cost = cost;
        this.visited = new boolean[n];
        this.bestPath = new int[n + 1]; // +1 to return to the start
    }

    // Function to solve TSP using Branch and Bound
    public void tsp(int city, int count, int currentCost, int startCity, int[] path) {
        // Add current city to path
        path[count - 1] = city;

        // If all cities have been visited, return to start city
        if (count == n && cost[city][startCity] > 0) {
            int totalCost = currentCost + cost[city][startCity];
            if (totalCost < minCost) {
                minCost = totalCost;
                System.arraycopy(path, 0, bestPath, 0, n);
                bestPath[n] = startCity; // Return to start
            }
            return;
        }

        // Try visiting each city
        for (int nextCity = 0; nextCity < n; nextCity++) {
            if (!visited[nextCity] && cost[city][nextCity] > 0) {
                visited[nextCity] = true;
                tsp(nextCity, count + 1, currentCost + cost[city][nextCity], startCity, path);
                visited[nextCity] = false; // Backtrack
            }
        }
    }

    public void solveTSP(int startCity) {
        Arrays.fill(visited, false);
        visited[startCity] = true;
        int[] path = new int[n];
        tsp(startCity, 1, 0, startCity, path);

        // Output the result
        if (minCost < INF) {
            System.out.print("Minimum tour cost: " + minCost + "\nPath: ");
            for (int i = 0; i <= n; i++) {
                System.out.print(bestPath[i] + (i < n ? " -> " : ""));
            }
        } else {
            System.out.println("No solution found.");
        }
    }

    public static void main(String[] args) {
        int[][] costMatrix = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };

        TSP tsp = new TSP(costMatrix);
        tsp.solveTSP(0); // Start from city 0
    }
}
