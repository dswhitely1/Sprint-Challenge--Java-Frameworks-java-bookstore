package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Author;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AuthorRepository extends CrudRepository<Author, Long>
{
    @Transactional
    @Modifying
    @Query(value = "DELETE from bookAuthors where bookid = :bookid")
    void deleteBookAuthorsByBookId(long bookid);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO bookAuthors(bookid, authorid) values(:bookid, :authorid)", nativeQuery = true)
    void insertWrote(long bookid, long authorid);
}
