package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.BookAuthors;
import com.lambdaschool.starthere.services.AuthorService;
import com.lambdaschool.starthere.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/data")
public class BookController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    BookService bookService;
    @Autowired
    AuthorService authorService;

    @GetMapping("/books")
    public ResponseEntity<?> listAllBooks(Pageable pageable, HttpServletRequest req)
    {
        logger.trace(req.getMethod()
                        .toUpperCase() + " " + req.getRequestURI() + " accessed.");
        List<Book> list = bookService.findAll(pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/authors")
    public ResponseEntity<?> listAllAuthors(Pageable pageable, HttpServletRequest req)
    {
        logger.trace(req.getMethod()
                        .toUpperCase() + " " + req.getRequestURI() + " accessed.");
        List<Author> list = authorService.findAll(pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBookById(@RequestBody Book book, @PathVariable long id)
    {
        Book updateBook = bookService.update(book, id);
        return new ResponseEntity<>(updateBook, HttpStatus.OK);
    }

    @PostMapping("/books/{bookid}/authors/{authorid}")
    public ResponseEntity<?> updateBook(@PathVariable long bookid, @PathVariable long authorid)
    {
        Author newAuthor = authorService.findAuthorById(authorid);
        Book currentBook = bookService.findBookById(bookid);
        List<BookAuthors> newAuthorList = currentBook.getBookAuthors();
        newAuthorList.add(new BookAuthors(currentBook, newAuthor));
        currentBook.setBookAuthors(newAuthorList);
        bookService.update(currentBook, bookid);
        return new ResponseEntity<>(currentBook, HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id)
    {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
