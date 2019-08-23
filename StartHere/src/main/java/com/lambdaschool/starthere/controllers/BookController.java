package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.BookAuthors;
import com.lambdaschool.starthere.services.AuthorService;
import com.lambdaschool.starthere.services.BookService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @ApiOperation(value = "Returns a list of All Books",
                  response = Book.class,
                  responseContainer = "List")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping(value = "/allbooks",
                produces = {"application/json"})
    public ResponseEntity<?> listAllBooks(Pageable pageable, HttpServletRequest req)
    {
        logger.trace(req.getMethod()
                        .toUpperCase() + " " + req.getRequestURI() + " accessed.");
        List<Book> list = bookService.findAll(pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns a list of All Books based on Paging and Sorting",
                  response = Book.class,
                  responseContainer = "List")
    @ApiImplicitParams({@ApiImplicitParam(name = "page",
                                          dataType = "integer",
                                          paramType = "query",
                                          value = "Results page you want to retrieve(0..N)"), @ApiImplicitParam(name = "size",
                                                                                                                dataType = "integer",
                                                                                                                paramType = "query",
                                                                                                                value = "Number of Records per page"), @ApiImplicitParam(name = "sort",
                                                                                                                                                                         allowMultiple = true,
                                                                                                                                                                         dataType = "string",
                                                                                                                                                                         paramType = "query",

                                                                                                                                                                         value = "Sorting criteria in the format: property(,asc|desc).  Default sort order is ascending.  Multiple sort criteria are supported")})


    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping(value = "/books",
                produces = {"application/json"})
    public ResponseEntity<?> listAllBooksPaging(
            @PageableDefault(page = 0,
                             size = 5)
                    Pageable pageable, HttpServletRequest req)
    {
        logger.trace(req.getMethod()
                        .toUpperCase() + " " + req.getRequestURI() + " accessed.");
        List<Book> list = bookService.findAll(pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns a list of All Authors",
                  response = Author.class,
                  responseContainer = "List")
    @PreAuthorize("hasAnyRole('ROLE_USER') or hasAnyRole('ROLE_DATA') or hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/authors",
                produces = {"application/json"})
    public ResponseEntity<?> listAllAuthors(HttpServletRequest req)
    {
        logger.trace(req.getMethod()
                        .toUpperCase() + " " + req.getRequestURI() + " accessed.");
        List<Author> list = authorService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "Update the book with the provided book id",
                  response = Book.class)
    @PreAuthorize("hasAnyRole('ROLE_DATA') or hasAnyRole('ROLE_ADMIN')")
    @PutMapping(value = "/books/{id}",
                consumes = {"application/json"},
                produces = {"application/json"})
    public ResponseEntity<?> updateBookById(
            @RequestBody
                    Book book,
            @ApiParam(name = "Book Id",
                      required = true,
                      example = "1")
            @PathVariable
                    long id, HttpServletRequest req)
    {
        logger.trace(req.getMethod()
                        .toUpperCase() + " " + req.getRequestURI() + " accessed.");
        Book updateBook = bookService.update(book, id);
        return new ResponseEntity<>(updateBook, HttpStatus.OK);
    }

    @ApiOperation(value = "Adds an Author to the Provide Book Id",
                  notes = "The newly created student id will be sent in the location header",
                  response = void.class)
    @ApiResponses({@ApiResponse(code = 201,
                                message = "Student Created",
                                response = Book.class)})
    @PreAuthorize("hasAnyRole('ROLE_DATA') or hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/books/{bookid}/authors/{authorid}")

    public ResponseEntity<?> updateBook(
            @ApiParam(name = "Book Id",
                      required = true,
                      example = "1")
            @PathVariable
                    long bookid,
            @ApiParam(name = "Author Id",
                      required = true,
                      example = "1")
            @PathVariable
                    long authorid, HttpServletRequest req)
    {
        logger.trace(req.getMethod()
                        .toUpperCase() + " " + req.getRequestURI() + " accessed.");
        Author newAuthor = authorService.findAuthorById(authorid);
        Book currentBook = bookService.findBookById(bookid);
        List<BookAuthors> newAuthorList = currentBook.getBookAuthors();
        newAuthorList.add(new BookAuthors(currentBook, newAuthor));
        currentBook.setBookAuthors(newAuthorList);
        bookService.update(currentBook, bookid);
        return new ResponseEntity<>(currentBook, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a Book with the provided Book Id",
                  response = void.class)
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(
            @ApiParam(name = "Book Id",
                      required = true,
                      example = "1")
            @PathVariable
                    long id, HttpServletRequest req)
    {
        logger.trace(req.getMethod()
                        .toUpperCase() + " " + req.getRequestURI() + " accessed.");
        bookService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
