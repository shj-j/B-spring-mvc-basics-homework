package com.thoughtworks.capacity.gtb.mvc.controller;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RestController
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid User user){
        userService.register(user);
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public User login(@RequestParam(name = "username")
                          @NotBlank(message = "用户名不为空")
                          @Pattern(regexp = "^[a-zA-Z0-9_]{3,10}$", message = "用户名不合法")
                                  String username,
                      @RequestParam(name = "password")
                              @NotBlank(message = "密码不能为空")
                              @Size(min = 5, max = 12, message = "密码不合法")
                              String password) throws AuthenticationException {

        return userService.login(username, password);
    }

}
