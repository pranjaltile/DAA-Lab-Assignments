import java.util.Arrays;

public class obstt {

    static class OBSTResult {
        int[][] cost;
        int[][] root;

        OBSTResult(int n) {
            cost = new int[n + 1][n + 1];
            root = new int[n + 1][n + 1];
        }
    }

    public static OBSTResult optimalBST(int[] keys, int[] freq) {
        int n = keys.length;
        OBSTResult result = new OBSTResult(n);

     
        for (int i = 0; i < n; i++) {
            result.cost[i][i] = freq[i];
            result.root[i][i] = i; 
        }

        
        for (int len = 2; len <= n; len++) { 
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                result.cost[i][j] = Integer.MAX_VALUE;

               
                for (int r = i; r <= j; r++) {
                  
                    int c = (r > i ? result.cost[i][r - 1] : 0) +
                            (r < j ? result.cost[r + 1][j] : 0) +
                            sum(freq, i, j);
                    
                 
                    if (c < result.cost[i][j]) {
                        result.cost[i][j] = c;
                        result.root[i][j] = r;
                    }
                }
            }
        }

        return result;
    }

    private static int sum(int[] freq, int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += freq[k];
        }
        return sum;
    }

    public static void printOptimalBST(OBSTResult result, int[] keys, int i, int j) {
        if (i > j) return;
        int r = result.root[i][j];
        System.out.println("Key: " + keys[r] + " is the root of keys " + keys[i] + " to " + keys[j]);
        printOptimalBST(result, keys, i, r - 1); // left subtree
        printOptimalBST(result, keys, r + 1, j); // right subtree
    }

    public static void main(String[] args) {
        int[] keys = {10, 20, 30};
        int[] freq = {3, 1, 2};

        OBSTResult result = optimalBST(keys, freq);
        System.out.println("Cost of Optimal BST: " + result.cost[0][keys.length - 1]);
        printOptimalBST(result, keys, 0, keys.length - 1);
    }
}

