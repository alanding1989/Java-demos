package ProducerConsumer.blockingQueue;

import ProducerConsumer.AbstractProducerConsumer;
import java.util.concurrent.BlockingQueue;

/**
 *  Author      :   AlanDing
 *  Time        :   2019/10/17 下午5:24
 *  File        :   Consumer.java
 *  Description :
 */
public class ConsumerQueue extends AbstractProducerConsumer {
    private final BlockingQueue<Integer> blockingQueue;

    public ConsumerQueue(BlockingQueue<Integer> sharedQueue) {
        this.blockingQueue = sharedQueue;
    }

    @Override
    protected void loop(Thread thread) {
        while (isRunning) {
            try {
                Thread.sleep(SLEEPTIME);

                if (!blockingQueue.isEmpty()) {
                    System.out.println("Consumer consume data: " + blockingQueue.take()
                                           + ", size: " + blockingQueue.size() + " \n");
                }

                System.out.println("Queue is empty, Consumer " + thread.getId()
                                       + " is waiting, size: " + blockingQueue.size() + "\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
