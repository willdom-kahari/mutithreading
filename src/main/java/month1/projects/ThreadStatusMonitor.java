package month1.projects;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class ThreadStatusMonitor {
    private static final Object sharedLock = new Object();
    private static final List<Thread> monitoredThreads = new ArrayList<>();


    public static void main(String[] args) {
        // Create and start 5 threads with different behaviours
        for (int i = 1; i <= 5; i++) {
            Thread thread = new Thread(new WorkerThread(i), "Worker-" + i);
            monitoredThreads.add(thread);
        }

        // Start a monitoring thread
        Thread monitorThread = new Thread(new MonitorTask(), "Monitor");
        monitorThread.setDaemon(true);
        monitorThread.start();

        // Start all worker threads
        for (Thread thread : monitoredThreads) {
            thread.start();
        }

        // Let the program run for a while
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nMain thread is shutting down...");

    }

    private record WorkerThread(int id) implements Runnable {

        @Override
            public void run() {
                try {
                    switch (id) {
                        case 1 -> {
                            System.out.printf("[%s] Doing quick work and terminating%n", Thread.currentThread().getName());
                            TimeUnit.MILLISECONDS.sleep(200);
                        }
                        case 2 -> {
                            System.out.printf("[%s] Going to wait for a long time%n", Thread.currentThread().getName());
                            synchronized (this) {
                                wait(5000);
                            }
                        }
                        case 3 -> {
                            // Thread that does work in intervals
                            for (int i = 0; i < 3; i++) {
                                System.out.printf("[%s] Doing work iteration %d%n",
                                        Thread.currentThread().getName(), i);
                                TimeUnit.SECONDS.sleep(1);
                            }
                        }
                        case 4 -> {
                            // Thread that competes for a synchronized block
                            System.out.printf("[%s] Trying to acquire shared lock%n",
                                    Thread.currentThread().getName());
                            synchronized (sharedLock) {
                                System.out.printf("[%s] Acquired shared lock, working...%n",
                                        Thread.currentThread().getName());
                                TimeUnit.SECONDS.sleep(3);
                            }
                        }
                        case 5 -> {
                            // Another thread that competes for the same synchronised block
                            System.out.printf("[%s] Trying to acquire shared lock%n",
                                    Thread.currentThread().getName());
                            synchronized (sharedLock) {
                                System.out.printf("[%s] Acquired shared lock, working...%n",
                                        Thread.currentThread().getName());
                                TimeUnit.SECONDS.sleep(2);
                            }
                        }
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.printf("[%s] Finished work%n", Thread.currentThread().getName());
            }
        }

    private static class MonitorTask implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println("\n=== Current Thread States ===");
                for (Thread thread : monitoredThreads) {
                    System.out.printf("%-10s: %s%n",
                            thread.getName(), thread.getState());
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
}
