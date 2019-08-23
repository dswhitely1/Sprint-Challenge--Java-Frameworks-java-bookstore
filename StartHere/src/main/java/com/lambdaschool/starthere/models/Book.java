package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Book", description = "The Book Entity")
@Entity
@Table(name="book")
public class Book extends Auditable
{
    @ApiModelProperty(name = "bookid", value="Primary Key for Books", required = true, example="1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;

    @ApiModelProperty(name="title", value="Book Title", required = true, example = "Java for Dummies")
    @Column(nullable = false)
    private String title;

    @ApiModelProperty(name="ISBN", value="ISBN Number of the book", required = true, example = "9780738206752")
    @Column(nullable = false)
    private String ISBN;

    @ApiModelProperty(name="copy", value="Book copyright year", required = true, example="1976")
    private String copy;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("book")
    private List<BookAuthors> bookAuthors = new ArrayList<>();

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(nullable=false,name = "sectionid")
//    @JsonIgnoreProperties("book")
//    private Section section;

    public Book()
    {
    }

    public Book(String title, String ISBN, String copy, List<BookAuthors> bookAuthors)
    {
        this.title = title;
        this.ISBN = ISBN;
        this.copy = copy;
//        this.section = section;
        for (BookAuthors w: bookAuthors)
        {
            w.setBook(this);
        }
        this.bookAuthors = bookAuthors;
    }

    public long getBookid()
    {
        return bookid;
    }

    public void setBookid(long bookid)
    {
        this.bookid = bookid;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getISBN()
    {
        return ISBN;
    }

    public void setISBN(String ISBN)
    {
        this.ISBN = ISBN;
    }

    public String getCopy()
    {
        return copy;
    }

    public void setCopy(String copy)
    {
        this.copy = copy;
    }

    public List<BookAuthors> getBookAuthors()
    {
        return bookAuthors;
    }

    public void setBookAuthors(List<BookAuthors> bookAuthors)
    {
        this.bookAuthors = bookAuthors;
    }

//    public Section getSection()
//    {
//        return section;
//    }
//
//    public void setSection(Section section)
//    {
//        this.section = section;
//    }
}
