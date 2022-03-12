package com.librarymanager.library.controller;

import com.librarymanager.library.pojo.Book;
import com.librarymanager.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/book")
public class BookController{
//获取指定资源的classpath路径
    public String getRealPath(){
        return ClassUtils.getDefaultClassLoader().getResource("static/img/books/").getPath();
    }

    String realPath = getRealPath();

    String imgPath = null;
    @Autowired
    private BookService bookService;
    //图书管理页
    @RequestMapping("/toBookTables")
    public String toBookTables(HttpSession session){
        List<Book> books = bookService.queryAllBooks();
        session.setAttribute("books",books);
        return "views/book_tables";
    }

    @RequestMapping("/toInsert")
    public String toInsert(){
        return "views/book_insert";
    }
    //添加图书
    @RequestMapping("/insert")
    public String insert(@RequestParam("multipartFile") MultipartFile multipartFile,
                         @RequestParam("title") String title,
                         @RequestParam("author") String author,
                         @RequestParam("price") String price,
                         @RequestParam("type") String type,
                         HttpSession session){

        String imgPath = getImgPath(multipartFile,session);
        bookService.saveBook(new Book(null,title,author,type,price,imgPath,null,null,null,null));
        return "redirect:/book/toBookTables";
    }


    //根据id删除图书
    @RequestMapping("/delete/{id}")
    public String deleteBooK(@PathVariable("id") String id, Model model){
        int i = bookService.deleteBookById(Integer.parseInt(id));
        if (i == 0){
            model.addAttribute("msg","删除失败");
            return "views/book_tables";
        }
        model.addAttribute("msg","删除成功");
        return "redirect:/book/toBookTables";
    }

    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable("id") String id, HttpSession session){
        Book book = bookService.queryBookById(Integer.parseInt(id));
        session.setAttribute("book",book);
        return "views/book_update";
    }

    //根据id更新图书
    @RequestMapping("/update")
    public String updateBook(@RequestParam("id") String id,
                             @RequestParam("title") String title,
                             @RequestParam("author") String author,
                             @RequestParam("type") String type,
                             @RequestParam("price") String price,
                             @RequestParam("multipartFile")MultipartFile multipartFile,
                             HttpSession session){
        imgPath = getImgPath(multipartFile,session);
        int i = bookService.updateBookById(new Book(Integer.parseInt(id), title, author,type,price,imgPath,null,null, null, null));
        if (i == 0){
            return "views/book_tables";
        }
        return "redirect:/book/toBookTables";
    }

   //获取图片的路径
    private String getImgPath(MultipartFile multipartFile,HttpSession session) {
        if (multipartFile.isEmpty()){
            Book book = (Book) session.getAttribute("book");
            return imgPath = book.getImg();
        }
        System.out.println(realPath);
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdir();
        }
        String uuid = UUID.randomUUID().toString();
        String fileName = multipartFile.getOriginalFilename();
        fileName = uuid + fileName.substring(fileName.lastIndexOf("."));
        String filePath  = realPath + "/" + fileName;
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
