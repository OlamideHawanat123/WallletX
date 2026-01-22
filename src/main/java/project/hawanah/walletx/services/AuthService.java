package project.hawanah.walletx.services;

import project.hawanah.walletx.dtos.requests.RegisterUserRequest;
import project.hawanah.walletx.dtos.requests.UserLoginRequest;
import project.hawanah.walletx.dtos.responses.RegisterUserResponse;
import project.hawanah.walletx.dtos.responses.UserLoginResponse;

public interface AuthService {
    public RegisterUserResponse registerUser(RegisterUserRequest request);

    UserLoginResponse login(UserLoginRequest request);
}
