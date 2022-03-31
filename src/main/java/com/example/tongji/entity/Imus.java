package com.example.tongji.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * Imus 加速度数据
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("imus")
public class Imus {
    @TableId(type = IdType.AUTO)
    private Integer id;

    //解决与mysql关键字冲突,字段名加上反斜杠
    @TableField("`time_stamp`")
    private BigInteger timeStamp;

    private Integer imUid;

    private String accX;
    private String accY;
    private String accZ;
}
