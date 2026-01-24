package project.hawanah.walletx.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.hawanah.walletx.dtos.requests.ActivateAccountRequest;
import project.hawanah.walletx.dtos.requests.RegisterUserRequest;
import project.hawanah.walletx.dtos.requests.UserLoginRequest;
import project.hawanah.walletx.dtos.responses.ActivateAccountResponse;
import project.hawanah.walletx.dtos.responses.RegisterUserResponse;
import project.hawanah.walletx.dtos.responses.UserLoginResponse;
import project.hawanah.walletx.services.AuthService;
import project.hawanah.walletx.services.VerificationService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private VerificationService verificationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        RegisterUserResponse registerUserResponse = authService.registerUser(registerUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUserResponse);
    }

    @PatchMapping("/activateAccount")
    public ResponseEntity<?> activateAccount(@Valid @RequestBody ActivateAccountRequest activateAccountRequest) {
        ActivateAccountResponse response =  verificationService.activateAccount(activateAccountRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        UserLoginResponse userLoginResponse = authService.login(userLoginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(userLoginResponse);
    }
}
