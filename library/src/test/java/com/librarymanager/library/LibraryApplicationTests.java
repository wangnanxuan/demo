package com.librarymanager.library;

import com.librarymanager.library.mapper.BookMapper;
import com.librarymanager.library.mapper.UserMapper;
import com.librarymanager.library.pojo.Book;
import com.librarymanager.library.pojo.User;
import com.librarymanager.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

@SpringBootTest
class LibraryApplicationTests {
    @Autowired
    private BookMapper bookMapper;
    @Test
    void contextLoads() {

    }

}
