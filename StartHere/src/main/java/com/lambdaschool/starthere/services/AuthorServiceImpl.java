package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService
{
    @Autowired
    AuthorRepository authorRepo;

    @Override
    public List<Author> findAll(Pageable pageable)
    {
        List<Author> list = new ArrayList<>();
        authorRepo.findAll()
                  .iterator()
                  .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Author findAuthorById(long id) throws ResourceNotFoundException
    {
        return authorRepo.findById(id)
                         .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Transactional
    @Modifying
    @Override
    public Author save(Author author)
    {
        Author newAuthor = new Author();
        newAuthor.setFname(author.getFname());
        newAuthor.setLname(author.getLname());
        return newAuthor;
    }

    @Transactional
    @Modifying
    @Override
    public Author update(Author author, long id) throws ResourceNotFoundException
    {
        Author currentAuthor = authorRepo.findById(id)
                                         .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
        if (author.getFname() != null)
        {
            currentAuthor.setFname(author.getFname());
        }
        if (author.getLname() != null)
        {
            currentAuthor.setLname(author.getLname());
        }
        return authorRepo.save(currentAuthor);
    }

    @Transactional
    @Modifying
    @Override
    public void delete(long id) throws ResourceNotFoundException
    {
        if (authorRepo.findById(id)
                      .isPresent())
        {
            authorRepo.deleteById(id);
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }
}
