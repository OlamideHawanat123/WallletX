package project.hawanah.walletx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.hawanah.walletx.data.model.VerificationCode;
import project.hawanah.walletx.data.repository.UserRepository;
import project.hawanah.walletx.data.repository.VerificationCodeRepository;

import java.util.Random;

@Service
public class VerificationService {

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public void sendVerificationCode(String email){
        String code = generateVerificationCode();
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setCode(code);
        verificationCode.setEmail(email);
        verificationCodeRepository.save(verificationCode);
        emailService.sendAccountActivationEmail(email, code);
    }













    public String generateVerificationCode(){
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

}
