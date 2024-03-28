package com.dogfood.authcenter.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * mock
 * @author zhongfupeng
 */
@Service
public class UserService {

    private Map<String, String> tokens = new HashMap<>();

    public User parse(String token) {
        String username = tokens.get(token);
        if (username == null) {
            return null;
        } else {
            User user = new User();
            user.setUsername(username);
            return user;
        }
    }

    public void add(String token, String username) {
        tokens.put(token, username);
    }
}
