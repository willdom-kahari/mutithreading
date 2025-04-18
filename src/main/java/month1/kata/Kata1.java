package month1.kata;



/**
 * Create 10 threads, each print 1–100 sequentially (observe interleaving).
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class Kata1 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(new NumberPrinter());
            t1.start();
        }
    }

    private static class NumberPrinter implements Runnable{


        @Override
        public void run() {
            for (int i = 1; i < 101; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
            }
        }
    }
}


