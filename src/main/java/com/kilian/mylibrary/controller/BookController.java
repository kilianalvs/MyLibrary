package com.kilian.mylibrary.controller;

import com.kilian.mylibrary.model.Book;
import com.kilian.mylibrary.repository.BookRepository;
import com.kilian.mylibrary.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    /**
     * Read - Get all books
     * @return - An Iterable object of book
     */
    @GetMapping("/books")
    public Iterable<Book> getBooks() {
        return bookService.findAllBooks();
    }

    /**
     * Read - Get a book with id
     * @param id - id of book
     * @return - A book
     */
    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable("id") Long id) {
        Optional<Book> book = bookService.findBookById(id);
        return book.orElse(null);
    }

    /**
     * Delete - Book with id
     * @param id - id of book
     */
    @DeleteMapping("/book/{id}")
    public void deleteBookById(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
    }

    /**
     * Create - Add a new book
     * @param book - An book object
     * @return - The book saved
     */
    @PostMapping("/book")
    public Book addBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    /**
     * Update - Update an existing book
     * @param id - The id of the book to update
     * @param book - The book object updated
     * @return - The book saved
     */
    @PutMapping("/book/{id}")
    public Book updateBook(@PathVariable("id") final Long id, @RequestBody Book book) {
        Optional<Book> e = bookRepository.findById(id);
        if(e.isPresent()) {
            Book currentBook = e.get();

            String title = book.getTitle();
            if (title != null) {
                currentBook.setTitle(title);
            }
            String author = book.getAuthor();
            if (author != null) {
                currentBook.setAuthor(author);
            }
            String description = book.getDescription();
            if (description != null) {
                currentBook.setDescription(description);
            }
            LocalDate publishedDate = book.getPublishedDate();
            if (publishedDate != null) {
                currentBook.setPublishedDate(publishedDate);
            }

            return bookService.saveBook(currentBook);

        } else {
            return null;
        }
    }
}
