package ProducerConsumer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *  Author      :   AlanDing
 *  Time        :   2019/10/17 下午4:39
 *  File        :   ProducerConsumer.java
 *  Description :
 */

public abstract class AbstractProducerConsumer implements Runnable {

    protected static final int SLEEPTIME = 1000;
    protected static AtomicInteger count = new AtomicInteger();
    protected volatile boolean isRunning = true;

    @Override
    public void run() {
        // thread can`t treat as a instance field or static field, because the class is
        // instantiated in the main thread and than transfer to method submit().
        Thread thread = Thread.currentThread();
        System.out.println("Start " + this.getClass().getName() + " id: " + thread.getId() + " \n");

        loop(thread);
    }

    protected abstract void loop(Thread thread);

    public void stop() {
        isRunning = false;
        // 当实现Runnable接口时，主线程调用 p.stop()-Thread.currentThread().interrupt();
        // 方法并不是作用在p线程上，所以一直无法中断线程执行。
        // 必须要设置标志位
        System.out.println("\n" + this.getClass().getName() + " id: "
                               + Thread.currentThread().getId() + " is stopped !!!\n");
    }
}
