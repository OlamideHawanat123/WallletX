package project.hawanah.walletx.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.hawanah.walletx.data.model.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, String> {
}
