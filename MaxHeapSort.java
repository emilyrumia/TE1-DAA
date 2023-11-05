public class MaxHeapSort {
    public static void maxHeapSort(int[] arr) {
        int n = arr.length;

        // Step 1: Bangun Max Heap
        buildMaxHeap(arr);

        // Step 2: Lakukan pengurutan
        for (int i = n - 1; i > 0; i--) {
            // Tukar elemen maksimum (pada akar) dengan elemen terakhir
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Panggil heapify untuk memulihkan sifat Max Heap pada array yang tersisa
            heapify(arr, i, 0);
        }
    }

    public static void buildMaxHeap(int[] arr) {
        int n = arr.length;

        // Mulai dari elemen terakhir yang bukan daun (n/2 - 1) ke elemen pertama
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    public static void heapify(int[] arr, int n, int rootIndex) {
        int largest = rootIndex;
        int leftChild = 2 * rootIndex + 1;
        int rightChild = 2 * rootIndex + 2;

        // Cari indeks yang memiliki nilai lebih besar antara root, left child, dan right child
        if (leftChild < n && arr[leftChild] > arr[largest]) {
            largest = leftChild;
        }

        if (rightChild < n && arr[rightChild] > arr[largest]) {
            largest = rightChild;
        }

        // Jika indeks terbesar bukan root, tukar root dengan indeks terbesar
        if (largest != rootIndex) {
            int swap = arr[rootIndex];
            arr[rootIndex] = arr[largest];
            arr[largest] = swap;

            // Rekursif memanggil heapify pada subtree yang terpengaruh
            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        int n = arr.length;

        maxHeapSort(arr);

        System.out.println("Array yang telah diurutkan: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
