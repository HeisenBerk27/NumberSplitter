package numberSplitter;
import java.util.ArrayList;

class NumberCounter implements Runnable {
    private ArrayList<Integer> numbers;
    private ArrayList<Integer> evenNumbers;
    private ArrayList<Integer> oddNumbers;

    public NumberCounter(ArrayList<Integer> numbers, ArrayList<Integer> evenNumbers, ArrayList<Integer> oddNumbers) {
        this.numbers = numbers;
        this.evenNumbers = evenNumbers;
        this.oddNumbers = oddNumbers;
    }

    @Override
    public void run() {
        for (Integer number : numbers) {
            if (number % 2 == 0) {
                synchronized (evenNumbers) {
                    evenNumbers.add(number);
                }
            } else {
                synchronized (oddNumbers) {
                    oddNumbers.add(number);
                }
            }
        }
    }
}
