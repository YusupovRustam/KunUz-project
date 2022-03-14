package com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendEmail(String toAccound,String title,String text){
        SimpleMailMessage msg=new SimpleMailMessage();
        msg.setTo(toAccound);
        msg.setSubject(title);
        msg.setText(text);
        javaMailSender.send(msg);

    }
}
