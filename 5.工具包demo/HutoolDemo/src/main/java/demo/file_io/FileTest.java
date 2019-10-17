package demo.file_io;

import cn.hutool.core.io.resource.ClassPathResource;
import demo.TestUtil.AbstractTestBefore;


public class FileTest extends AbstractTestBefore {

    // @Test
    public void test1() {
        ClassPathResource resource = new ClassPathResource("META-INF/MANIFEST.MF");
        System.out.println(resource.readUtf8Str());
    }
}
