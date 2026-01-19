package project.hawanah.walletx.services;

import org.springframework.stereotype.Service;

import java.util.Random;

public class VerificationService {

    public String generateVerificationCode(){
        return String.valueOf(100000 + new Random().nextInt(900000));

    }

}
