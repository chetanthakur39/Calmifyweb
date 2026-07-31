package com.calmify.backend.controller;

//import com.calmify.backend.entity.User;
import com.calmify.backend.service.UserService;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.calmify.backend.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import com.calmify.backend.dto.LoginDto;
import com.calmify.backend.dto.LoginResponseDto;
import com.calmify.backend.dto.UserResponseDto;
import com.calmify.backend.dto.ProfileResponseDto;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
//import com.calmify.backend.dto.ProfileResponseDto;
import com.calmify.backend.dto.UpdateProfileDto;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.security.core.Authentication;
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/users/register")
public UserResponseDto registerUser(@Valid @RequestBody UserDto userDto) {
    return userService.registerUser(userDto);
}

@PostMapping("/api/users/login")
public LoginResponseDto loginUser(@RequestBody LoginDto loginDto) {
    return userService.loginUser(loginDto);
}

@GetMapping("/api/users/profile")
public ProfileResponseDto getProfile(Authentication authentication) {

    String email = authentication.getName();

    return userService.getProfile(email);
}
@PutMapping("/api/users/profile")
public ProfileResponseDto updateProfile(
        Authentication authentication,
        @RequestBody UpdateProfileDto updateProfileDto) {

    String email = authentication.getName();

    return userService.updateProfile(
            email,
            updateProfileDto
    );
}
}
