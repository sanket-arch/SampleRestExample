package com.api.book.bootrestbook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.book.bootrestbook.dao.BookRepository;
import com.api.book.bootrestbook.entities.Book;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks() {

        return (List<Book>) this.bookRepository.findAll();
    }

    public Book getBookById(int bookId) {
        Book bookFound = null;
        try {
            bookFound = this.bookRepository.findById(bookId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookFound;
    }

    public Book postBook(Book book) {

        return this.bookRepository.save(book);
    }

    public void deleteBook(int bookId) {

        this.bookRepository.deleteById(bookId);

    }

    public Book updateBook(Book book, int id) {
        Book updatedBook = null;
        try {
            book.setId(id);
            updatedBook = this.bookRepository.save(book);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return updatedBook;

    }

}
