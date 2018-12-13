import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        for (int i = 1; i < 128; i++) {
            long duration = run(i);
            int calc_count = 10000;
            System.out.println(i + ";" + (double) (duration / calc_count));
        }
    }

    private static long run(int thread_count) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(thread_count, thread_count, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        long time0 = System.nanoTime();
        for (int i = 0; i < 10000; i++)
            executor.submit(new Worker());
        while (executor.getTaskCount() != executor.getCompletedTaskCount()) ;
        long time1 = System.nanoTime();
        executor.shutdown();
        return (time1 - time0);
    }
}

class Worker implements Runnable {
    @Override
    public void run() {
        long time0 = System.nanoTime();
        while (System.nanoTime() < time0 + 50000) {
            long a = System.nanoTime() * 2;
        }
        ;
    }
}
