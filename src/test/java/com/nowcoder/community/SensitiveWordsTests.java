package com.nowcoder.community;

import com.nowcoder.community.util.SensitiveWordsFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = NowcoderApplication.class)
public class SensitiveWordsTests {

    @Autowired
    private SensitiveWordsFilter sensitiveWordsFilter;

    @Test
    public void testFilter() {
        String text = "哈哈，我在吸毒呢，你在干嘛，哎哟！abcd";
        System.out.println(sensitiveWordsFilter.filterText(text));
    }
}
