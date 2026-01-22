package project.hawanah.walletx.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.hawanah.walletx.data.model.User;
import project.hawanah.walletx.data.model.VerificationCode;
import project.hawanah.walletx.data.repository.UserRepository;
import project.hawanah.walletx.dtos.requests.RegisterUserRequest;
import project.hawanah.walletx.dtos.requests.UserLoginRequest;
import project.hawanah.walletx.dtos.responses.RegisterUserResponse;
import project.hawanah.walletx.dtos.responses.UserLoginResponse;
import project.hawanah.walletx.exceptions.EmailExistsException;
import project.hawanah.walletx.exceptions.ImpermissibleRequestException;
import project.hawanah.walletx.exceptions.InvalidCredentialsException;
import project.hawanah.walletx.utils.Mapper;

@Service
public class AuthServiceImplementation implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationService verificationService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    @Transactional
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        if (checkEmailExistence(request.getEmail())) throw new EmailExistsException("Email already exists");

        User user = Mapper.mapRequestToUser(request);
        user = userRepository.save(user);

        VerificationCode vCode = verificationService.sendVerificationCode(user.getEmail());
        verificationService.sendVerificationCode(user.getEmail());
        return respondToUserRegistration(user, vCode.getId());

    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) throw new UsernameNotFoundException("User not found");
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) throw new InvalidCredentialsException("Invalid details");
        if (!user.isActivated()) throw new ImpermissibleRequestException("You're not permitted to login!");

    }


    private RegisterUserResponse respondToUserRegistration(User user, String codeId) {
        RegisterUserResponse response = new RegisterUserResponse();
        response.setMessage("User registered successfully, Please, check your email for activation code");
        response.setId(user.getId());
        response.setVerificationId(codeId);
        return response;
    }

    private boolean checkEmailExistence(String email) {
        return userRepository.existsByEmail(email);
    }
}
