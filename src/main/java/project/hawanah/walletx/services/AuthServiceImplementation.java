package project.hawanah.walletx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.hawanah.walletx.data.model.User;
import project.hawanah.walletx.data.repository.UserRepository;
import project.hawanah.walletx.dtos.requests.RegisterUserRequest;
import project.hawanah.walletx.dtos.responses.RegisterUserResponse;
import project.hawanah.walletx.exceptions.EmailExistsException;
import project.hawanah.walletx.utils.Mapper;

@Service
public class AuthServiceImplementation implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationService verificationService;


    @Override
    public RegisterUserResponse registerUser( RegisterUserRequest request) {
       if (checkEmailExistence(request.getEmail())) throw new EmailExistsException("Email already exists");
       User user = Mapper.mapRequestToUser(request);
        userRepository.save(user);
        String code = verificationService.sendVerificationCode(user.getEmail());
        String codeId = verificationService.getCodeId(code);
       return respondToUserRegistration(user, codeId);

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
