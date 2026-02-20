package project.hawanah.walletx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.hawanah.walletx.data.model.User;
import project.hawanah.walletx.data.model.VerificationCode;
import project.hawanah.walletx.data.repository.UserRepository;
import project.hawanah.walletx.data.repository.VerificationCodeRepository;
import project.hawanah.walletx.dtos.requests.ActivateAccountRequest;
import project.hawanah.walletx.dtos.responses.ActivateAccountResponse;
import project.hawanah.walletx.exceptions.ExpiredVerificationCodeException;
import project.hawanah.walletx.exceptions.InvalidCodeException;
import java.time.Instant;
import java.util.Random;

@Service
public class VerificationService {

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private WalletService walletService;

    public VerificationCode sendVerificationCode(String email){
        String code = generateVerificationCode();
        VerificationCode vCode = new VerificationCode(code, email);
        emailService.sendAccountActivationEmail(email, code);
        return verificationCodeRepository.save(vCode);
    }

    public ActivateAccountResponse activateAccount(ActivateAccountRequest request){
        VerificationCode verificationCode = verificationCodeRepository
                .findById(request.getVerificationId())
                .orElseThrow(() -> new InvalidCodeException("Code is invalid!") );

        if (verificationCode
                .getExpiryDate()
                .isBefore(Instant.now()))
            throw new ExpiredVerificationCodeException("Code is expired!");
        if (!verificationCode.getCode().equals(request.getCode())) throw new InvalidCodeException("Code is invalid");
        verifyUser(verificationCode.getEmail());
        walletService.createWallet(verificationCode.getEmail());
        return setVerificationResponse();
    }


    @Scheduled(fixedRate = 60000)
    public void deleteExpiredVerificationCodes(){
        verificationCodeRepository.deleteExpiredVerificationCodes(Instant.now());
    }

    public String getCodeId(String code){
        VerificationCode verificationCode = verificationCodeRepository.findByCode(code);
        return verificationCode.getId();
    }

    public String generateVerificationCode(){
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    private void verifyUser(String email){
        User user = userRepository.findByEmail(email);
        if (user == null) throw new UsernameNotFoundException("User doesn't exist!");
        user.setActivated(true);
        userRepository.save(user);
        emailService.sendAccountActivationSuccessEmail(user.getEmail(), user.getLastName());
    }

    private ActivateAccountResponse setVerificationResponse(){
        ActivateAccountResponse response = new ActivateAccountResponse();
        response.setMessage("Account verification was successful, welcome onboard!");
        return response;
    }

}
