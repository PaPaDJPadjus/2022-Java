package types;

public class Performance2 {

    public static void main(String[] args) {

        double start = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < 1e9; i++) {
            a += 0;
        }

        System.out.println((System.currentTimeMillis() - start) / 1000);
    }

}
