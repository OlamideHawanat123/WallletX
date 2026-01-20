package project.hawanah.walletx.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Entity
@Getter @Setter
public class VerificationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String code;
    private String email;
    private Instant expiryDate;
    private boolean isExpired;

    public  VerificationCode(String code, String email) {
        this.code = code;
        this.email = email;
        this.expiryDate = Instant.now().plusSeconds(600);
    }

    public VerificationCode() {}


}
