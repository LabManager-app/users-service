package com.labmanager.users.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.labmanager.users.entity.User;
import com.labmanager.users.service.UsersService;


@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<User>> getAll() {
        List<User> users = usersService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/login")  
    // se prijavi uporabnik (preveri email - pswd)
    public ResponseEntity<Boolean> login(@RequestBody Map<String, String> body) {
        boolean ok = usersService.checkLogin(body.get("email"), body.get("password"));
        return ResponseEntity.ok(ok);
    }

    @PostMapping("/register")  
    public ResponseEntity<Boolean> register(@RequestBody Map<String, String> body) {
        boolean added = usersService.addUser(body.get("name"), body.get("email"), body.get("password"), body.get("position"), body.get("code1"), body.get("code2"));
        return ResponseEntity.ok(added);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> removeUser(@PathVariable Long id) {
        boolean removed = usersService.deleteUser(id);
        return ResponseEntity.ok(removed);
    }

    

    //@GetMapping("/profile") 
    // vidis profil + projekte
    // lahko nastavi ostale stvari profila (pozicija) 
    // if admin: can change employee position

    
}
