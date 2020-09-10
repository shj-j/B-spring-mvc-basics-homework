package com.thoughtworks.capacity.gtb.mvc.repository;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.UserExistException;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    
    private static final Map<String, User> userMap = new HashMap<>();

    public void save(User user){

        if (userMap.containsKey(user.getUserName())){
            throw new UserExistException();
        }else{
            userMap.put(user.getUserName(), user);
        }

    }

    public User findByUserName(String userName) {
        return userMap.get(userName);
    }

}
