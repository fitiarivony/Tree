/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 *
 * @author FITIA ARIVONY
 */
public class MailHandling extends MimeMessageHelper {
    JavaMailSender sender;
    MimeMessage message;

    public JavaMailSender getSender() {
        return sender;
    }

    public void setSender(JavaMailSender sender) {
        this.sender = sender;
    }

    public MimeMessage getMessage() {
        return message;
    }

    public void setMessage(MimeMessage message) {
        this.message = message;
    }
    

    public MailHandling(JavaMailSender sender, String to, String subject, String text, MimeMessage message)
            throws MessagingException {
        super((message = sender.createMimeMessage()), true);
        this.setTo(to);
        this.setSubject(subject);
        this.setText(text, true);
        this.setSender(sender);
        this.setMessage(message);
    }
    public void send() {
        this.getSender().send(this.getMessage());
    }

}
