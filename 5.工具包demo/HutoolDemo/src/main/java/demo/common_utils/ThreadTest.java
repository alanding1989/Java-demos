package demo.common_utils;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ArrayUtil;
import demo.TestUtil.AbstractTestBefore;
import demo.TestUtil.Comment;
import java.util.stream.IntStream;
import org.junit.Test;

public class ThreadTest extends AbstractTestBefore {

    @Test
    @Comment("多线程工具")
    public void test1() {
        p3("所有线程", ArrayUtil.join(ThreadUtil.getThreads(), "\r\n\t\t\t\t"));
        p3("获取主线程", ThreadUtil.getMainThread());

        p3("不用捕捉异常的sleep", "ThreadUtil.sleep(2000);");
        ThreadUtil.sleep(2000);

        p3("很方便的通过线程池执行任务", "");
        IntStream.range(0, 10).parallel().<Runnable>mapToObj(
            i -> () -> System.out.println("\t\t当前线程是：" + Thread.currentThread()))
            .forEach(ThreadUtil::execute);
    }
}
