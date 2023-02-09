package com.nowcoder.community;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = NowcoderApplication.class)
public class WkTests {
    @Test
    public void testWk() {
        String cmd = "d:/code-tools/wkhtmltopdf/bin/wkhtmltoimage --quality 75 https://www.nowcoder.com d:/code-datas/wk-images/test5.png";
        try {
            Runtime.getRuntime().exec(cmd);
            System.out.println("ok!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
