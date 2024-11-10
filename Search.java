import java.util.Arrays;
import java.util.Scanner;

public class search {
    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Get the size of the array from the user
        System.out.println("Enter the size of the array:");
        int size = sc.nextInt();
        int[] a = new int[size];

        // Step 2: Input array elements from the user
        System.out.println("Enter " + size + " elements:");
        for (int i = 0; i < size; i++) {
            a[i] = sc.nextInt();
        }

        // Sort the array before performing binary search
        Arrays.sort(a);

        // Display the sorted array
        System.out.print("The sorted array is: ");
        for (int num : a) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Step 3: Binary search
        int beg = 0, end = a.length - 1;
        int mid;

        System.out.println("Enter number to search:");
        int num = sc.nextInt();

        boolean found = false;
        while (beg <= end) {
            mid = (beg + end) / 2;

            if (a[mid] == num) {
                System.out.println("Element " + a[mid] + " found at location " + mid);
                found = true;
                break;
            } else if (a[mid] < num) {
                beg = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        if (!found) {
            System.out.println("Element is not in array");
        }

        sc.close();
    }
}
