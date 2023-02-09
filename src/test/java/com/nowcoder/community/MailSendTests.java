package com.nowcoder.community;

import com.nowcoder.community.util.MailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = NowcoderApplication.class)
public class MailSendTests {

    @Autowired
    private MailUtil mailUtil;

    // thymeleaf模板引擎
    @Autowired
    private TemplateEngine templateEngine; // 报错不影响

    @Test
    public void testTextMail() {
        mailUtil.sendMail("2357808792@qq.com", "shut", "spring boot send mail");
    }

    @Test
    public void testHTMLMail() {
        Context context = new Context();
        context.setVariable("username", "朱高杰");
        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);
        mailUtil.sendMail("2862493505@qq.com", "html", content);
    }

}
