package month1.starting_a_thread;


import java.util.logging.Logger;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class Main {
    public static void main(String[] args) {
        Thread myThread = new MyThread("extending-thread");
        Thread myRunnable = new Thread(new MyRunnable(), "runnable-thread");
        myThread.start();
        myRunnable.start();
    }

    private static class MyRunnable implements Runnable{
        private static final Logger LOG = Logger.getLogger(MyRunnable.class.getName());
        @Override
        public void run() {
            LOG.info("Running: " + Thread.currentThread().getName());
        }
    }

    private static class MyThread extends Thread{
        private static final Logger LOG = Logger.getLogger(MyThread.class.getName());
        @Override
        public void run() {
            LOG.info("Running: " + Thread.currentThread().getName());
        }


        public MyThread(String name) {
            super(name);
        }
    }
}
