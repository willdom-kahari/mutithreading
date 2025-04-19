package month1.projects;


/**
 * Create a counter incremented by multiple threads (demonstrate race condition).
 *
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class CounterRaceCondition {
    public static void main(String[] args) {
        final Counter counter = new Counter();
        Thread task1 = new Thread(new Task(counter), "Task 1");
        Thread task2 = new Thread(new Task(counter), "Task 2");
        task1.start();
        task2.start();

    }

    private static class Counter {
        private int val;

        void increment() {
            val++;
            System.out.println(Thread.currentThread().getName() + ": counter value is " + val);
        }
    }

    private record Task(Counter counter) implements Runnable {

        @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    counter.increment();
                    try {
                        Thread.sleep(100L); // simulate execution
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
}

