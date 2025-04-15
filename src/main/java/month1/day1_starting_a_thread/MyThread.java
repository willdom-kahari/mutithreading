package month1.day1_starting_a_thread;


import java.util.logging.Logger;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class MyThread extends Thread{
    private static final Logger LOG = Logger.getLogger(MyThread.class.getName());
    @Override
    public void run() {
        LOG.info("MyThread running: " + Thread.currentThread().getName());
    }
}
