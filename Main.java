public class Main {
    static final int size = 1000000;
    static final int h = size / 2;

    public static void main(String[] args) {
        createMassive();
        createMassive_2();

    }

    public static void createMassive() {
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

        }
        System.out.println("Время работы метода " + (System.currentTimeMillis() - a) + " мс");
    }

    public static void createMassive_2() {
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        System.out.println("Время деления массива " + (System.currentTimeMillis() - a) + " мс");

        long t = System.currentTimeMillis();
        Thread counting = new Thread(() -> {

            for (int i = 0; i < a1.length; i++) {
                a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.out.println("Время обработки первого массива " + (System.currentTimeMillis() - t) + " мс");
        });

        Thread counting_2 = new Thread(() -> {

            for (int i = 0; i < a2.length; i++) {
                a2[i] = (float) (a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.out.println("Время обработки второго массива " + (System.currentTimeMillis() - t) + " мс");
        });

        counting.start();
        counting_2.start();

        try {
            counting.join();
            counting_2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long b = System.currentTimeMillis();
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.println("Время объеденения массивов " + (System.currentTimeMillis() - b) + " мс");


    }
}
