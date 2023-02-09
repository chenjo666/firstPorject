package com.nowcoder.community.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {

    @Bean
    public Producer kaptchaProducer() {
        Properties properties = new Properties();
        properties.setProperty("kaptcha.image.width", "100"); // 宽100px
        properties.setProperty("kaptcha.image.height", "40"); // 高40px
        properties.setProperty("kaptcha.textproducer.font.size", "32"); // 字体大小32px
        properties.setProperty("kaptcha.textproducer.font.color", "black"); // 字体颜色black
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"); // 字体类型
        properties.setProperty("kaptcha.textproducer.char.length", "4"); // 字体数量
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise"); // 字体干扰

        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Config config = new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }
}