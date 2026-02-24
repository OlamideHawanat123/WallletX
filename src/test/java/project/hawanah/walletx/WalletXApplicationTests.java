package project.hawanah.walletx;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
@Import({TestMailConfig.class})
class WalletXApplicationTests {

    @MockitoBean
    private JavaMailSender javaMailSender;

    @Test
    void contextLoads() {
    }

}
