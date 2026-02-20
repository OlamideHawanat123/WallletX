package project.hawanah.walletx.data.model;

import jakarta.persistence.*;
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

    private BigDecimal balance;

    private Instant createdAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
