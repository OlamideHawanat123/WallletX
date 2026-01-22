package project.hawanah.walletx.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserLoginResponse {
    private String message;
    private String token;
}
