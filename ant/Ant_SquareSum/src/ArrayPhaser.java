import java.util.concurrent.*;

/**
 * Created by sigmund69 on 01.04.2016.
 */
public class ArrayPhaser implements SquareSun {
    // private Phaser phaser = new Phaser(1);
    //private
    @Override
    public long getSquareSum(int[] values, int numberOfThreads) throws ExecutionException, InterruptedException {
        long sum = 0;
        Phaser phaser = new Phaser(1);

        int elementsOfThread;
        if (values.length < numberOfThreads) {
            elementsOfThread = values.length;
            numberOfThreads = 1;
        } else {
            elementsOfThread = values.length / numberOfThreads;
        }

        ExecutorService executor = null;
        int currentArrayIndex = 0;

        for (int i = 0; i < numberOfThreads; i++) {
            phaser.register();
            executor = Executors.newSingleThreadExecutor();
            final int tempCurrentArrayIndex = currentArrayIndex;
            final int elementsOfThreadTemp = elementsOfThread;
            final int[] valuesTemp = values;
            Future<Long> f = executor.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    long tempSum = 0;
                    int numberOfIterations = 0;
                    for (int j = tempCurrentArrayIndex; j < valuesTemp.length && numberOfIterations < elementsOfThreadTemp; j++) {
                        tempSum += (long) valuesTemp[j] * valuesTemp[j];
                        numberOfIterations++;
                    }
                    return tempSum;
                }
            });
            currentArrayIndex += elementsOfThreadTemp;
            sum += (long) f.get();
            System.out.println(sum);
            System.out.println("Phase " + phaser.getPhase() + "(current^ " + executor + ") is finished.");
            phaser.arriveAndDeregister();
            executor.shutdown();
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] values = { 3, 4, 5, 7, 2, 5 };
        ArrayPhaser arrayPhaser = new ArrayPhaser();
        long expected = 128;
        long actual = arrayPhaser.getSquareSum(values, 3);
        actual = arrayPhaser.getSquareSum(values,7);
    }
}




















