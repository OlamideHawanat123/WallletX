package project.hawanah.walletx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public void sendAccountActivationEmail(String email, String code){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setText(
                "Email Activation Code: " + code + "\n" +
                        "Use this code to activate your account."
        );
        message.setSubject("WalletX: Account Activation-" + code);
        message.setFrom("raheemhawanat@gmail.com");
        mailSender.send(message);
        
        
    }


}
