package org.example.e1;

import org.springframework.stereotype.Service;

@Service
public class SomeService {

    @CustomWrapper
    public UserDto login(UserDto userDto) {
        System.out.println("7 - SomeService#login() - BEGIN");
        System.out.println("    " + userDto);
        userDto.setLogin(userDto.getLogin() + "_LOGIN_MUTATION");
        userDto.setPassword(userDto.getPassword() + "_LOGIN_MUTATION");
        System.out.println("8 - SomeService#login() - END");
        return userDto;
    }

    @Log
    public int textLength(String text) {
        System.out.println("SomeService#textLength()");
        return text.length();
    }

}
