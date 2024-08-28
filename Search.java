import java.util.Scanner;

public class Search {
    public static void main(String... args) {
        int a[] = {1, 7, 12, 23, 31, 42, 45};
        int beg = 0, end = a.length - 1;
        int mid;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number:");
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
    }
}

