
import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: AlanDing.
 * Time  : 19-8-1 上午1:51.
 * File  : Main.java.
 * Description:
 */

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("main Start...");
        long st = Instant.now().toEpochMilli();
        List<Thread> t = ThreadBuilder.builder("timer", "alan", "bob", "alice");
//        threadStart(creatThread("my", "alan", "bob", "alice"));
//        threadInterupt(creatThread("my", "alan", "bob", "alice"));
        threadDaemon(t);

        System.out.println("\n main End... \n");
        System.out.println(Instant.now().toEpochMilli() - st);
    }

    private static void threadDaemon(List<Thread> threads) throws InterruptedException {
        Thread t = threads.get(0);
        // when main thread die, setDaemon() will automaticly die
        t.setDaemon(true);
        t.start();
        Thread.sleep(5000);
    }

    private static void threadStart(List<Thread> threads) throws InterruptedException {
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

    private static void threadInterupt(List<Thread> threads) throws InterruptedException {
        threads.forEach(Thread::start);
        Thread.sleep(1000);
        threads.forEach(Thread::interrupt);
    }
}

class ThreadBuilder {
//    public static void whichThread(String threadName) {
//        if (threadName.equals("my")) {
//        } else if (threadName.equals("timer")) {
//        }
//    }

    static List<Thread> builder(String... names) {
        List<Thread> threads = new ArrayList<>();
        if (names[0].equals("my")) {
            Arrays.asList(names).subList(1, names.length)
                .forEach(name -> threads.add(new MyThread(name)));
        } else if (names[0].equals("timer")) {
            Arrays.asList(names).subList(1, names.length)
                .forEach(name -> threads.add(new TimerThread(name)));
        }
        return threads;
    }

    private static class MyThread extends Thread {

        private final String name;

        MyThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                System.out.println(name + " is running... in thread " + this.getId());
                try {
                    Thread.sleep(100);
                    System.out.println("Goodbye " + name);
                } catch (InterruptedException e) {
                    System.out.println("@@interrupted by main thread ");
                    break;
//                e.printStackTrace();
                }
            }
        }
    }

    private static class TimerThread extends Thread {

        private final String name;

        TimerThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(name + " is running... in Daemon Thread " + this.getId());
                try {
                    Thread.sleep(1000);
                    System.out
                        .println(LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
                } catch (InterruptedException e) {
                    System.out.println("@@interrupted by main thread ");
//                e.printStackTrace();
                }
            }
        }
    }
}


