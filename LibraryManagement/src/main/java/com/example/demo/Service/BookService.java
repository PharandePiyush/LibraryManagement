package com.example.demo.Service;


import com.example.demo.Entity.Book;
import com.example.demo.Entity.User;
import com.example.demo.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    // Aad book
    public Book addBook(Book book, User user) {
        book.setUser(user);  
        return bookRepository.save(book);
    }
    

    public List<Book> getBooksByUser(User user) {
        return bookRepository.findByUser(user);  
    }
    
    
    public Book getBookByIdAndUser(Long bookId, User user) {
        return bookRepository.findByIdAndUser(bookId, user)
            .orElseThrow(() -> new RuntimeException("Book not found or access denied"));
    }
    
    // Update book
    public Book updateBook(Long bookId, User user, Book updatedBook) {
        Book existingBook = getBookByIdAndUser(bookId, user);
        
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setDescription(updatedBook.getDescription());
        
        return bookRepository.save(existingBook);
    }
    

}