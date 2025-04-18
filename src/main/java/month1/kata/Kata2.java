package month1.kata;


/**
 * Make Thread-2 wait for Thread-1 to finish using join()
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class Kata2 {
    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(new NumberPrinter(), "First thread");
        Thread second = new Thread(new NumberPrinter(), "Second thread");
        first.start();
        first.join();
        second.start();
    }

    private static class NumberPrinter implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + ": counter = " + (i + 1));
            }
        }
    }
}


