package month1.projects;


/**
 * Create a counter incremented by multiple threads (demonstrate race condition).
 *
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class CounterSynchronised {
    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();
        Thread task1 = new Thread(new Task(counter), "Task 1");
        Thread task2 = new Thread(new Task(counter), "Task 2");
        task1.start();
        task2.start();

        task1.join();
        task2.join();

        System.out.println("Count = " + counter.getVal());
    }

    private static class Counter {
        private int val;

        synchronized void increment() {
            val++;
        }

        public int getVal() {
            return val;
        }
    }

    private record Task(Counter counter) implements Runnable {

        @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    counter.increment();
                }
            }
        }
}

