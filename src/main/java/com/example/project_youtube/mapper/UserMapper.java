package com.example.project_youtube.mapper;

import com.example.project_youtube.dtos.reponses.UserResponse;
import com.example.project_youtube.dtos.requests.UserCreationRequest;
import com.example.project_youtube.dtos.requests.UserUpdateRequest;
import com.example.project_youtube.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

//    @Mapping(source="password",target="lastName", ignore = true)
    UserResponse toUserResponse(User user);
}
