package com.example.project_youtube.controller;

import com.example.project_youtube.dtos.reponses.APIResponse;
import com.example.project_youtube.dtos.reponses.UserResponse;
import com.example.project_youtube.dtos.requests.UserCreationRequest;
import com.example.project_youtube.dtos.requests.UserUpdateRequest;
import com.example.project_youtube.exception.NumberErrorException;
import com.example.project_youtube.service.UserService;
import com.example.project_youtube.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping
//    public ResponseEntity<?> createUser(@RequestBody @Valid UserCreationRequest request, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            List<String> errors = bindingResult.getFieldErrors()
//                    .stream().map(FieldError::getDefaultMessage).toList();
//            return ResponseEntity.badRequest().body(errors);
//        }
//        userService.createUser(request);
//        return ResponseEntity.ok().body("Save successfully");
//    }
    
    @PostMapping
    public APIResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request){
        return APIResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping
    public List<User> getUsers() throws NumberErrorException {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public UserResponse getUserById(@PathVariable String userId){
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId){
         userService.deleteUser(userId);
         return ResponseEntity.ok("User deleted successfully");
    }
}
