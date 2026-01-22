package project.hawanah.walletx.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import project.hawanah.walletx.dtos.requests.RegisterUserRequest;
import project.hawanah.walletx.dtos.responses.RegisterUserResponse;
import project.hawanah.walletx.dtos.requests.UserLoginRequest;
import project.hawanah.walletx.dtos.responses.UserLoginResponse;
import project.hawanah.walletx.exceptions.EmailExistsException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class AuthTest {
    @Autowired
    private AuthService authService;

    @Test
    public void testThatUserCanRegister(){
        RegisterUserRequest request = new RegisterUserRequest();
        request.setFirstName("Olamide");
        request.setLastName("Bello");
        request.setAge(15);
        request.setEmail("meijaks@gmail.com");
        request.setPassword("olamide123");
        request.setAddress("Kingsway street, Yaba");
        request.setPhoneNumber("09138146912");

        RegisterUserResponse response = authService.registerUser(request);
        assertNotNull(response);
        assertEquals("User registered successfully", response.getMessage());
    }

    @Test
    public void testThatRegisterUserDoesntAllowDuplicateEmails(){
        testThatUserCanRegister();
        assertThrows(EmailExistsException.class, this::testThatUserCanRegister);
    }

    @Test
    public void testThatUserCannotLeaveAFieldEmptyDuringRegistration(){
        RegisterUserRequest request = new RegisterUserRequest();
        request.setFirstName("Babajide");
        request.setLastName("Bello");
        request.setAge(15);
        request.setEmail("raheemhawanat@gmail.com");
        request.setPassword("olamide123");
        request.setAddress("Kingsway street, Yaba");
        request.setPhoneNumber("");
        RegisterUserResponse response = authService.registerUser(request);
    }

    @Test
    public void testThatUserCanLogin(){
        UserLoginRequest request = new UserLoginRequest();
        request.setEmail("raheemhawanat@gmail.com");
        request.setPassword("olamide123");
        UserLoginResponse response = authService.login(request);
        assertNotNull(response);
        assertEquals("login successful!", response.getMessage());
    }

}
