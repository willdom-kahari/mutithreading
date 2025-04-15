package month1.day1_starting_a_thread;


import java.util.logging.Logger;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class MyRunnable implements Runnable{
    private static final Logger LOG = Logger.getLogger(MyRunnable.class.getName());
    @Override
    public void run() {
        LOG.info("MyRunnable running: " + Thread.currentThread().getName());
    }
}
