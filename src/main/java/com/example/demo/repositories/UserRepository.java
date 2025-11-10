package com.example.demo.repositories;


import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Spring Data JPA automáticamente crea una consulta para buscar un usuario
     * por su columna 'email'. Es útil para logins y para evitar duplicados.
     * Devuelve un Optional porque el usuario podría no existir.
     */
    Optional<User> findByEmail(String email);

}