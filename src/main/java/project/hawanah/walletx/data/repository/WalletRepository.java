package project.hawanah.walletx.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.hawanah.walletx.data.model.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
}
