package project.hawanah.walletx.data.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.hawanah.walletx.data.model.VerificationCode;

import java.time.Instant;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, String> {
    @Transactional
    @Modifying
    @Query("DELETE FROM VerificationCode v WHERE v.expiryDate < :now")
    void deleteExpiredVerificationCodes(Instant now);
}
