package com.example.exam2.Controller;

import com.example.exam2.ApiResponse.ApiResponse;
import com.example.exam2.Model.User;
import com.example.exam2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController1 {
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("user added"));
    }

    @GetMapping("/get")
    public ArrayList<User> getUsers(){
        return userService.getUsers();
    }

    @PutMapping("/updateUser/{ID}")
    public ResponseEntity updateUser(@PathVariable String ID,@RequestBody @Valid User user,Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (userService.updateUser(ID,user)){
            return ResponseEntity.status(200).body(new ApiResponse("user updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("user not exist"));
    }

    @DeleteMapping("/delete/{ID}")
    public ResponseEntity deleteUser(@PathVariable String ID){
        if (userService.deleteUser(ID)){
            return ResponseEntity.status(200).body(new ApiResponse("user deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("user not exist"));
    }
    @GetMapping("/listByBalance/{balance}")
    public ResponseEntity listByBalance(@PathVariable int balance){
        if (userService.listByBalance(balance) != null){
            return ResponseEntity.status(200).body(userService.listByBalance(balance));
        }
        return ResponseEntity.status(400).body(new ApiResponse("user by this balance or above not found"));
    }

    @GetMapping("/listByAge/{age}")
    public ResponseEntity listByAge(@PathVariable int age){
        if (userService.listByAge(age) != null){
            return ResponseEntity.status(200).body(userService.listByAge(age));
        }
        return ResponseEntity.status(400).body(new ApiResponse("user by this age or above not found"));
    }

}
