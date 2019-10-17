package demo.thread_task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.cron.CronUtil;


public class CronTest {

    public static void main(String[] args) {
        CronUtil.setMatchSecond(true);
        CronUtil.start();

        CronUtil.schedule("*/2 * * * * ?",
                          (Runnable) () -> System.out.println(DateUtil.now() + " 执行新任务"));
    }
}
