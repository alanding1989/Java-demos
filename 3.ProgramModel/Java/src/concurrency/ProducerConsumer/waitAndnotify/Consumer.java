package ProducerConsumer.waitAndnotify;

import ProducerConsumer.AbstractProducerConsumer;
import java.util.Vector;

/**
 *  Author      :   AlanDing
 *  Time        :   2019/10/17 下午5:24
 *  File        :   Consumer.java
 *  Description :
 */
public class Consumer extends AbstractProducerConsumer {
    private final Vector<Integer> sharedQueue;

    public Consumer(Vector<Integer> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    protected void loop(Thread thread) {
        while (isRunning) {
            try {
                Thread.sleep(SLEEPTIME);

                while (sharedQueue.isEmpty()) {
                    synchronized (sharedQueue) {
                        System.out.println("Queue is empty, Consumer " + thread.getId()
                                               + " is waiting, size: " + sharedQueue.size() + "\n");
                        sharedQueue.wait();
                    }
                }

                synchronized (sharedQueue) {
                    System.out.println("Consumer consume data: " + sharedQueue.remove(0)
                                           + ", size: " + sharedQueue.size() + " \n");
                    sharedQueue.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
