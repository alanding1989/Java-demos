package ProducerConsumer;

import java.util.Vector;

/**
 *  Author      :   AlanDing
 *  Time        :   2019/10/17 下午5:23
 *  File        :   Producer.java
 *  Description :
 */
public class Producer extends AbstractProducerConsumer {
    private final Vector<Integer> sharedQueue;
    private final int SIZE;

    public Producer(Vector<Integer> sharedQueue, int size) {
        this.sharedQueue = sharedQueue;
        this.SIZE = size;
    }

    @Override
    protected void loop(Thread thread) {
        while (isRunning) {
            try {
                Thread.sleep(SLEEPTIME);

                // 队列满了，就阻塞当前线程
                while (sharedQueue.size() == SIZE) {
                    synchronized (sharedQueue) {
                        System.out.println(
                            "Queue is full, producer " + thread.getId() + " is waiting, size: "
                                + sharedQueue.size() + "\n");
                        sharedQueue.wait();
                    }
                }

                // 队列没满
                synchronized (sharedQueue) {
                    int data = count.incrementAndGet();
                    sharedQueue.add(data);
                    System.out.println("producer " + thread.getId() + " create data:" + data
                                           + ", size: " + sharedQueue.size() + "\n");
                    sharedQueue.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
