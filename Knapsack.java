import java.util.*;

public class Knapsack {
    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of items:");
        int n = sc.nextInt();

        int[] weights = new int[n];
        int[] profits = new int[n];
        float[] ratios = new float[n];

        System.out.println("Enter weights:");
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
        }

        System.out.println("Enter profits:");
        for (int i = 0; i < n; i++) {
            profits[i] = sc.nextInt();
        }

        
        for (int i = 0; i < n; i++) {
            ratios[i] = (float) profits[i] / weights[i];
        }

        System.out.println("Enter the large volume of Knapsack:");
        int maxQty = sc.nextInt();

      
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(weights[i], profits[i], ratios[i]);
        }

  
        Arrays.sort(items, (a, b) -> Float.compare(b.ratio, a.ratio));

        float totalProfit = 0;
        int remainingCapacity = maxQty;

      
        for (Item item : items) {
            if (remainingCapacity == 0) break;

            int qtyToTake = Math.min(item.weight, remainingCapacity);
            totalProfit += qtyToTake * item.ratio;
            remainingCapacity -= qtyToTake;
        }

        System.out.println("Profit is: " + totalProfit);
        sc.close();
    }


    static class Item {
        int weight;
        int profit;
        float ratio;

        Item(int weight, int profit, float ratio) {
            this.weight = weight;
            this.profit = profit;
            this.ratio = ratio;
        }
    }
}
