import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Item {
    int weight;
    int value;

    Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

public class FractionalKnapsack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        Item[] items = new Item[n];
        System.out.println("Enter profits and weights for each item:");
        for (int i = 0; i < n; i++) {
            System.out.print("Item " + (i + 1) + " Profit: ");
            int profit = scanner.nextInt();
            System.out.print("Item " + (i + 1) + " Weight: ");
            int weight = scanner.nextInt();
            items[i] = new Item(weight, profit);
        }

        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = scanner.nextInt();

        double maxValue = getMaxValue(items, capacity);
        System.out.println("Maximum value in Knapsack = " + maxValue);

        scanner.close();
    }

    public static double getMaxValue(Item[] items, int capacity) {
        // Sort items by value-to-weight ratio in descending order
        Arrays.sort(items, Comparator.comparingDouble((Item item) -> (double) item.value / item.weight).reversed());

        double maxValue = 0;

        for (Item item : items) {
            if (capacity - item.weight >= 0) {
                // Take the whole item
                capacity -= item.weight;
                maxValue += item.value;
            } else {
                // Take the fraction of the remaining capacity
                maxValue += (double) item.value * capacity / item.weight;
                break;
            }
        }

        return maxValue;
    }
}

