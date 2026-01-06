package com.labmanager.users.controller;

import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

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

// Swagger API dokumentacija: http://localhost:8081/swagger-ui/index.html#/

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "API za upravljanje uporabnikov")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @Operation(summary = "Pridobi vse uporabnike", description = "Vrne seznam vseh uporabnikov v sistemu")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Seznam uporabnikov vrnjen")
    })
    @GetMapping({"", "/"})
    public ResponseEntity<List<User>> getAll() {
        List<User> users = usersService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Prijava uporabnika", description = "Preveri elektronski naslov in geslo ter vrne uporabnika ob uspehu")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Prijava uspešna"),
        @ApiResponse(responseCode = "401", description = "Neuspešna prijava")
    })
    @PostMapping("/login")  
    // se prijavi uporabnik (preveri email - pswd)
    public ResponseEntity<User> login(@RequestBody Map<String, String> body) {
        User user = usersService.checkLogin(body.get("email"), body.get("password"));
        if (user != null) return ResponseEntity.ok(user);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @Operation(summary = "Registracija uporabnika", description = "Ustvari novega uporabnika z osnovnimi podatki")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Uporabnik ustvarjen"),
        @ApiResponse(responseCode = "400", description = "Neveljaven zahtevek")
    })
    @PostMapping("/register")  
    public ResponseEntity<User> register(@RequestBody Map<String, String> body) {
        User created = usersService.addUser(body.get("name"), body.get("email"), body.get("password"), body.get("position"), body.get("code1"), body.get("code2"));
        if (created != null) return ResponseEntity.status(HttpStatus.CREATED).body(created);
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Odstrani uporabnika", description = "Izbriše uporabnika po ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "True če izbrisan, drugače false")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> removeUser(@Parameter(description = "ID uporabnika") @PathVariable("id") Long id) {
        boolean removed = usersService.deleteUser(id);
        return ResponseEntity.ok(removed);
    }

    

    //@GetMapping("/profile") 
    // vidis profil + projekte
    // lahko nastavi ostale stvari profila (pozicija) 
    // if admin: can change employee position

    
}
