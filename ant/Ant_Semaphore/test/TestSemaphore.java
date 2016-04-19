/**
 * Created by Alexandr Kruhlov on 24.03.2016.
 */

public class TestSemaphore {
    public static int numberObjects = 0;

    @org.junit.Test
    public void singleThread() throws InterruptedException {
        MySemaphore mySemaphore = new MySemaphore();

        int numberOfActivThreads = Thread.activeCount();
        new Thread(new SingleWorker(mySemaphore)).start();
        new Thread(new SingleWorker(mySemaphore)).start();

        while (true) {
            Thread.sleep(1000);
            mySemaphore.release();
            if (isExistAnyThread(numberOfActivThreads)) break;
        }
        mySemaphore.release();
        mySemaphore.release();
    }

    @org.junit.Test
    public void testMultyThread() throws InterruptedException {
        MySemaphore mySemaphore = new MySemaphore();
        int numberOfActivThreads = Thread.activeCount();

        new Thread(new MultyWorker(mySemaphore, 4)).start();
        while (true) {
            Thread.sleep(1000);
            mySemaphore.release(2);
            if (isExistAnyThread(numberOfActivThreads)) break;
        }
    }

    public static boolean isExistAnyThread(int numberOfActivThreads) {
        return Thread.activeCount() - numberOfActivThreads == 0;
    }
}
















