package month1.day1_starting_a_thread;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class Main {
    public static void main(String[] args) {
        Thread myThread = new MyThread();
        Thread myRunnable = new Thread(new MyRunnable(), "runnable-thread");
        myThread.start();
        myRunnable.start();
    }
}
