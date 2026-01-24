package project.hawanah.walletx.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class WalletServiceTest {
    @Autowired
    private WalletService walletService;

    AuthTest authTest = new AuthTest();

    @Test
    public void testThatWalletGetsCreatedAfterAccountActivation(){

    }
}
