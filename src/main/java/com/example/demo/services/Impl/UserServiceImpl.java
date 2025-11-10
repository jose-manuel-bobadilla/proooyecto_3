package com.example.demo.services.Impl;

import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public User save(User user) {
        // En un proyecto real, aquí encriptarías la contraseña antes de guardar
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Usuario no encontrado con ID: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public User update(Long id, User userDetails) {
        User user = findById(id); // Reutiliza el método para verificar si existe

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        // Por seguridad, la contraseña no se actualiza directamente.
        // Se suele hacer en un endpoint aparte "/change-password".

        return userRepository.save(user);
    }
}