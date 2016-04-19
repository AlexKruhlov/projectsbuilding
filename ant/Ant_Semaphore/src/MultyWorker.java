/**
 * Created by sigmund69 on 18.04.2016.
 */
class MultyWorker implements Runnable {
    private MySemaphore mySemaphore;
    private int id;
    private int number = 0;
    private int permits = 0;

    MultyWorker(MySemaphore mySemaphore, int permits) {
        this.mySemaphore = mySemaphore;
        id = ++ Test.numberObjects;
        this.permits = permits;
    }

    @Override
    public void run() {
        int startValue = mySemaphore.getAvailablePermits();
        try {
            mySemaphore.acquire(permits);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setPermits(int permits) {
        this.permits = permits;
    }
}