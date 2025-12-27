package com.labmanager.users.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.labmanager.users.entity.User;
import com.labmanager.users.service.UsersService;


@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    //@GetMapping({"", "/"})

    @GetMapping("/login")  
    // se prijavi uporabnik (preveri email - pswd)
    public ResponseEntity<Boolean> login(@RequestBody Map<String, String> body) {
        boolean ok = usersService.checkLogin(body.get("email"), body.get("password"));
        return ResponseEntity.ok(ok);
    }

    @GetMapping("/register")  
    public ResponseEntity<Boolean> register(@RequestBody Map<String, String> body) {
        boolean added = usersService.addUser(body.get("name"), body.get("email"), body.get("password"), body.get("code1"), body.get("code2"));
        return ResponseEntity.ok(added);
    }

    

    //@GetMapping("/profile") 
    // vidis profil + projekte
    // lahko nastavi ostale stvari profila (pozicija) 
    // if admin: can change employee position

    
}
