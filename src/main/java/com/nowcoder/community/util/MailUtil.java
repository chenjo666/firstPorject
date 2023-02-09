package com.nowcoder.community.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailUtil {
    // 日志
    private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);

    @Autowired
    private JavaMailSender javaMailSender; // 报错无关
    @Value("${spring.mail.username}")
    private String MAIL_SENDER;

    public void sendMail(String to, String subject, String text) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(MAIL_SENDER);  // 发件人
            helper.setTo(to);   // 收件人
            helper.setSubject(subject); // 主题
            helper.setText(text, true);   // 内容（true表示可以使用html）
            javaMailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
