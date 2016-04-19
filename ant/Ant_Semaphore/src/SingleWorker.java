
/**
 * Created by sigmund69 on 18.04.2016.
 */
class SingleWorker implements Runnable {
    private MySemaphore mySemaphore;
    private int id;
    private int number = 0;

    SingleWorker(MySemaphore mySemaphore) {
        this.mySemaphore = mySemaphore;
        id = ++ Test.numberObjects;
    }

    @Override
    public void run() {
        int startValue = mySemaphore.getAvailablePermits();
        try {
            mySemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
