package com.librarymanager.library;

import com.librarymanager.library.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LibraryApplicationTests {
    @Autowired
    private BookMapper bookMapper;
    @Test
    void contextLoads() {

    }

}
