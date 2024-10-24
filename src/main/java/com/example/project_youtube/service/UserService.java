package com.example.project_youtube.service;

import com.example.project_youtube.dtos.reponses.UserResponse;
import com.example.project_youtube.mapper.UserMapper;
import com.example.project_youtube.dtos.requests.UserCreationRequest;
import com.example.project_youtube.dtos.requests.UserUpdateRequest;
import com.example.project_youtube.entity.User;
import com.example.project_youtube.exception.AppException;
import com.example.project_youtube.exception.ErrorCode;
import com.example.project_youtube.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse createUser(UserCreationRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);
        //mã hóa password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUserById(String id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() -> new RuntimeException(("User Not Found"))));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(("User Not Found")));
        userMapper.updateUser(existingUser,request);
        return userMapper.toUserResponse(userRepository.save(existingUser));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
