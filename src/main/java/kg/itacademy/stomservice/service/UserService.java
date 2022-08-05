package kg.itacademy.stomservice.service;


import kg.itacademy.stomservice.model.UserAuthModel;
import kg.itacademy.stomservice.model.UserModel;

public interface UserService {
    String getToken(UserAuthModel userAuthDto);
    String createUser(UserModel userAuthDto);
}