package com.calmify.backend.service.impl;
import com.calmify.backend.entity.User;
import com.calmify.backend.service.UserService;
import org.springframework.stereotype.Service;
import com.calmify.backend.repository.UserRepository;
import com.calmify.backend.dto.UserDto;
import com.calmify.backend.exception.UserAlreadyExistsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.calmify.backend.dto.LoginDto;
import com.calmify.backend.exception.InvalidCredentialsException;
import com.calmify.backend.dto.UserResponseDto;
import com.calmify.backend.dto.LoginResponseDto;
import com.calmify.backend.service.JwtService;
import com.calmify.backend.dto.ProfileResponseDto;
import com.calmify.backend.dto.UpdateProfileDto;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
public UserServiceImpl(
        UserRepository userRepository,
        BCryptPasswordEncoder passwordEncoder,
        JwtService jwtService) {

    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
}

    @Override
public UserResponseDto registerUser(UserDto userDto) {

    if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
        throw new UserAlreadyExistsException("Email already exists");
    }

    User user = new User();

    user.setName(userDto.getName());
    user.setEmail(userDto.getEmail());
    user.setPassword(
            passwordEncoder.encode(userDto.getPassword())
    );
    user.setAge(userDto.getAge());
    user.setGender(userDto.getGender());
    user.setPhone(userDto.getPhone());
    user.setCountry(userDto.getCountry());

   User savedUser = userRepository.save(user);

UserResponseDto response = new UserResponseDto();
response.setId(savedUser.getId());
response.setName(savedUser.getName());
response.setEmail(savedUser.getEmail());

return response;
}

@Override
public LoginResponseDto loginUser(LoginDto loginDto) {

    User user = userRepository
            .findByEmail(loginDto.getEmail())
            .orElseThrow(() ->
                    new InvalidCredentialsException("Invalid Email or Password"));

    boolean isPasswordMatched = passwordEncoder.matches(
            loginDto.getPassword(),
            user.getPassword()
    );

   

    if (!isPasswordMatched) {
        throw new InvalidCredentialsException("Invalid Email or Password");
    }

    

String token = jwtService.generateToken(user.getEmail());
LoginResponseDto response = new LoginResponseDto();


response.setId(user.getId()); 
response.setToken(token);
response.setName(user.getName());
response.setEmail(user.getEmail());

return response;
}
@Override
public ProfileResponseDto getProfile(String email) {

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User Not Found"));

    ProfileResponseDto response = new ProfileResponseDto();

    response.setName(user.getName());
    response.setEmail(user.getEmail());
    response.setAge(user.getAge());
    response.setGender(user.getGender());
    response.setPhone(user.getPhone());
    response.setCountry(user.getCountry());

    return response;
}

@Override
public ProfileResponseDto updateProfile(
        String email,
        UpdateProfileDto updateProfileDto) {

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    user.setName(updateProfileDto.getName());
    user.setAge(updateProfileDto.getAge());
    user.setGender(updateProfileDto.getGender());
    user.setPhone(updateProfileDto.getPhone());
    user.setCountry(updateProfileDto.getCountry());

    User updatedUser = userRepository.save(user);

    ProfileResponseDto response = new ProfileResponseDto();

    response.setName(updatedUser.getName());
    response.setEmail(updatedUser.getEmail());
    response.setAge(updatedUser.getAge());
    response.setGender(updatedUser.getGender());
    response.setPhone(updatedUser.getPhone());
    response.setCountry(updatedUser.getCountry());

    return response;
}

}     