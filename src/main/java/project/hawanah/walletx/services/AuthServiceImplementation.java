package project.hawanah.walletx.services;

import jakarta.validation.Valid;
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

    @Override
    public RegisterUserResponse registerUser( RegisterUserRequest request) {
       if (checkEmailExistence(request.getEmail())) throw new EmailExistsException("Email already exists");
       User user = Mapper.mapRequestToUser(request);
        userRepository.save(user);
       return respondToUserRegistration(user);
    }















    private RegisterUserResponse respondToUserRegistration(User user) {
        RegisterUserResponse response = new RegisterUserResponse();
        response.setMessage("User registered successfully");
        response.setId(user.getId());
        return response;
    }

    private boolean checkEmailExistence(String email) {
        return userRepository.existsByEmail(email);
    }
}
