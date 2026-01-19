package project.hawanah.walletx.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.hawanah.walletx.data.model.User;

public interface UserRepository extends JpaRepository<User, String>{
    boolean existsByEmail(String email);
}
