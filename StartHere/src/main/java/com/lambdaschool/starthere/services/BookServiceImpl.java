package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.BookAuthors;
import com.lambdaschool.starthere.repository.AuthorRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "bookService")
public class BookServiceImpl implements BookService
{
    @Autowired
    BookRepository bookRepo;

    @Autowired
    AuthorRepository authorRepo;

    @Override
    public List<Book> findAll(Pageable pageable)
    {
        List<Book> list = new ArrayList<>();
        bookRepo.findAll(pageable)
                  .iterator()
                  .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Book findBookById(long id) throws ResourceNotFoundException
    {
        return bookRepo.findById(id)
                         .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Transactional
    @Modifying
    @Override
    public Book save(Book book)
    {
        Book newBook = new Book();
        newBook.setTitle(book.getTitle());
        newBook.setISBN(book.getISBN());
        newBook.setCopy(book.getCopy());
//        newBook.setSection(book.getSection());
        ArrayList<BookAuthors> newBookAuthors = new ArrayList<>();
        for (BookAuthors w: book.getBookAuthors())
        {
            newBookAuthors.add(new BookAuthors(newBook, w.getAuthor()));
        }
        newBook.setBookAuthors(newBookAuthors);
        return bookRepo.save(newBook);
    }

    @Transactional
    @Modifying
    @Override
    public Book update(Book book, long id) throws ResourceNotFoundException
    {
        Book currentBook = bookRepo.findById(id)
                                         .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
        if (book.getTitle()!=null)
        {
            currentBook.setTitle(book.getTitle());
        }
        if (book.getISBN()!=null)
        {
            currentBook.setISBN(book.getISBN());
        }
        if (currentBook.getCopy() != book.getCopy())
        {
            currentBook.setCopy(book.getCopy());
        }
//        if (!book.getSection().equals(currentBook.getSection()))
//        {
//            currentBook.setSection(book.getSection());
//        }
        if (book.getBookAuthors().size()>0)
        {
            authorRepo.deleteBookAuthorsByBookId(currentBook.getBookid());

            for (BookAuthors w: book.getBookAuthors())
            {
                authorRepo.insertWrote(id, w.getAuthor().getAuthorid());
            }
        }
        return bookRepo.save(currentBook);
    }

    @Transactional
    @Modifying
    @Override
    public void delete(long id) throws ResourceNotFoundException
    {
        if (bookRepo.findById(id)
                      .isPresent())
        {
            bookRepo.deleteById(id);
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }
}
