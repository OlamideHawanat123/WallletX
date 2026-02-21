package project.hawanah.walletx.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
public class WalletServiceTest {
    @Autowired
    private WalletService walletService;

    @MockitoBean
    private JavaMailSender javaMailSender;

    AuthTest authTest = new AuthTest();

    @Test
    public void testThatWalletGetsCreatedAfterAccountActivation(){

    }
}
