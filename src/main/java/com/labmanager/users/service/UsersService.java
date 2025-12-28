package com.labmanager.users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.labmanager.users.entity.User;
import com.labmanager.users.entity.Codes;
import com.labmanager.users.repository.UserRepository;

@Service
public class UsersService {
    private final UserRepository userRepo;

    public UsersService(UserRepository userRepository) {
        this.userRepo = userRepository;
    }


    // Get all users
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // Get user by id
    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    // Add/create user
    public boolean addUser(String name, String email, String pswd, String position, String code1, String code2){
        Codes codes = new Codes();
        if(!codes.checkCode1(code1)) return false;
        String role = codes.getRole(code2);
        if(role == null) return false;

        // add user to db
        User user = new User(name, email, pswd, role, position);
        userRepo.save(user);
        return true;
    }
    

    // Check login by email and password (plain-text comparison)
    public boolean checkLogin(String email, String password){
        if (email == null || password == null) return false;
        User user = userRepo.findByEmail(email);
        if (user == null) return false;
        return password.equals(user.getPassword());
    }

    // Delete user by id
    public boolean deleteUser(Long id) {
        if (id == null) return false;
        if (!userRepo.existsById(id)) return false;
        userRepo.deleteById(id);
        return true;
    }

}
