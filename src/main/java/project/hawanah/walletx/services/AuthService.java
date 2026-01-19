package project.hawanah.walletx.services;

import project.hawanah.walletx.dtos.requests.RegisterUserRequest;
import project.hawanah.walletx.dtos.responses.RegisterUserResponse;

public interface AuthService {
    public RegisterUserResponse registerUser(RegisterUserRequest request);
}
