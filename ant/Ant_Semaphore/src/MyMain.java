/**
 * Created by sigmund69 on 18.04.2016.
 */
public class MyMain {
    public static void main(String[] args) throws InterruptedException {
        MySemaphore mySemaphore = new MySemaphore();
        int numberOfActivThreads = Thread.activeCount();
        new Thread(new SingleWorker(mySemaphore)).start();
        new Thread(new SingleWorker(mySemaphore)).start();

        while (true) {
            Thread.sleep(1000);
            mySemaphore.release();
            if (Test.isExistAnyThread(numberOfActivThreads)) break;
        }
        mySemaphore.release();
        mySemaphore.release();
    }
}
