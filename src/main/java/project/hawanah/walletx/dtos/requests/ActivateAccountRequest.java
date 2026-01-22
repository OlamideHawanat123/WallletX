package project.hawanah.walletx.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ActivateAccountRequest {
    @NotBlank(message = "Verification id is required")
    private String verificationId;

    @NotBlank(message = "Code is required")
    private String code;
}
