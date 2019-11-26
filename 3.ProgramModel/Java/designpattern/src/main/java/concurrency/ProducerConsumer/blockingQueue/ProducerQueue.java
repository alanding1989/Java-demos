package ProducerConsumer.blockingQueue;

import ProducerConsumer.AbstractProducerConsumer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *  Author      :   AlanDing
 *  Time        :   2019/10/17 下午5:23
 *  File        :   Producer.java
 *  Description :
 */
public class ProducerQueue extends AbstractProducerConsumer {
    private final BlockingQueue<Integer> blockingQueue;

    public ProducerQueue(BlockingQueue<Integer> sharedQueue) {
        this.blockingQueue = sharedQueue;
    }

    @Override
    protected void loop(Thread thread) {
        while (isRunning) {
            try {
                Thread.sleep(SLEEPTIME);

                int data = count.incrementAndGet();
                // 队列满了，就阻塞当前线程，和wait()-notify不同的是，只要返回true，生产的data就已经被加入进共享队列
                // 即生产的任务已经完成，没有必要循环轮询判断。
                if (blockingQueue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println("Producer " + thread.getId() + " create "
                                           + data + " size: " + blockingQueue.size());
                }
                System.out.println(
                    "Queue is full, producer " + thread.getId() + " is waiting, size: "
                        + blockingQueue.size() + "\n");
            } catch (
                InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
