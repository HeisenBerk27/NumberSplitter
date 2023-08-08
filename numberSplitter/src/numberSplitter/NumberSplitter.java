package numberSplitter;

import java.util.ArrayList;

public class NumberSplitter {
    public static void main(String[] args) {
        ArrayList<Integer> allNumbers = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            allNumbers.add(i);
        }

        ArrayList<Integer> evenNumbers = new ArrayList<>();
        ArrayList<Integer> oddNumbers = new ArrayList<>();

        int chunkSize = allNumbers.size() / 4;
        ArrayList<Integer>[] chunks = new ArrayList[4];
        Thread[] threads = new Thread[4];

        for (int i = 0; i < 4; i++) {
            int start = i * chunkSize;
            int end = (i == 3) ? allNumbers.size() : (i + 1) * chunkSize;
            chunks[i] = new ArrayList<>(allNumbers.subList(start, end));

            threads[i] = new Thread(new NumberCounter(chunks[i], evenNumbers, oddNumbers));
            threads[i].start();
        }

        try {
            for (int i = 0; i < 4; i++) {
                threads[i].join(); // Wait for all threads to finish
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Even Numbers: " + evenNumbers);
        System.out.println("Odd Numbers: " + oddNumbers);
    }
}
