package month1.kata;


/**
 * Create a thread that sleeps but can be interrupted.
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class Kata3 {

    public static void main(String[] args) {
        // Create and start a thread
        Thread worker = new Thread(new SleepingTask());
        worker.start();

        //Let the thread sleep for a while
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Interrupt the sleeping thread
        worker.interrupt();
    }

    static class SleepingTask implements Runnable{
        @Override
        public void run() {
            System.out.println("Thread started - going to sleep for 10 seconds");

            try {
                //Long sleep that can be interrupted
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                //Properly handle the interruption
                System.out.println("Thread was interrupted while sleeping!");
                Thread.currentThread().interrupt(); // Restore interrupt flag
            }

            System.out.println("Thread continuing with other work");
        }
    }
}

