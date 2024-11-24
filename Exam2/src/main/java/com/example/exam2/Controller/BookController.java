package com.example.exam2.Controller;

import com.example.exam2.ApiResponse.ApiResponse;
import com.example.exam2.Model.Book;
import com.example.exam2.Model.User;
import com.example.exam2.Service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.AbstractFutureOrPresentInstantBasedValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/get")
    public ArrayList<Book> getBooks(){
        ArrayList<Book> books = bookService.getBooks();
        return books;
    }
    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody @Valid Book book , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        bookService.addBooks(book);
        return ResponseEntity.status(200).body(new ApiResponse("Book added"));
    }
    @PutMapping("/update/{ID}")
    public ResponseEntity updateBook(@PathVariable String ID,@RequestBody @Valid Book book, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (bookService.updateBook(ID,book)){
            return ResponseEntity.status(200).body(new ApiResponse("Book updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Book not exist"));
    }
    @DeleteMapping("/delete/{ID}")
    public ResponseEntity deleteBook(@PathVariable String ID){
        if (bookService.deleteBook(ID)){
            return ResponseEntity.status(200).body(new ApiResponse("Book Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Book not exist"));
    }

    @GetMapping("/searchByName/{name}")
    public ResponseEntity searchByName(@PathVariable String name){
        if (bookService.searchByName(name) != null){
            return ResponseEntity.status(200).body(bookService.searchByName(name));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Book not found"));
    }

    @GetMapping("/listBookByCategory/{category}")
    public ResponseEntity listBookByCategory(@PathVariable String category){
        if (bookService.listBookByCategory(category) != null){
            return ResponseEntity.status(200).body(bookService.listBookByCategory(category));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found any book by this category"));
    }

    @GetMapping("/listBookByNumberOfPage/{number_of_pages}")
    public ResponseEntity listBookByNumberOfPage(@PathVariable int number_of_pages){
        if (bookService.listBookByNumberOfPage(number_of_pages) != null){
            return ResponseEntity.status(200).body(bookService.listBookByNumberOfPage(number_of_pages));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found any book have this number of page or above"));
    }

    /*
    @PutMapping("/changeStatus/{ID}")
    public ResponseEntity changeStatus(@PathVariable String ID, @RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (bookService.changeStatus(ID,user)){
            return ResponseEntity.status(200).body(new ApiResponse("Book Change Status to unavailable"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Book not exist"));
    }
     */
    @PutMapping("/changeStatus/{ID}/{userID}")
    public ResponseEntity changeStatus(@PathVariable String ID, @PathVariable String userID){
        if (bookService.changeStatus(ID,userID) == 1){
            return ResponseEntity.status(200).body(new ApiResponse("Book Change Status to unavailable"));
        }else if(bookService.changeStatus(ID,userID) == 2){
            return ResponseEntity.status(400).body(new ApiResponse("user not found"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Book not exist"));
        }
    }

}
