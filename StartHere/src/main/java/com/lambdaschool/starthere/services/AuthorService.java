package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService
{
    List<Author> findAll();

    Author findAuthorById(long id);

    Author save(Author author);

    Author update(Author author, long id);

    void delete(long id);
}
