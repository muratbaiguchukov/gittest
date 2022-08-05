package kg.itacademy.stomservice.controller;

import kg.itacademy.stomservice.model.UserAuthModel;
import kg.itacademy.stomservice.model.UserModel;
import kg.itacademy.stomservice.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {
    final UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/sign-in")
    public String getAuthToken(@Valid @RequestBody UserAuthModel userAuthDto) {
        return userService.getToken(userAuthDto);
    }

    @PostMapping("/sign-up")
    public String register(@Valid @RequestBody UserModel userModel) {
        return userService.createUser(userModel);
    }

    @GetMapping("Testtest")
    public void test() {
        System.out.println(passwordEncoder.encode("admin123"));
        ;
    }
}