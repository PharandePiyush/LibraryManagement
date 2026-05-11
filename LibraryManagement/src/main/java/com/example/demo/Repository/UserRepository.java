package com.example.demo.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
  
    Optional<User> findByUsernameAndPassword(String username, String password);
   
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);  

}
