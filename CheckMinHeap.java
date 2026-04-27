/*
 * Check if an Array Represents a Min Heap
 * --------------------------------------
 * Condition for Min Heap:
 * Every parent node must be <= its children
 *
 * For array representation:
 * parent(i) = (i - 1) / 2
 * left child  = 2*i + 1
 * right child = 2*i + 2
 *
 * Time Complexity  : O(n)
 * Space Complexity : O(1)
 */

public class CheckMinHeap {

    /*
     * Function to check whether given array is Min Heap or not
     */
    public static boolean isMinHeap(int[] arr) {

        // Number of elements in array
        int n = arr.length;

        /*
         * Only need to check parent nodes
         * Last parent index = (n / 2) - 1
         */
        for (int i = 0; i <= (n / 2) - 1; i++) {

            // Left child index
            int left = 2 * i + 1;

            // Right child index
            int right = 2 * i + 2;

            // If left child exists and parent > left child
            if (left < n && arr[i] > arr[left]) {
                return false;
            }

            // If right child exists and parent > right child
            if (right < n && arr[i] > arr[right]) {
                return false;
            }
        }

        // If all parent nodes satisfy min heap property
        return true;
    }

    public static void main(String[] args) {

        // Example 1: Valid Min Heap
        int[] arr1 = {2, 4, 5, 10, 15, 20};

        // Example 2: Not a Min Heap
        int[] arr2 = {10, 4, 5, 20, 15};

        // Check first array
        if (isMinHeap(arr1)) {
            System.out.println("arr1 is a Min Heap");
        } else {
            System.out.println("arr1 is NOT a Min Heap");
        }

        // Check second array
        if (isMinHeap(arr2)) {
            System.out.println("arr2 is a Min Heap");
        } else {
            System.out.println("arr2 is NOT a Min Heap");
        }
    }
}