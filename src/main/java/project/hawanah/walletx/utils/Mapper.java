package project.hawanah.walletx.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.hawanah.walletx.data.model.User;
import project.hawanah.walletx.dtos.requests.RegisterUserRequest;

public class Mapper {
    static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public static User mapRequestToUser(RegisterUserRequest request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        user.setAge(request.getAge());
        return user;
    }
}
