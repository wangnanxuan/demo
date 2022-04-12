package com.librarymanager.library.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.librarymanager.library.mapper.BookMapper;
import com.librarymanager.library.pojo.Book;
import com.librarymanager.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    /**
     * @param current 当前页
     * @param size    每页显示记录条数
     * @return 返回分页信息Map
     */
    @Override
    public Map<String, Object> bookPage(int current, int size) {
        Page<Book> bookPage = new Page<Book>(current, size);
        Page<Book> selectPage = bookMapper.selectPage(bookPage, null);
        HashMap<String, Object> bookPageMap = new HashMap<>(10);
        //当前页
        bookPageMap.put("current", selectPage.getCurrent());
        //每页显示数据集合
        bookPageMap.put("records", selectPage.getRecords());
        //每页显示记录条数
        bookPageMap.put("size", selectPage.getSize());
        //总记录条数
        bookPageMap.put("total", selectPage.getTotal());
        //总页面数
        bookPageMap.put("pages", selectPage.getPages());
        //下一页
        bookPageMap.put("next", selectPage.hasNext());
        //上一页
        bookPageMap.put("previous", selectPage.hasPrevious());


        return bookPageMap;
    }
}
