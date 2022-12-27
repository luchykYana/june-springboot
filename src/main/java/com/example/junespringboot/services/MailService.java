package com.example.junespringboot.services;

import com.example.junespringboot.models.Customer;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {

    private JavaMailSender javaMailSender;

    public void send(Customer customer) {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(customer.getEmail());
            helper.setText("<a href='http://localhost:8080/customers/activate/" + customer.getId()+"'>click to activate</a>", true);
            helper.setFrom(new InternetAddress("yanaluchyk@gmail.com"));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        javaMailSender.send(message);
    }
}
