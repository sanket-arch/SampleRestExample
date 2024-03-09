package com.api.book.bootrestbook.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookService;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    // fetching multiple data
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> bookList = this.bookService.getAllBooks();

        if (bookList.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(bookList));
    }

    // @GetMapping("/book/{id}")
    // public Book getBook(@PathVariable("id") int bookId) {
    // return this.bookService.getBookById(bookId);
    // }

    // fecthing single data
    @GetMapping("/book")
    public ResponseEntity<Book> getMethodName(@RequestParam int id) {
        Book book = this.bookService.getBookById(id);

        if (book == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }

    // adding new data
    @PostMapping("/book/add")
    public ResponseEntity<Book> postMethodName(@RequestBody Book book) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.bookService.postBook(book));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }

    }

    // deleting data
    @DeleteMapping("/book/remove")
    public ResponseEntity<Void> deleteBook(@RequestParam int bookId) {

        try {
            this.bookService.deleteBook(bookId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }

    // updating multiple data
    @PutMapping("/book/update")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @RequestParam int id) {
        Book updatedBook = this.bookService.updateBook(book, id);
        if (updatedBook == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(updatedBook));
    }
}
