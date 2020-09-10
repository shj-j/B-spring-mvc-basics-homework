package com.thoughtworks.capacity.gtb.mvc.repository;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.UserExistException;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    
    private static final Map<String, User> userMap = new HashMap<>();

    public void save(User user){

        if (userMap.containsKey(user.getUsername())){
            throw new UserExistException("用户已存在");
        }else{
            user.setId(userMap.size()+1);
            userMap.put(user.getUsername(), user);
        }

    }

    public User findUserByUserName(String userName) {
        return userMap.get(userName);
    }

}
