package project.hawanah.walletx.utils;

import project.hawanah.walletx.data.model.User;
import project.hawanah.walletx.dtos.requests.RegisterUserRequest;

public class Mapper {
    public static User mapRequestToUser(RegisterUserRequest request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        user.setAge(request.getAge());
        return user;
    }
}
