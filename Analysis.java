import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class Analysis {
    
    public static void main(String[] args) {
        int[] exponents = {9, 13, 16}; // Eksponen untuk 2^9, 2^13, 2^16
        String[] statuses = {"sorted", "random", "reversed"};
        String folderName = "Dataset"; // Nama folder yang berisi dataset
    
        for (int exponent : exponents) {
            int size = (int) Math.pow(2, exponent); // Menghitung ukuran dataset
            for (String status : statuses) {
                int[] dataset = readDatasetFromFile(size, status, folderName);
                int[] copy;
                
                DecimalFormat df = new DecimalFormat("0.0000");
    
                System.out.println("Analyzing " + getSizeString(size) + " elements, Status: " + status);
    
                // Randomized Shell Sort Analysis
                copy = dataset.clone();
                long startMemory = getMemoryUsage();
                long startTime = System.nanoTime();
                RandomizedShellSort.randomizedShellSort(copy);
                long endTime = System.nanoTime();
                long endMemory = getMemoryUsage();
                System.out.println("Randomized Shell Sort - Execution Time: " + df.format((endTime - startTime) / 1_000_000.0) + " ms");
                System.out.println("Randomized Shell Sort - Memory Used: " + (endMemory - startMemory) + " bytes");
    
                // Max Heap Sort Analysis
                copy = dataset.clone();
                startMemory = getMemoryUsage();
                startTime = System.nanoTime();
                MaxHeapSort.maxHeapSort(copy);
                endTime = System.nanoTime();
                endMemory = getMemoryUsage();
                System.out.println("Max Heap Sort - Execution Time: " + df.format((endTime - startTime) / 1_000_000.0) + " ms");
                System.out.println("Max Heap Sort - Memory Used: " + (endMemory - startMemory) + " bytes");

                System.out.println();
            }
        }
    }   
 

    public static int[] readDatasetFromFile(int size, String status, String folderName) {
        String fileName = folderName + "/dataset_" + getSizeString(size) + "_" + status + ".txt";
        try {
            String content = new String(Files.readAllBytes(Paths.get(fileName)));
            String[] tokens = content.split(System.lineSeparator());
            int[] dataset = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                dataset[i] = Integer.parseInt(tokens[i]);
            }
            return dataset;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long getMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    public static String getSizeString(int size) {
        if (size == 512) {
            return "small";
        } else if (size == 8192) {
            return "medium";
        } else if (size == 65536) {
            return "large";
        } else {
            return "unknown";
        }
    }
}
