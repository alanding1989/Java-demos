import ProducerConsumer.blockingQueue.ConsumerQueue;
import ProducerConsumer.blockingQueue.ProducerQueue;
import ProducerConsumer.waitAndnotify.Consumer;
import ProducerConsumer.waitAndnotify.Producer;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Concurrency {
    public static void main(String[] args) throws InterruptedException {
        // ExecutorService service = Executors.newCachedThreadPool();
        @SuppressWarnings("AlibabaThreadShouldSetName")
        ExecutorService service = new ThreadPoolExecutor(5, 10, 60L, TimeUnit.MILLISECONDS,
                                                         new SynchronousQueue<>());

        // waitAndnotify(service);
        blockingQueue(service);
    }

    private static void blockingQueue(ExecutorService service) throws InterruptedException {
        BlockingDeque<Integer> sharedQueue = new LinkedBlockingDeque<>(4);

        List<ProducerQueue> producers = new ArrayList<>();
        List<ConsumerQueue> consumers = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            ProducerQueue p1 = new ProducerQueue(sharedQueue);
            ConsumerQueue c = new ConsumerQueue(sharedQueue);
            producers.add(p1);
            consumers.add(c);
            service.submit(p1);
            service.submit(c);
        }

        Thread.sleep(10 * 1000);
        producers.forEach(ProducerQueue::stop);

        Thread.sleep(10 * 1000);
        consumers.forEach(ConsumerQueue::stop);

        service.shutdown();
        System.out.println("\n @@ Service is shutting down !!! \n");
    }

    private static void waitAndnotify(ExecutorService service) throws InterruptedException {
        Vector<Integer> sharedQueue = new Vector<>();
        final int taskSize = 3;

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
