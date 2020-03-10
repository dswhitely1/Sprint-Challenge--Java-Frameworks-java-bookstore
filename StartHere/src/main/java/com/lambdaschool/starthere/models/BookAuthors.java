package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@ApiModel(value="Book Authors", description = "Handles Book and Author mappings")
@Entity
@Table(name="bookauthors")
public class BookAuthors extends Auditable implements Serializable
{
    @ApiModelProperty(name="bookid", value = "Foreign Key for Book Table", required = true, example = "1")
    @Id
    @ManyToOne
    @JoinColumn(name = "bookid")
    @JsonIgnoreProperties("bookAuthors")
    private Book book;

    @ApiModelProperty(name="authorid", value="Foreign Key for Author Table", required = true, example = "1")
    @Id
    @ManyToOne
    @JoinColumn(name = "authorid")
    @JsonIgnoreProperties("bookAuthors")
    private Author author;

    public BookAuthors()
    {
    }

    public BookAuthors(Book book, Author author)
    {
        this.book = book;
        this.author = author;
    }

    public Book getBook()
    {
        return book;
    }

    public void setBook(Book book)
    {
        this.book = book;
    }

    public Author getAuthor()
    {
        return author;
    }

    public void setAuthor(Author author)
    {
        this.author = author;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof BookAuthors))
        {
            return false;
        }
        BookAuthors bookAuthors = (BookAuthors) o;
        return getBook().equals(bookAuthors.getBook()) && getAuthor().equals(bookAuthors.getAuthor());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getBook(), getAuthor());
    }
}
