import java.util.Random;

public class MyRandom {
    private Random random;

    public MyRandom() {
        // Inisialisasi generator angka acak
        random = new Random();
    }

    public int nextInt(int bound) {
        // Menghasilkan bilangan bulat acak antara 0 (inklusif) dan bound (eksklusif)
        return random.nextInt(bound);
    }
}
