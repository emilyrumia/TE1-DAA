public class RandomizedShellSort {
    public static final int C = 4; // Jumlah pengulangan perbandingan antar region

    public static void exchange(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void compareExchange(int[] a, int i, int j) {

        if (((i < j) && (a[i] > a[j])) || ((i > j) && (a[i] < a[j])))
            exchange(a, i, j);
    }

    public static void permuteRandom(int a[], MyRandom rand) {
        for (int i = 0; i < a.length; i++) {
            // Menggunakan algoritma permutasi acak Knuth
            exchange(a, i, rand.nextInt(a.length - i) + i);
        }
    }

    public static void compareRegions(int[] a, int s, int t, int offset, MyRandom rand) {
        int mate[] = new int[offset]; // Array indeks offset

        for (int count = 0; count < C; count++) {
            // Melakukan C perbandingan antar region
            for (int i = 0; i < offset; i++) {
                mate[i] = i;
            }
            
            // Mengacaukan urutan indeks
            permuteRandom(mate, rand); // Comment this out to get a deterministic Shellsort
            
            for (int i = 0; i < offset; i++) {
                compareExchange(a, s + i, t + mate[i]);
            }
        }

        // System.out.println("A: " + java.util.Arrays.toString(a));

    }

    public static void randomizedShellSort(int[] a) {
        int n = a.length; // Diasumsikan bahwa n adalah bilangan pangkat dua
        MyRandom rand = new MyRandom(); // Generator angka acak 
    
        for (int offset = n / 2; offset > 0; offset /= 2) {

            // System.out.println("Offset: " + offset);
            
            // System.out.println("Perbandingan naik");
            for (int i = 0; i < n - offset; i += offset) {
                // System.out.println("i: " + i);
                // System.out.println("i + offset: " + (i + offset));
                compareRegions(a, i, i + offset, offset, rand); // Perbandingan naik
                // System.out.println();
            }
            
            // System.out.println("Perbandingan turun");
            for (int i = n - offset; i >= offset; i -= offset) {
                // System.out.println("i - offset: " + (i - offset));
                // System.out.println("i: " + i);
                compareRegions(a, i - offset, i, offset, rand); // Perbandingan turun
                // System.out.println(" ");
            }
            
            // System.out.println("Perbandingan 3 offset");
            // System.out.println("n + 3 * offset: " + (n + 3 * offset));
            for (int i = 0; i < n - 3 * offset; i += offset) {
                // System.out.println("i: " + i);
                // System.out.println("i + 3 * offset: " + (i + 3 * offset));
                compareRegions(a, i, i + 3 * offset, offset, rand); // Perbandingan 3 offset
                // System.out.println(" ");
            }
            
            // System.out.println("Perbandingan 2 offset");
            // System.out.println("n + 3 * offset: " + (n + 2 * offset));
            for (int i = 0; i < n - 2 * offset; i += offset) {
                // System.out.println("i: " + i);
                // System.out.println("i + 2 * offset: " + (i + 2 * offset));
                compareRegions(a, i, i + 2 * offset, offset, rand); // Perbandingan 2 offset
                // System.out.println(" ");
            }
            
            // System.out.println("Perbandingan antar region ganjil-genap");
            for (int i = 0; i < n; i += 2 * offset) {
                // System.out.println("i: " + i);
                // System.out.println("i: " +  (i + offset));
                compareRegions(a, i, i + offset, offset, rand); // Perbandingan antar region ganjil-genap
                // System.out.println(" ");
            }
            
            // System.out.println("Perbandingan antar region genap-ganjil");
            for (int i = offset; i < n - offset; i += 2 * offset) {
                // System.out.println("i: " + i);
                 // System.out.println("i + offset: " + (i + offset));
                compareRegions(a, i, i + offset, offset, rand); // Perbandingan antar region genap-ganjil
                // System.out.println(" ");
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5};
        System.out.println("Array awal: " + java.util.Arrays.toString(arr));
        int n = arr.length;

        randomizedShellSort(arr);

        System.out.println("Array yang sudah diurutkan:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
