package com.example.demo.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Book;
import com.example.demo.Entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
   
    List<Book> findByUser(User user);
    
   
    Optional<Book> findByIdAndUser(Long id, User user);
    
    List<Book> findByUserId(Long userId);  
    Optional<Book> findByIdAndUserId(Long id, Long userId);  
   
    
}
