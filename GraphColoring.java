import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GraphColoring {
    private int V; 
    private int[][] graph; 

    public GraphColoring(int v) {
        V = v;
        graph = new int[V][V];
    }

    public void addEdge(int u, int v) {
        graph[u][v] = 1;
        graph[v][u] = 1; 
    }

    private boolean isSafe(int v, int[] color, int c) {
        for (int i = 0; i < V; i++) {
            if (graph[v][i] == 1 && color[i] == c) {
                return false;
            }
        }
        return true;
    }

    private void graphColorUtil(int m, int[] color, int v, List<String> solutions, char[] colorChars) {

        if (v == V) {
            StringBuilder sb = new StringBuilder();
            for (int c : color) {
                sb.append(colorChars[c]).append(" ");
            }
            solutions.add(sb.toString().trim()); 
            return;
        }

        for (int c = 0; c < m; c++) {
            if (isSafe(v, color, c)) {
                color[v] = c; 

                graphColorUtil(m, color, v + 1, solutions, colorChars);

                color[v] = -1; 
            }
        }
    }

    public List<String> graphColoring(int m, char[] colorChars) {
        int[] color = new int[V]; 
        Arrays.fill(color, -1); 
        List<String> solutions = new ArrayList<>();

        graphColorUtil(m, color, 0, solutions, colorChars);
        return solutions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int vertices = scanner.nextInt();
        GraphColoring graph = new GraphColoring(vertices);

        System.out.print("Enter number of edges: ");
        int edges = scanner.nextInt();

        System.out.println("Enter edges (u v) where u and v are vertex indices (0-based):");
        for (int i = 0; i < edges; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph.addEdge(u, v);
        }

        System.out.print("Enter number of colors: ");
        int m = scanner.nextInt();
        char[] colorChars = new char[m];

        System.out.println("Enter the first letter of each color:");
        for (int i = 0; i < m; i++) {
            colorChars[i] = scanner.next().charAt(0);
        }

        List<String> solutions = graph.graphColoring(m, colorChars);

        if (solutions.isEmpty()) {
            System.out.println("No solution exists for the given graph with " + m + " colors.");
        } else {
            System.out.println("Possible color assignments:");
            for (String solution : solutions) {
                System.out.println(solution);
            }
        }

        scanner.close();
    }
}

