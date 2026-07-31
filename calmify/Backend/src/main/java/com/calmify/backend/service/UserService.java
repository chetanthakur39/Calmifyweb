package com.calmify.backend.service;
import com.calmify.backend.dto.LoginDto;
import com.calmify.backend.dto.UserDto;
//import com.calmify.backend.entity.User;
import com.calmify.backend.dto.UserResponseDto;
import com.calmify.backend.dto.LoginResponseDto;
import com.calmify.backend.dto.ProfileResponseDto;
import com.calmify.backend.dto.UpdateProfileDto;


public interface UserService {

    UserResponseDto registerUser(UserDto userDto);

    LoginResponseDto loginUser(LoginDto loginDto);
    ProfileResponseDto getProfile(String email);
    ProfileResponseDto updateProfile(
        String email,
        UpdateProfileDto updateProfileDto
);
}