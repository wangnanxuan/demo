package com.librarymanager.library.service.impl;

import com.librarymanager.library.mapper.BookMapper;
import com.librarymanager.library.pojo.Book;
import com.librarymanager.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> queryAllBooks() {
        return bookMapper.selectList(null);
    }

    @Override
    public Book queryBookById(int id) {
        return bookMapper.selectById(id);
    }

    @Override
    public int saveBook(Book book) {
        return bookMapper.insert(book);
    }

    @Override
    public int updateBookById(Book book) {
        return bookMapper.updateById(book);
    }

    @Override
    public int deleteBookById(int id) {
        return bookMapper.deleteById(id);
    }

}
