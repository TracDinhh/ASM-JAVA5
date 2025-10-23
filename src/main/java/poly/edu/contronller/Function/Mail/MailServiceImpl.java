package poly.edu.contronller.Function.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom("duongdinhtrac0103@gmail.com"); // Gmail của bạn

            mailSender.send(message);
            System.out.println("Email đã gửi đến: " + to);
        } catch (Exception e) {
            System.err.println("Gửi email thất bại: " + e.getMessage());
            throw new RuntimeException("Gửi mail thất bại", e);
        }
    }
}