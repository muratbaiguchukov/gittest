package kg.itacademy.stomservice.service;


import kg.itacademy.stomservice.models.UserAuthModel;
import kg.itacademy.stomservice.models.UserModel;

public interface UserService {
    String getToken(UserAuthModel userAuthDto);
    String createUser(UserModel userAuthDto);
}