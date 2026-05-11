package com.example.demo.Controller;


import com.example.demo.Entity.Book;
import com.example.demo.Entity.User;
import com.example.demo.Service.BookService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    
    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }
    
    
    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/";
        
        bookService.addBook(book, user);  
        return "redirect:/books/list";
    }
    
    // shog book
    @GetMapping("/list")
    public String showBookList(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/";
        
        List<Book> books = bookService.getBooksByUser(user);
        model.addAttribute("books", books);
        return "book-list";
    }
    
    // book details
    @GetMapping("/details/{id}")
    public String showBookDetails(@PathVariable Long id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/";
        
        try {
            Book book = bookService.getBookByIdAndUser(id, user);
            model.addAttribute("book", book);
            return "book-details";
        } catch (Exception e) {
            return "redirect:/books/list";
        }
    }
    
      
 
    @GetMapping("/update/{id}")
    public String showUpdateBookForm(@PathVariable Long id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/";
        
        try {
            Book book = bookService.getBookByIdAndUser(id, user);
            model.addAttribute("book", book);
            return "update-book";
        } catch (Exception e) {
            return "redirect:/books/list";
        }
    }
  
   //Update
    @PostMapping("/update")
    public String updateBook(@RequestParam Long id,
                             @RequestParam String title,
                             @RequestParam String author,
                             @RequestParam String description,
                             HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/";
        
        Book updatedBook = new Book();
        updatedBook.setTitle(title);
        updatedBook.setAuthor(author);
        updatedBook.setDescription(description);
        
        bookService.updateBook(id, user, updatedBook);
        return "redirect:/books/list";
    }
    
    
}