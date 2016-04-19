/**
 * Created by sigmund69 on 18.04.2016.
 */
public class Test {

    public static int numberObjects = 0;

    public static boolean isExistAnyThread(int numberOfActivThreads) {
        return Thread.activeCount() - numberOfActivThreads == 0;
    }

}
