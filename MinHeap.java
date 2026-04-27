/*
 * Min Heap Implementation in Java
 * --------------------------------
 * A Min Heap is a Complete Binary Tree where:
 * - Every parent node is <= its children
 * - Smallest element is always at the root
 *
 * We store heap in an array:
 * parent(i) = (i - 1) / 2
 * left(i)   = 2 * i + 1
 * right(i)  = 2 * i + 2
 *
 * Time Complexities:
 * --------------------------------
 * Insert        -> O(log n)
 * Get Min       -> O(1)
 * Extract Min   -> O(log n)
 * Decrease Key  -> O(log n)
 * Heapify       -> O(log n)
 * Build Heap    -> O(n)
 * Space         -> O(n)
 */

class MinHeap {

    // Array to store heap elements
    private int[] heap;

    // Current number of elements in heap
    private int size;

    // Maximum capacity of heap
    private int capacity;

    // Constructor to initialize heap
    public MinHeap(int capacity) {

        // Set maximum capacity
        this.capacity = capacity;

        // Create array of given capacity
        heap = new int[capacity];

        // Initially heap is empty
        size = 0;
    }

    // Returns index of parent node
    private int parent(int i) {
        return (i - 1) / 2;
    }

    // Returns index of left child
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    // Returns index of right child
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    /*
     * Insert a new element
     * Time Complexity: O(log n)
     */
    public void insert(int value) {

        // Check if heap is full
        if (size == capacity) {
            System.out.println("Heap Overflow");
            return;
        }

        // Put new value at end
        heap[size] = value;

        // Current index of inserted element
        int current = size;

        // Increase heap size
        size++;

        /*
         * Move upward until heap property is restored
         * If parent > child, swap them
         */
        while (current != 0 && heap[parent(current)] > heap[current]) {

            // Swap parent and current
            swap(current, parent(current));

            // Move current index upward
            current = parent(current);
        }
    }

    /*
     * Get minimum element
     * Time Complexity: O(1)
     */
    public int getMin() {

        // If heap empty
        if (size <= 0) {
            return -1;
        }

        // Root contains minimum value
        return heap[0];
    }

    /*
     * Remove and return minimum element
     * Time Complexity: O(log n)
     */
    public int extractMin() {

        // If heap empty
        if (size <= 0) {
            return -1;
        }

        // If only one element exists
        if (size == 1) {
            size--;
            return heap[0];
        }

        // Store root value (minimum)
        int root = heap[0];

        // Move last element to root
        heap[0] = heap[size - 1];

        // Reduce heap size
        size--;

        // Restore heap property downward
        minHeapify(0);

        // Return removed minimum
        return root;
    }

    /*
     * Heapify subtree rooted at index i
     * Time Complexity: O(log n)
     */
    private void minHeapify(int i) {

        // Assume current node is smallest
        int smallest = i;

        // Left child index
        int left = leftChild(i);

        // Right child index
        int right = rightChild(i);

        // Compare left child with current smallest
        if (left < size && heap[left] < heap[smallest]) {
            smallest = left;
        }

        // Compare right child with current smallest
        if (right < size && heap[right] < heap[smallest]) {
            smallest = right;
        }

        // If smallest is not current node
        if (smallest != i) {

            // Swap current with smallest child
            swap(i, smallest);

            // Recursively heapify affected subtree
            minHeapify(smallest);
        }
    }

    /*
     * Decrease value at index i
     * Time Complexity: O(log n)
     */
    public void decreaseKey(int i, int newValue) {

        // Replace old value
        heap[i] = newValue;

        // Move upward while parent is greater
        while (i != 0 && heap[parent(i)] > heap[i]) {

            // Swap with parent
            swap(i, parent(i));

            // Move index upward
            i = parent(i);
        }
    }

    /*
     * Delete element at index i
     * Trick:
     * 1. Decrease key to minimum possible
     * 2. Extract min
     *
     * Time Complexity: O(log n)
     */
    public void deleteKey(int i) {

        // Make this node smallest
        decreaseKey(i, Integer.MIN_VALUE);

        // Remove it
        extractMin();
    }

    /*
     * Swap two array elements
     */
    private void swap(int i, int j) {

        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /*
     * Print heap array
     */
    public void printHeap() {

        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }

        System.out.println();
    }

    /*
     * Main method for testing
     */
    public static void main(String[] args) {

        // Create heap with capacity 10
        MinHeap h = new MinHeap(10);

        // Insert elements
        h.insert(20);
        h.insert(15);
        h.insert(30);
        h.insert(5);
        h.insert(10);

        // Print heap
        System.out.println("Heap Elements:");
        h.printHeap();

        // Get minimum
        System.out.println("Min Element: " + h.getMin());

        // Remove minimum
        System.out.println("Extract Min: " + h.extractMin());

        // Print heap after deletion
        h.printHeap();

        // Delete index 1
        h.deleteKey(1);

        // Print final heap
        h.printHeap();
    }
}