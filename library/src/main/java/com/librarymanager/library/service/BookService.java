package com.librarymanager.library.service;

import com.librarymanager.library.pojo.Book;

import java.util.List;

public interface BookService {
    /**
     *
     * @return 查询所有的图书
     */
    List<Book> queryAllBooks();

    Book queryBookById(Long id);

    int saveBook(Book book);

    int updateBookById(Book book);

    int deleteBookById(Long id);
}
