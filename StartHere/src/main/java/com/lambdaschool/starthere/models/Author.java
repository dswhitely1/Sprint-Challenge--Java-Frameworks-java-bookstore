package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value="Author", description = "The writers of Said Books")
@Entity
@Table(name="author")
public class Author extends Auditable
{
    @ApiModelProperty(name="authorid", value="Primary Key for Author Table", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;

    @ApiModelProperty(name="fname", value="First Name of Author", required = true, example="Skyelar")
    @Column(nullable = false)
    private String fname;

    @ApiModelProperty(name="lname", value="Last Name of Author", required = true, example="Carroll")
    @Column(nullable = false)
    private String lname;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("author")
    private List<BookAuthors> bookAuthors = new ArrayList<>();

    public Author()
    {
    }

    public Author(String fname, String lname)
    {
        this.fname = fname;
        this.lname = lname;
    }

    public long getAuthorid()
    {
        return authorid;
    }

    public void setAuthorid(long authorid)
    {
        this.authorid = authorid;
    }

    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public String getLname()
    {
        return lname;
    }

    public void setLname(String lname)
    {
        this.lname = lname;
    }

    public List<BookAuthors> getBookAuthors()
    {
        return bookAuthors;
    }

    public void setBookAuthors(List<BookAuthors> bookAuthors)
    {
        this.bookAuthors = bookAuthors;
    }
}
