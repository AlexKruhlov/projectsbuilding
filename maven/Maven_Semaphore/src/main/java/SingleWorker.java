
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
            System.out.println("Запускаем o.acquire() потока" + id);
            mySemaphore.acquire();
            System.out.println("Вышли из метода o.acquire() потока" + id);
        } catch (InterruptedException e) {
            System.out.println("Сработало исключение метода o.acquire() потока" + id);
            e.printStackTrace();
        }

        System.out.println("Выполняем код после получения разрешения...");
    }
}
