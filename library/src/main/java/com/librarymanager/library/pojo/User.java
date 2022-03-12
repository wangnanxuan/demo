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
public class User implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String tel;
    private String email;
    private Integer level;
    //字段添加填充类容
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
    @Version
    private Integer version;
    @TableLogic
    private Integer deleted;
}
