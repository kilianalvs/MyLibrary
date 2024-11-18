package com.kilian.mylibrary.service;

import com.kilian.mylibrary.model.Book;
import com.kilian.mylibrary.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<Book> findBookById(long bookId) {
        return bookRepository.findById(bookId);
    }

    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public void deleteBookById(final Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }
}
