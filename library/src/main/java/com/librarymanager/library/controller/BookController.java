package com.librarymanager.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.librarymanager.library.mapper.BookMapper;
import com.librarymanager.library.pojo.Book;
import com.librarymanager.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/book")
public class BookController {
    String realPath = getRealPath();
    String imgPath = null;

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookService bookService;


    @RequestMapping("/toBookTables")
    public String toBookTables(HttpSession session, @RequestParam(value = "pageNumb", defaultValue = "1") String pageNumb) {
        Map<String, Object> map = bookService.bookPage(Integer.parseInt(pageNumb), 5);
        session.setAttribute("books", map);
        return "views/book_tables";
    }


    @RequestMapping("/toInsert")
    public String toInsert() {
        return "views/book_insert";
    }

    //添加图书
    @RequestMapping("/insert")
    public String insert(@RequestParam("multipartFile") MultipartFile multipartFile,
                         Book book,
                         HttpSession session) {

        String imgPath = getFilePath(multipartFile, session);
        book.setImg(imgPath);
        bookMapper.insert(book);
        return "redirect:/book/toBookTables";
    }


    //根据id删除图书
    @RequestMapping("/delete/{id}")
    public String deleteBooK(@PathVariable("id") String id, Model model) {
        int i = bookMapper.deleteById(Long.parseLong(id));
        if (i == 0) {
            model.addAttribute("msg", "删除失败");
            return "views/book_tables";
        }
        model.addAttribute("msg", "删除成功");
        return "redirect:/book/toBookTables";
    }

    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable("id") String id, HttpSession session) {
        Book book = bookMapper.selectById(Long.parseLong(id));
        session.setAttribute("book", book);
        return "views/book_update";
    }

    //根据id更新图书
    @RequestMapping("/update")
    public String updateBook(Book book,
                             @RequestParam("multipartFile") MultipartFile multipartFile,
                             HttpSession session) {
        imgPath = getFilePath(multipartFile, session);
        book.setImg(imgPath);
        int i = bookMapper.updateById(book);
        if (i == 0) {
            return "views/book_tables";
        }
        return "redirect:/book/toBookTables";
    }

    //获取指定资源的classpath路径
    public String getRealPath() {
        return ClassUtils.getDefaultClassLoader().getResource("static/img/books/").getPath();
    }

    //获取图片的路径
    private String getFilePath(MultipartFile multipartFile, HttpSession session) {
        if (multipartFile.isEmpty()) {
            Book book = (Book) session.getAttribute("book");
            return imgPath = book.getImg();
        }


        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdir();
        }

        String uuid = UUID.randomUUID().toString();
        String fileName = multipartFile.getOriginalFilename();
        fileName = uuid + fileName.substring(fileName.lastIndexOf("."));
        String filePath = realPath + "/" + fileName;
        //使用transferTo(File dest)方法将上传文件转移到服务器上指定的文件路径;
        try {
            multipartFile.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgPath = "/img/books/" + fileName;
        return imgPath;
    }
}
