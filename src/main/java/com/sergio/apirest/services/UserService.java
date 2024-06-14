package com.sergio.apirest.services;

import com.sergio.apirest.repositories.UserRepository;
import com.sergio.apirest.user.Role;
import com.sergio.apirest.user.User;
import com.sergio.apirest.user.userDto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }


    public UserDto createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Encripta la contraseña
        user.setEmail(userDto.getEmail());
        user.setRole(Role.USER);
        userRepository.save(user);
        return userDto;
    }

    @Transactional
    public UserDto updateUser(Integer userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Actualizar los campos del usuario
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());

        // Solo actualizar la contraseña si se proporciona una nueva
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            user.setPassword(userDto.getPassword());
        }

        userRepository.save(user);

        return userDto;
    }


}
