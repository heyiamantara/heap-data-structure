/*
 * Convert Min Heap to Max Heap
 * ---------------------------
 * Given an array that represents a Min Heap,
 * convert it into a Max Heap in-place.
 *
 * Idea:
 * Build Max Heap using bottom-up heapify.
 *
 * Example:
 * Input  : [1, 3, 5, 7, 9, 2]
 * Output : [9, 7, 5, 3, 1, 2]
 *
 * Time Complexity  : O(n)
 * Space Complexity : O(1)
 */

public class MinHeapToMaxHeap {

    /*
     * Function to convert Min Heap to Max Heap
     */
    public static void convertToMaxHeap(int[] arr) {

        // Number of elements
        int n = arr.length;

        /*
         * Start from last non-leaf node
         * Last parent = (n / 2) - 1
         * Move upward to root
         */
        for (int i = (n / 2) - 1; i >= 0; i--) {

            // Restore max heap property
            maxHeapify(arr, n, i);
        }
    }

    /*
     * Heapify subtree rooted at index i
     * Maintains Max Heap property
     */
    public static void maxHeapify(int[] arr, int n, int i) {

        // Assume current node is largest
        int largest = i;

        // Left child index
        int left = 2 * i + 1;

        // Right child index
        int right = 2 * i + 2;

        // Compare with left child
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // Compare with right child
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {

            // Swap values
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            // Recursively heapify affected subtree
            maxHeapify(arr, n, largest);
        }
    }

    /*
     * Print array
     */
    public static void printArray(int[] arr) {

        for (int num : arr) {
            System.out.print(num + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {

        // Given Min Heap array
        int[] arr = {1, 3, 5, 7, 9, 2};

        System.out.println("Min Heap:");
        printArray(arr);

        // Convert to Max Heap
        convertToMaxHeap(arr);

        System.out.println("Max Heap:");
        printArray(arr);
    }
}