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
	// 这的thread 变量不能作为类的字段，因为字段是在主进程初始化的，然后在run中获取的ID实际是主进程的ID，
	// 覆写的run()方法才是在子进程中被调用的任务，所以需要在run()里初始化。
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
