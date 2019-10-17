package demo.thread_task;

import cn.hutool.core.date.DateUtil;

public class HutoolCronTask {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(DateUtil.now() + " this is HutoolCronTask");
        }).start();
    }
}
