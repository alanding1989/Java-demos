import ProducerConsumer.Consumer;
import ProducerConsumer.Producer;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Concurrency {
    public static void main(String[] args) throws InterruptedException {
        Vector<Integer> sharedQueue = new Vector<>();
        final int taskSize = 3;

        // ExecutorService service = Executors.newCachedThreadPool();
        ExecutorService service = new ThreadPoolExecutor(5, 10, 60L, TimeUnit.MILLISECONDS,
                                                         new SynchronousQueue<>());

        List<Producer> producers = new ArrayList<>();
        List<Consumer> consumers = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Producer p1 = new Producer(sharedQueue, taskSize);
            Consumer c = new Consumer(sharedQueue);
            producers.add(p1);
            consumers.add(c);
            service.submit(p1);
            service.submit(c);
        }

        Thread.sleep(10 * 1000);
        producers.forEach(Producer::stop);

        Thread.sleep(10 * 1000);
        consumers.forEach(Consumer::stop);

        service.shutdown();
        System.out.println("\n @@ Service is shutting down !!! \n");
    }
}
