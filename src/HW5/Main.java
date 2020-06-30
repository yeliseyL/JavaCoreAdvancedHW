package HW5;

public class Main {
    static final int SIZE = 10000000;
    static final int HALF_SIZE = SIZE / 2;

    public static void main(String[] args) throws InterruptedException {
        arrayCalcTime();
        arrayCalcTimeMultithreaded();
    }

    public static void arrayCalcTime() {
        float[] arr = new float[SIZE];

        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + (float) i / 5) * Math.cos(0.2f +
                    (float) i / 5) * Math.cos(0.4f + (float) i / 2));
        }

        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    public static void arrayCalcTimeMultithreaded() throws InterruptedException {
        float[] arr = new float[SIZE];
        float[] halfArr1 = new float[HALF_SIZE];
        float[] halfArr2 = new float[HALF_SIZE];

        for (int i = 0; i < SIZE - 1; i++) {
            arr[i] = 1;
        }

        long start = System.currentTimeMillis();
        System.arraycopy(arr, 0, halfArr1, 0, HALF_SIZE);
        System.arraycopy(arr, HALF_SIZE, halfArr2, 0, HALF_SIZE);

        Thread halfArr1Thread = new Thread(() -> {
            for (int i = 0; i < HALF_SIZE; i++) {
                arr[i] = (float)(arr[i] * Math.sin(0.2f + (float) i / 5) * Math.cos(0.2f +
                        (float) i / 5) * Math.cos(0.4f + (float) i / 2));
            }
        });

        Thread halfArr2Thread = new Thread(() -> {
            for (int i = 0; i < HALF_SIZE; i++) {
                arr[i] = (float)(arr[i] * Math.sin(0.2f + (float) (i + HALF_SIZE) / 5) * Math.cos(0.2f +
                        (float) (i + HALF_SIZE) / 5) * Math.cos(0.4f + (float) (i + HALF_SIZE) / 2));
            }
        });

        halfArr1Thread.start();
        halfArr2Thread.start();

        halfArr1Thread.join();
        halfArr2Thread.join();

        System.arraycopy(halfArr1, 0, arr, 0, HALF_SIZE);
        System.arraycopy(halfArr2, 0, arr, HALF_SIZE, HALF_SIZE);

        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
