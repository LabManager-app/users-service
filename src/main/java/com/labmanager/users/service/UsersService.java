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

    // Add/create user - return created User or null on failure
    public User addUser(String name, String email, String pswd, String position, String code1, String code2){
        Codes codes = new Codes();
        if(!codes.checkCode1(code1)) return null;
        String role = codes.getRole(code2);
        if(role == null) return null;

        // add user to db
        User user = new User(name, email, pswd, role, position);
        return userRepo.save(user);
    }
    

    // Check login by email and password (plain-text comparison) - return User if OK, otherwise null
    public User checkLogin(String email, String password){
        if (email == null || password == null) return null;
        User user = userRepo.findByEmail(email);
        if (user == null) return null;
        return password.equals(user.getPassword()) ? user : null;
    }

    // Delete user by id
    public boolean deleteUser(Long id) {
        if (id == null) return false;
        if (!userRepo.existsById(id)) return false;
        userRepo.deleteById(id);
        return true;
    }

}
