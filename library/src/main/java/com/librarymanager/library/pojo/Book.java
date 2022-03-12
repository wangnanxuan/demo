package com.librarymanager.library.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book implements Serializable {
    //id 雪花算法
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String title;
    private String author;
    private String type;
    private String price;
    private String img;
    //字段自动填充
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
    @Version
    private Integer version;
    //逻辑删除
    @TableLogic
    private Integer deleted;
}
