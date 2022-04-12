package com.librarymanager.library;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.librarymanager.library.mapper.BookMapper;
import com.librarymanager.library.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class LibraryApplicationTests {
    @Autowired
    private BookMapper bookMapper;
    @Test
    void contextLoads() {
        //1 创建page对象
        //传入两个参数：当前页 和 每页显示记录数
        Page<Book> page = new Page<Book>(1,5);
        //调用mp分页查询的方法
        //调用mp分页查询过程中，底层封装
        //把分页所有数据封装到page对象里面
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<Book>();
        bookMapper.selectPage(page,bookQueryWrapper);
        //通过page对象获取分页数据
        System.out.println(page.getCurrent());//当前页
        System.out.println(page.getRecords());//每页数据list集合
        System.out.println(page.getSize());//每页显示记录数
        System.out.println(page.getTotal()); //总记录数
        System.out.println(page.getPages()); //总页数
        System.out.println(page.hasNext()); //下一页
        System.out.println(page.hasPrevious()); //上一页

//        for (int i = 0; i < 100; i++) {
//            String name = (UUID.randomUUID().toString().trim().substring(1, 5));
//            bookMapper.insert(new Book(null,name,i+"","test","img","img"+i,null,null,null,null));
//        }
    }

}
