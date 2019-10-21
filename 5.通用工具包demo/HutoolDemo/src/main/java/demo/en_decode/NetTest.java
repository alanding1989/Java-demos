package demo.en_decode;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.ZipUtil;
import demo.TestUtil.AbstractTestBefore;
import demo.TestUtil.Comment;
import org.junit.Test;

public class NetTest extends AbstractTestBefore {

    @Test
    @Comment("ipv4 和 long 互换")
    public void test1() {
        String ip = "220.181.57.216";
        long value = 0L;

        value = NetUtil.ipv4ToLong(ip);
        ip = NetUtil.longToIpv4(value);
        p2("ip地址", ip, "对应的long", value);
        p2("long值 ", value, "对应的ip", ip);
    }


    @Test
    @Comment("判断端口和地址")
    public void test2() {
        ZipUtil z;
        int port1 = 80;
        int port2 = 68000;
        String ip1 = "220.181.57.216";
        String ip2 = "192.168.0.8";
        p2("端口号", port1, "是否已经被占用", !NetUtil.isUsableLocalPort(port1));
        p2("端口号", port2, "是否一个有效的端口号", NetUtil.isValidPort(port2));
        p2("ip地址", ip1, "是否是个内网地址", NetUtil.isInnerIP(ip1));
        p2("ip地址", ip2, "是否是个内网地址", NetUtil.isInnerIP(ip2));
    }


    @Test
    @Comment("其他相关操作")
    public void test3() {
        String ip = "220.181.57.216";
        String host = "baidu.com";

        p2("原ip", ip, "隐藏最后一位", NetUtil.hideIpPart(ip));
        p2("域名", host, "对应的ip地址", NetUtil.getIpByHost(host));
        p3("本机ip地址", NetUtil.localIpv4s());
        p3("本机mac地址", NetUtil.getLocalMacAddress());
    }
}
