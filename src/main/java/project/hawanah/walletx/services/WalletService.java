package project.hawanah.walletx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.hawanah.walletx.data.model.User;
import project.hawanah.walletx.data.model.Wallet;
import project.hawanah.walletx.data.repository.UserRepository;
import project.hawanah.walletx.data.repository.WalletRepository;
import project.hawanah.walletx.utils.Mapper;


@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    public Wallet createWallet(String email){
        User user = userRepository.findByEmail(email);
        if(user == null)throw new UsernameNotFoundException("User does not exist");
        Wallet wallet = Mapper.mapDetailsToWallet(user);
        walletRepository.save(wallet);
        userRepository.save(user);
        return wallet;
    }
}
