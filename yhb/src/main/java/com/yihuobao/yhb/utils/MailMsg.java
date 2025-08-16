package com.yihuobao.yhb.utils;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailMsg {
    @Resource
    private JavaMailSenderImpl mailSender;
    public boolean mail(String userEmail, String code) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setText("<p style='color: blue'>欢迎使用易货宝！你的验证码为：" + code + "(有效期为2分钟)</p>", true);
        helper.setSubject("易货宝验证码");
        helper.setTo(userEmail);
        helper.setFrom("3488570183@qq.com");
        mailSender.send(mimeMessage);
        return true;
    }
}
