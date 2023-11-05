import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DatasetGenerator {
    public static void main(String[] args) {
        generateDatasets();
    }

    public static void generateDatasets() {
        int[] exponents = {9, 13, 16}; // Eksponen untuk 2^9, 2^13, 2^16
        String datasetFolder = "Dataset"; // Nama folder untuk dataset
    
        File folder = new File(datasetFolder);
        if (!folder.exists()) {
            folder.mkdir(); // Membuat folder jika belum ada
        }
    
        String[] statuses = {"sorted", "random", "reversed"};
    
        for (int exponent : exponents) {
            int size = (int) Math.pow(2, exponent); // Menghitung ukuran dataset
            for (String status : statuses) {
                int[] dataset = generateDataset(size, status);
                saveDatasetToFile(dataset, size, status, datasetFolder);
            }
        }
    }
    

    public static void saveDatasetToFile(int[] dataset, int size, String status, String folderName) {
        String sizeString = getSizeString(size); // Mengonversi ukuran menjadi string
        String fileName = folderName + "/dataset_" + sizeString + "_" + status + ".txt";
    
        try (FileWriter writer = new FileWriter(fileName)) {
            for (int i = 0; i < dataset.length; i++) {
                writer.write(Integer.toString(dataset[i]));
                writer.write(System.lineSeparator()); // Baris baru setelah setiap angka
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static int[] generateDataset(int size, String status) {
        int[] dataset = new int[size];
        Random random = new Random();

        switch (status) {
            case "sorted":
                for (int i = 0; i < size; i++) {
                    dataset[i] = i;
                }
                break;
            case "random":
                for (int i = 0; i < size; i++) {
                    dataset[i] = random.nextInt();
                }
                break;
            case "reversed":
                for (int i = 0; i < size; i++) {
                    dataset[i] = size - i - 1;
                }
                break;
        }

        return dataset;
    }
}
