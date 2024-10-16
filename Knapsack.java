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

class Knapsack {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();
        
        Item[] items = new Item[n];
        int[] profits = new int[n];
        int[] weights = new int[n];

        System.out.println("Enter profits and weights for each item:");
        for (int i = 0; i < n; i++) {
            System.out.print("Item " + (i + 1) + " Profit: ");
            profits[i] = scanner.nextInt();
            System.out.print("Item " + (i + 1) + " Weight: ");
            weights[i] = scanner.nextInt();
            items[i] = new Item(weights[i], profits[i]);
        }
        
        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = scanner.nextInt();
        
        double maxValue = getMaxValue(items, capacity, n);
        System.out.println("Maximum value in Knapsack = " + maxValue);
    }
    
    public static double getMaxValue(Item[] items, int capacity, int n) {
        // Sort items by value/weight ratio in descending order
        Arrays.sort(items, Comparator.comparingDouble((Item item) -> (double)item.value / item.weight).reversed());


        return branchAndBound(items, capacity, n);
    }

    private static double branchAndBound(Item[] items, int capacity, int n) {
        int maxWeight = 0;
        double maxValue = 0;
        boolean[] included = new boolean[n];

        // Create a queue to hold the nodes of the state space tree
        Node[] queue = new Node[n * n];
        int front = 0, rear = -1;

        // Initial node
        Node root = new Node(0, 0, 0.0, new boolean[n]);
        enqueue(queue, rear++, root);

        while (front <= rear) {
            Node currentNode = dequeue(queue, front++);

            // If we have reached the end of the items
            if (currentNode.level == n) {
                continue;
            }

            // Consider the next item
            Item nextItem = items[currentNode.level];

            // Create the node for including the item
            Node withItem = new Node(currentNode.level + 1,
                                     currentNode.weight + nextItem.weight,
                                     currentNode.value + nextItem.value,
                                     Arrays.copyOf(currentNode.included, n));
            withItem.included[currentNode.level] = true;

            // Check if the item can be included
            if (withItem.weight <= capacity) {
                if (withItem.value > maxValue) {
                    maxValue = withItem.value;
                    maxWeight = withItem.weight;
                    included = Arrays.copyOf(withItem.included, n);
                }
                enqueue(queue, rear++, withItem);
            }

            // Create the node for excluding the item
            Node withoutItem = new Node(currentNode.level + 1, currentNode.weight, currentNode.value, Arrays.copyOf(currentNode.included, n));
            enqueue(queue, rear++, withoutItem);
        }

        // Output the result
        System.out.println("Selected items for maximum profit:");
        for (int i = 0; i < n; i++) {
            if (included[i]) {
                System.out.println("Item " + (i + 1) + " - Profit: " + items[i].value + ", Weight: " + items[i].weight);
            }
        }
        System.out.println("Total weight: " + maxWeight);
        System.out.println("Total profit: " + maxValue);
        
        return maxValue;
    }

    private static void enqueue(Node[] queue, int rear, Node node) {
        queue[rear] = node;
    }

    private static Node dequeue(Node[] queue, int front) {
        return queue[front];
    }

    static class Node {
        int level;
        int weight;
        double value;
        boolean[] included;

        Node(int level, int weight, double value, boolean[] included) {
            this.level = level;
            this.weight = weight;
            this.value = value;
            this.included = included;
        }
    }
}

