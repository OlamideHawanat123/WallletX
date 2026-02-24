package project.hawanah.walletx;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.mail.javamail.JavaMailSender;

@TestConfiguration
public class TestMailConfig {
    public JavaMailSender javaMailSender(){
        return Mockito.mock(JavaMailSender.class);
    }
}
