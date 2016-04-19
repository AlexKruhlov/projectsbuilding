/**
 * Created by Alexandr Kruhlov on 24.03.2016.
 */

public class MySemaphore implements Semaphore {
    private int permits = 0;
    private Object lock = new Object();

    // Запрашивает разрешение. Если есть свободное захватывает его.
    // Если нет - приостанавливает поток до тех пор пока не появится свободное разрешение.
    @Override
    public void acquire() throws InterruptedException {
        boolean isPermitsGeven = false;
        while (true) {
            synchronized (lock) {
                System.out.println("Please, give me one permit!");
                if (getAvailablePermits() > 0) {
                    setPermits(getAvailablePermits() - 1);
                    System.out.println("Please, take the permit!");
                    isPermitsGeven = true;
                } else {
                    System.out.println("Sorry, but the system has not free permits now!");
                    lock.wait();
                    //setPermits(getAvailablePermits() - 1);
                }
            }
            if (isPermitsGeven) break;
        }
    }

    // Запрашивает переданое количество разрешений. Если есть переданое количество свободных разрешений
    // захватывает их.

    // Если нет - приостанавливает поток до тех пор пока не появится переданое колтчество свободных
    // разрешений.

    @Override
    public void acquire(int permits) throws InterruptedException {
        boolean isPermitsGeven = false;
        while (true) {
            synchronized (lock) {
                System.out.println("Please, give me " + permits + " permits!");
                if (getAvailablePermits() >= permits) {
                    System.out.println("Please, take " + permits + " permits!");
                    setPermits(getAvailablePermits() - permits);
                    isPermitsGeven = true;
                } else {
                    System.out.println("Sorry, but the system has " + getAvailablePermits() + " free permits now!");
                    lock.wait();
                }
            }
            if (isPermitsGeven) break;
        }
    }

    // Отпускает разрешение возвращая его семафору.
    @Override
    public void release() {
        synchronized (lock) {
            setPermits(getAvailablePermits() + 1);
            System.out.println("Added one permit, number of permits: " + getAvailablePermits());
            lock.notify();
        }
    }

    // Отпускает переданое количество разрешений возварщая их семафору.

    @Override
    public void release(int permits) {
        synchronized (lock) {
            setPermits(getAvailablePermits() + permits);
            System.out.println("Added " + permits + " permits, number of permits: " + getAvailablePermits());
            lock.notifyAll();
        }
    }

    // Возвращает количество свободных разрешений на данный момент.
    @Override
    public int getAvailablePermits() {
        return permits;
    }

    public void setPermits(int permits) {
        this.permits = permits;
    }
}


