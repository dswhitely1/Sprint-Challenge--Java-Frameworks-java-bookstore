package com.lambdaschool.starthere;

import com.lambdaschool.starthere.models.*;
import com.lambdaschool.starthere.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;





    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin", "password", admins);
        u1.getQuotes().add(new Quote("A creative man is motivated by the desire to achieve, not by the desire to beat others", u1));
        u1.getQuotes().add(new Quote("The question isn't who is going to let me; it's who is going to stop me.", u1));
        userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), r3));
        datas.add(new UserRoles(new User(), r2));
        User u2 = new User("cinnamon", "1234567", datas);
        userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("barnbarn", "ILuvM4th!", users);
        u3.getQuotes().add(new Quote("Live long and prosper", u3));
        u3.getQuotes().add(new Quote("The enemy of my enemy is the enemy I kill last", u3));
        u3.getQuotes().add(new Quote("Beam me up", u3));
        userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Bob", "password", users);
        userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("Jane", "password", users);
        userService.save(u5);

//        Section s1 = new Section("Fiction");
//        Section s2 = new Section("Technology");
//        Section s3 = new Section("Travel");
//        Section s4 = new Section("Business");
//        Section s5 = new Section("Religion");
//
//        sectionService.save(s1);
//        sectionService.save(s2);
//        sectionService.save(s3);
//        sectionService.save(s4);
//        sectionService.save(s5);
//
//        Author a1 = new Author("John", "Mitchell");
//        Author a2 = new Author("Dan", "Brown");
//        Author a3 = new Author("Jerry", "Poe");
//        Author a4 = new Author("Wells", "Teague");
//        Author a5 = new Author("George", "Gallinger");
//        Author a6 = new Author("Ian", "Stewart");
//
//        authorService.save(a1);
//        authorService.save(a2);
//        authorService.save(a3);
//        authorService.save(a4);
//        authorService.save(a5);
//        authorService.save(a6);
//
//        ArrayList<Wrote> author6 = new ArrayList<>();
//        author6.add(new Wrote(new Book(), a6));
//        Book b1 = new Book("Flatterland", "9870738206752", "2001", s1, author6);
//        bookService.save(b1);
//
//        ArrayList<Wrote> author2 = new ArrayList<>();
//        author2.add(new Wrote(new Book(),a2));
//        Book b2 = new Book("Digital FOrtess", "9788489367012", "2007", s1, author2);
//        Book b3 = new Book("The Da Vinci Code", "9780307474278", "2009", s1, author2);
//        bookService.save(b2);
//        bookService.save(b3);
//
//        ArrayList<Wrote> author5 = new ArrayList<>();
//        author5.add(new Wrote(new Book(), a5));
//        author5.add(new Wrote(new Book(), a3));
//        Book b4 = new Book("Essentials of Finance", "1314241651234", null, s4,author5);
//        bookService.save(b4);
//
//        ArrayList<Wrote> author4 = new ArrayList<>();
//        author4.add(new Wrote(new Book(), a4));
//        Book b5 = new Book("Calling Texas Home", "1885171382134", "2000", s3, author4);
    }
}