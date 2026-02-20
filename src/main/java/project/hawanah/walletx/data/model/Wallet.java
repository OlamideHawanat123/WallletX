package project.hawanah.walletx.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
public class Wallet {
    @Id
    private String id;

    private String userId;

    private BigDecimal balance;

    private Instant createdAt;

}
