/*
 * Max Heap Implementation in Java
 * --------------------------------
 * A Max Heap is a Complete Binary Tree where:
 * - Every parent node is >= its children
 * - Largest element is always at the root
 *
 * Array Representation:
 * parent(i) = (i - 1) / 2
 * left(i)   = 2 * i + 1
 * right(i)  = 2 * i + 2
 *
 * Time Complexities:
 * --------------------------------
 * Insert        -> O(log n)
 * Get Max       -> O(1)
 * Extract Max   -> O(log n)
 * Increase Key  -> O(log n)
 * Delete Key    -> O(log n)
 * Heapify       -> O(log n)
 * Build Heap    -> O(n)
 * Space         -> O(n)
 */

class MaxHeap {

    // Array to store heap elements
    private int[] heap;

    // Current number of elements
    private int size;

    // Maximum capacity
    private int capacity;

    // Constructor
    public MaxHeap(int capacity) {

        // Set heap capacity
        this.capacity = capacity;

        // Create array
        heap = new int[capacity];

        // Initially empty
        size = 0;
    }

    // Return parent index
    private int parent(int i) {
        return (i - 1) / 2;
    }

    // Return left child index
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    // Return right child index
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    /*
     * Insert new element
     * Time Complexity: O(log n)
     */
    public void insert(int value) {

        // Check overflow
        if (size == capacity) {
            System.out.println("Heap Overflow");
            return;
        }

        // Insert at end
        heap[size] = value;

        // Current inserted index
        int current = size;

        // Increase size
        size++;

        /*
         * Move upward while parent is smaller
         */
        while (current != 0 && heap[parent(current)] < heap[current]) {

            // Swap with parent
            swap(current, parent(current));

            // Move upward
            current = parent(current);
        }
    }

    /*
     * Return maximum element
     * Time Complexity: O(1)
     */
    public int getMax() {

        // If heap empty
        if (size <= 0) {
            return -1;
        }

        // Root has maximum value
        return heap[0];
    }

    /*
     * Remove and return max element
     * Time Complexity: O(log n)
     */
    public int extractMax() {

        // Heap empty
        if (size <= 0) {
            return -1;
        }

        // Only one element
        if (size == 1) {
            size--;
            return heap[0];
        }

        // Save root
        int root = heap[0];

        // Move last element to root
        heap[0] = heap[size - 1];

        // Reduce size
        size--;

        // Restore heap property
        maxHeapify(0);

        return root;
    }

    /*
     * Heapify subtree rooted at index i
     * Time Complexity: O(log n)
     */
    private void maxHeapify(int i) {

        // Assume current is largest
        int largest = i;

        // Left child index
        int left = leftChild(i);

        // Right child index
        int right = rightChild(i);

        // Compare left child
        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }

        // Compare right child
        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }

        // If largest changed
        if (largest != i) {

            // Swap current with largest child
            swap(i, largest);

            // Recursively heapify affected subtree
            maxHeapify(largest);
        }
    }

    /*
     * Increase value at index i
     * Time Complexity: O(log n)
     */
    public void increaseKey(int i, int newValue) {

        // Replace old value
        heap[i] = newValue;

        // Move upward while parent is smaller
        while (i != 0 && heap[parent(i)] < heap[i]) {

            // Swap with parent
            swap(i, parent(i));

            // Move upward
            i = parent(i);
        }
    }

    /*
     * Delete element at index i
     * Trick:
     * 1. Increase key to maximum value
     * 2. Extract max
     *
     * Time Complexity: O(log n)
     */
    public void deleteKey(int i) {

        // Make it largest
        increaseKey(i, Integer.MAX_VALUE);

        // Remove root
        extractMax();
    }

    /*
     * Swap two elements
     */
    private void swap(int i, int j) {

        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /*
     * Print heap
     */
    public void printHeap() {

        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }

        System.out.println();
    }

    /*
     * Main method
     */
    public static void main(String[] args) {

        // Create heap
        MaxHeap h = new MaxHeap(10);

        // Insert elements
        h.insert(20);
        h.insert(15);
        h.insert(30);
        h.insert(40);
        h.insert(10);

        // Print heap
        System.out.println("Heap Elements:");
        h.printHeap();

        // Get max
        System.out.println("Max Element: " + h.getMax());

        // Remove max
        System.out.println("Extract Max: " + h.extractMax());

        // Print after deletion
        h.printHeap();

        // Delete index 1
        h.deleteKey(1);

        // Final heap
        h.printHeap();
    }
}