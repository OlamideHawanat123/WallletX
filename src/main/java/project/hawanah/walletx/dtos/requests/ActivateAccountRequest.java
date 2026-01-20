package project.hawanah.walletx.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ActivateAccountRequest {
    private String verificationId;
    private String code;
}
