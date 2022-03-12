package com.librarymanager.library.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.librarymanager.library.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
