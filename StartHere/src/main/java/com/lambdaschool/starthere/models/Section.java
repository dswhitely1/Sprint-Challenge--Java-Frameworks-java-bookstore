//package com.lambdaschool.starthere.models;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//
//import javax.persistence.*;
//
//@ApiModel(value="Section", description = "Categories for Books")
//@Entity
//@Table(name="section")
//public class Section extends Auditable
//{
//    @ApiModelProperty(name = "sectionid", value = "Primary Key for Section", required = true, example = "1")
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long sectionid;
//
//    @ApiModelProperty(name="name", value="Section Name", required = true, example="Fiction")
//    @Column(nullable = false, unique = true)
//    private String name;
//
//    @OneToOne(mappedBy = "section")
//    @JsonIgnoreProperties("section")
//    private Book book;
//
//    public Section()
//    {
//    }
//
//    public Section(String name)
//    {
//        this.name = name;
//    }
//
//    public long getSectionid()
//    {
//        return sectionid;
//    }
//
//    public void setSectionid(long sectionid)
//    {
//        this.sectionid = sectionid;
//    }
//
//    public String getName()
//    {
//        return name;
//    }
//
//    public void setName(String name)
//    {
//        this.name = name;
//    }
//
//    public Book getBook()
//    {
//        return book;
//    }
//
//    public void setBook(Book book)
//    {
//        this.book = book;
//    }
//}
