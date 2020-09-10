package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.UserNotFoundException;
import com.thoughtworks.capacity.gtb.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class UserService {

    private final UserRepository userRepository = new UserRepository();

    public void register(User user){
        userRepository.save(user);
    }

    public User login(String username, String password) throws AuthenticationException {
        User user = userRepository.findUserByUserName(username);
        if (user == null){
            throw new UserNotFoundException("用户不存在");
        }else if ( ! user.getPassword().equals(password)){
            throw new AuthenticationException("用户名或密码错误");
        }
        return user;

    }
}
