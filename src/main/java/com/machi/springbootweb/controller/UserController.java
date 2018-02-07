package com.machi.springbootweb.controller;

import com.machi.springbootweb.model.User;
import com.machi.springbootweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dell on 2018/2/7.
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired private UserRepository userRepository;

    @PostMapping(value = "findByName")
    public User findByName(@RequestParam(value = "name") String name){

        User user = userRepository.findByName(name);

        return user;

    }

    @GetMapping(value = "findAll")
    public List<User> findAll(){
        List<User> users = userRepository.findAll();
        return users;
    }

}
