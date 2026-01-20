package project.hawanah.walletx.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.hawanah.walletx.data.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    boolean existsByEmail(String email);

    User findByEmail(String email);
}
